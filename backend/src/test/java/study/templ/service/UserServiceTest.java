package study.templ.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
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
    @BeforeEach
    void delete(){
        userService.deleteAllUser();
        teamService.deleteAllTeam();
        memberRepository.deleteAll();
        applicationRepository.deleteAll();
    }

    @Test
    void createApplication() {
        //given
        Optional<User> isUser1 = userService.createUser("a","b","c");
        Optional<User> isUser2 = userService.createUser("a","b","c");
        Optional<User> isUser3 = userService.createUser("a","b","c");
        Optional<Team> isTeam = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.get().getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser2.get().getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser3.get().getUserid(),"c"));
        //then
        Assertions.assertThat(applicationRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void acceptApplication() {
        //given
        Optional<User> isUser1 = userService.createUser("a","b","c");
        Optional<User> isUser2 = userService.createUser("a","b","c");
        Optional<User> isUser3 = userService.createUser("a","b","c");
        Optional<Team> isTeam = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.get().getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser2.get().getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser3.get().getUserid(),"c"));
        userService.acceptApplication(new AcceptApplicationForm(isUser1.get().getUserid(),isTeam.get().getTeamid(),isUser2.get().getUserid(),true));
        userService.acceptApplication(new AcceptApplicationForm(isUser1.get().getUserid(),isTeam.get().getTeamid(),isUser3.get().getUserid(),false));
        //then
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(1);
    }
    @Test
    void ifUserDelete_ApplicationDeleteMemberDelete(){
        //given
        Optional<User> isUser1 = userService.createUser("a","b","c");
        Optional<User> isUser2 = userService.createUser("a","b","c");
        Optional<User> isUser3 = userService.createUser("a","b","c");
        Optional<User> isUser4 = userService.createUser("a","b","c");
        Optional<Team> isTeam = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",isUser1.get().getUserid()));
        //when
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser2.get().getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser3.get().getUserid(),"c"));
        userService.createApplication(new CreateApplicationForm(isTeam.get().getTeamid(),isUser4.get().getUserid(),"c"));
        //234
        userService.acceptApplication(new AcceptApplicationForm(isUser1.get().getUserid(),isTeam.get().getTeamid(),isUser2.get().getUserid(),true));
        //2 -> member
        userService.deleteUserById(isUser2.get().getUserid());
        userService.deleteUserById(isUser3.get().getUserid());
        //2 delete 3 delete
        //then
        //application 4 member 0
        Assertions.assertThat(applicationRepository.findAll().size()).isEqualTo(1);
        Assertions.assertThat(memberRepository.findAll().size()).isEqualTo(0);

    }

    @Test
    void deleteMember() {
        //given
        int user1 = userService.createUser("a","b","c").get().getUserid();
        int user2 = userService.createUser("a","b","c").get().getUserid();
        int user3 = userService.createUser("a","b","c").get().getUserid();
        int team1 = teamService.createTeam(new CreateTeamForm(0,3,2,false,"t","i",user1)).get().getTeamid();
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
}