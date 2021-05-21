package study.templ.service;

import io.swagger.models.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import study.templ.domain.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TeamServiceTest {
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @AfterEach
    void afterTest(){
        teamService.deleteAllTeam();
        userService.deleteAllUser();
    }

    @Test
    void createTeam() {
        //given
        int user_id = userService.createUser("acc", "pass", "nick").getUserid();
        //fail 404
        CreateTeamForm createTeamForm = new CreateTeamForm(1,3,2,false,"title","intro",user_id+1);
        try {
            teamService.createTeam(createTeamForm);
        }
        catch(Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("user not found. check user id.");
        }
        //success
        createTeamForm = new CreateTeamForm(1,3,2,false,"title","intro",user_id);
        Team team = teamService.createTeam(createTeamForm);
        //then
        Assertions.assertThat(team.getTitle()).isEqualTo(createTeamForm.getTitle());

    }

    @Test
    void getTeamByCategory() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").getUserid();
        teamService.createTeam(new CreateTeamForm(1, 2, 2,true, "t","i", user_id1));
        teamService.createTeam(new CreateTeamForm(3, 2, 2,true, "t2","i2", user_id2));
        teamService.createTeam(new CreateTeamForm(3, 3, 3,true, "t3","i3", user_id1));
        //when
        List<Team> result = teamService.getTeamByCategory(PageRequest.of(0,1000),3).getContent();
        //then
        Assertions.assertThat(2).isEqualTo(result.size());
    }

    @Test
    void getTeam() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").getUserid();
        teamService.createTeam(new CreateTeamForm(1, 2, 2, true, "t","i",  user_id1));
        teamService.createTeam(new CreateTeamForm(3, 2, 2,true, "t2","i2", user_id2));
        teamService.createTeam(new CreateTeamForm(1, 3, 3,true, "t3","i3", user_id1));
        teamService.createTeam(new CreateTeamForm(1, 3, 3,true, "t3","i3", user_id1));
        teamService.createTeam(new CreateTeamForm(1, 3, 3,true, "t3","i3", user_id1));
        //when
        Assertions.assertThat(5).isEqualTo(teamService.getTeam(PageRequest.of(0,1000)).getContent().size());
    }

    @Test
    void getTeamContents() {
        //given
        int user_id = userService.createUser("acc", "pass", "nick").getUserid();
        int team_id =((Team)teamService.createTeam(new CreateTeamForm(1, 2, 2, true, "t","i", user_id))).getTeamid();
        Assertions.assertThat(teamService.getTeamContents(team_id).getIntroduction()).isEqualTo(teamService.getTeamById(team_id).getIntroduction());
    }

    @Test
    void deleteTeamAsOwner() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        Team team= teamService.createTeam(new CreateTeamForm(3, 2, 2,true, "t2","i2", owner_id));
        //when then
        try{
            teamService.deleteTeamAsOwner(user_id1, team.getTeamid());
        }
        catch(Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("user is not owner of team.");
        }
        try {
            teamService.deleteTeamAsOwner(owner_id, team.getTeamid());
        }
        catch (Exception e){
            Assertions.fail("fail");
        }
        try{
            teamService.getTeamById(team.getTeamid());
        }
        catch (Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("team not found. check team id.");
        }
    }

    @Test
    void ifUserDelete_OwnteamDelete(){
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        Team team = teamService.createTeam(new CreateTeamForm(3, 2, 2,true, "t2","i2", owner_id));
        userService.deleteUserById(owner_id);
        try {
            teamService.deleteTeamAsOwner(owner_id, team.getTeamid());
        }
        catch(Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("team not found. check team id.");
        }
    }

    @Test
    void getMemberOfTeam() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id = userService.createUser("a","p","n").getUserid();
        int team_id = (teamService.createTeam(new CreateTeamForm(1,3,4,false,"t","i", owner_id))).getTeamid();
        //when
        userService.createApplication(new CreateApplicationForm(team_id, user_id, "application_contents"));
        //reject
        userService.acceptApplication(new AcceptApplicationForm(owner_id,team_id,user_id,false));
        //then
        for(Member m : teamService.getMemberOfTeam(team_id)) {
            if (m.getUserid() == user_id)
                Assertions.fail("fail");
        }
        for(Member m : userService.getTeamAsMember(user_id)) {
            if (m.getTeamid() == team_id)
                Assertions.fail("fail");
        }
        //when
        userService.createApplication(new CreateApplicationForm(team_id, user_id, "application_contents"));
        //accept
        userService.acceptApplication(new AcceptApplicationForm(owner_id,team_id,user_id,true));
        //then
        boolean flag = false;
        System.out.println(teamService.getMemberOfTeam(team_id).size());
        for(Member m : teamService.getMemberOfTeam(team_id)) {
            if (m.getUserid() == user_id)
                flag = true;
        }
        Assertions.assertThat(flag).isEqualTo(true);

        flag = false;
        System.out.println(userService.getTeamAsMember(user_id).size());
        for(Member m : userService.getTeamAsMember(user_id)) {
            if (m.getTeamid() == team_id)
                flag= true;
        }
        Assertions.assertThat(flag).isEqualTo(true);
    }

}