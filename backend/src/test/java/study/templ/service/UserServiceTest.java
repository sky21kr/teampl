package study.templ.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.templ.domain.*;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.MemberRepository;

import javax.persistence.Access;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MemberRepository memberRepository;
    @AfterEach
    void delete(){
        userService.deleteAllUser();
        teamService.deleteAllTeam();
        memberRepository.deleteAll();
        applicationRepository.deleteAll();
    }

    @Test
    void createApplication() {
        //given
        User isUser1 = userService.createUser("a","b","c");
        User isUser2 = userService.createUser("a","b","c");
        User isUser3 = userService.createUser("a","b","c");
        Team team = (Team) teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser2.getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser3.getUserid(),"c"));
        //then
        Assertions.assertThat(applicationRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void acceptApplication() {
        //given
        User isUser1 = userService.createUser("a","b","c");
        User isUser2 = userService.createUser("a","b","c");
        User isUser3 = userService.createUser("a","b","c");
        Team team = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser2.getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser3.getUserid(),"c"));
        userService.acceptApplication(new AcceptApplicationForm(isUser1.getUserid(),team.getTeamid(),isUser2.getUserid(),true));
        userService.acceptApplication(new AcceptApplicationForm(isUser1.getUserid(),team.getTeamid(),isUser3.getUserid(),false));
        //then
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(2);  //owner + user2
    }
    @Test
    void ifUserDelete_ApplicationDeleteMemberDelete(){
        //given
        User isUser1 = userService.createUser("a","b","c");
        User isUser2 = userService.createUser("a","b","c");
        User isUser3 = userService.createUser("a","b","c");
        User isUser4 = userService.createUser("a","b","c");
        Team team= teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser2.getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser3.getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(team.getTeamid(),isUser4.getUserid(),"c"));
        //234
        userService.acceptApplication(new AcceptApplicationForm(isUser1.getUserid(),team.getTeamid(),isUser2.getUserid(),true));
        //2 -> member
        userService.deleteUserById(isUser2.getUserid());
        userService.deleteUserById(isUser3.getUserid());
        //2 delete 3 delete
        //then
        //application 4 member 1
        Assertions.assertThat(applicationRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(1);

    }

    @Test
    void deleteMember() {
        //given
        int user1 = userService.createUser("a","b","c").getUserid();
        int user2 = userService.createUser("a","b","c").getUserid();
        int user3 = userService.createUser("a","b","c").getUserid();
        int team1 = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",user1)).getTeamid();
        userService.createApplication(new CreateApplicationForm(team1, user2, "contents"));
        userService.createApplication(new CreateApplicationForm(team1, user3, "contents"));
        userService.acceptApplication(new AcceptApplicationForm(user1, team1, user2, true));
        userService.acceptApplication(new AcceptApplicationForm(user1, team1, user3, true));
        //when
        userService.deleteMember(user2, team1, user2);
        userService.deleteMember(user3, team1, user1);
        //then
        Assertions.assertThat(memberRepository.findById(new MemberId(team1, user2))).isEmpty();
        Assertions.assertThat(memberRepository.findById(new MemberId(team1, user3))).isEmpty();
    }

    @Test
    void getTeamAsMember() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id = userService.createUser("a","p","n").getUserid();
        int team_id = (teamService.createTeam(new CreateTeamForm(1,3,4,false,"t","i", owner_id))).getTeamid();

        //when reject
        userService.createApplication(new CreateApplicationForm(team_id, user_id, "application_contents"));
        userService.acceptApplication(new AcceptApplicationForm(owner_id,team_id,user_id,false));
        //then
        Assertions.assertThat(userService.getTeamAsMember(user_id).size()).isEqualTo(0);
        //when accept and own team
        userService.createApplication(new CreateApplicationForm(team_id, user_id, "application_contents"));
        userService.acceptApplication(new AcceptApplicationForm(owner_id,team_id,user_id,true));
        teamService.createTeam(new CreateTeamForm(2, 3,3, true, "t", "in",user_id));
        //then
        List<Team> result = userService.getTeamAsMember(user_id);
        Assertions.assertThat(result.size()).isEqualTo(2);
        boolean flag = false;
        for (Team t:result){
            if (t.getTeamid().intValue()==team_id)
                flag = true;
        }
        Assertions.assertThat(flag).isEqualTo(true);
    }
}