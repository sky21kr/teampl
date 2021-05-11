package study.templ.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.templ.domain.Member;
import study.templ.domain.Team;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        //when
        int user_id = userService.createUser("acc", "pass", "nick").get().getUserid();
        Optional<Team> isTeam = teamService.createTeam(1,3,2,false,"title","intro",user_id);
        if (isTeam.isEmpty())
            Assertions.fail("creationfailed");
        Team team= isTeam.get();
        //then
        if (teamService.getTeamById(team.getTeamid()).isEmpty())
            Assertions.fail("fail");
        Assertions.assertThat(teamService.getTeamById(team.getTeamid()).get().getCategory()).isEqualTo(team.getCategory());
    }

    @Test
    void getTeamByCategory() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").get().getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").get().getUserid();
        teamService.createTeam(1, 2, 2, true, "t","i", user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", user_id2);
        teamService.createTeam(3, 3, 3, true, "t3","i3", user_id1);
        //when
        List<Team> result = teamService.getTeamByCategory(3);
        //then
        Assertions.assertThat(2).isEqualTo(result.size());
    }

    @Test
    void getTeam() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").get().getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").get().getUserid();
        teamService.createTeam(1, 2, 2, true, "t","i",  user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", user_id2);
        teamService.createTeam(1, 3, 3, true, "t3","i3", user_id1);
        teamService.createTeam(1, 3, 3, true, "t3","i3", user_id1);
        teamService.createTeam(1, 3, 3, true, "t3","i3", user_id1);
        //when
        Assertions.assertThat(5).isEqualTo(teamService.getTeam().size());
    }

    @Test
    void getTeamContents() {
        //given
        int user_id = userService.createUser("acc", "pass", "nick").get().getUserid();
        int team_id = teamService.createTeam(1, 2, 2, true, "t","i", user_id).get().getTeamid();
        Assertions.assertThat(teamService.getTeamContents(team_id).get().getIntroduction()).isEqualTo(teamService.getTeamById(team_id).get().getIntroduction());
    }

    @Test
    void deleteTeamAsOwner() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").get().getUserid();
        int user_id1 = userService.createUser("acc", "pass", "nick").get().getUserid();
        Optional<Team> isTeam = teamService.createTeam(3, 2, 2, true, "t2","i2", owner_id);
        //when
        if (isTeam.isEmpty())
            Assertions.fail("creationfail");
        Team team = isTeam.get();
        Assertions.assertThat(false).isEqualTo(teamService.deleteTeamAsOwner(user_id1, team.getTeamid()));
        Assertions.assertThat(true).isEqualTo(teamService.deleteTeamAsOwner(owner_id, team.getTeamid()));
        Assertions.assertThat(true).isEqualTo(teamService.getTeamById(team.getTeamid()).isEmpty());
    }

    @Test
    void ifUserDelete_OwnteamDelete(){
        int owner_id = userService.createUser("acc", "pass", "nick").get().getUserid();
        Optional<Team> isTeam = teamService.createTeam(3, 2, 2, true, "t2","i2", owner_id);
        if (isTeam.isEmpty())
            Assertions.fail("creationfail");
        Team team = isTeam.get();
        userService.deleteUserById(owner_id);
        Assertions.assertThat(false).isEqualTo(teamService.deleteTeamAsOwner(owner_id, team.getTeamid()));
    }

    @Test
    void getMemberOfTeam() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").get().getUserid();
        int user_id = userService.createUser("a","p","n").get().getUserid();
        int team_id = teamService.createTeam(1,3,4,false,"t","i", owner_id).get().getTeamid();
        //when
        userService.createApplication(team_id, user_id, "application_contents");
        //reject
        userService.acceptApplication(owner_id,team_id,user_id,false);
        //then
        for(Member m : teamService.getMemberOfTeam(team_id))
            if (m.getUserid()==user_id)
                Assertions.fail("havetofail");
        for(Member m : userService.getTeamAsMember(user_id))
            if (m.getTeamid()==team_id)
                Assertions.fail("havetofail");
        //accept
        userService.acceptApplication(owner_id,team_id,user_id,true);
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