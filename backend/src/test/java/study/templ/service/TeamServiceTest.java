package study.templ.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.templ.domain.Team;

import java.util.List;

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
        int user_id = userService.createUser("acc", "pass", "nick").getUserid();
        Team team = teamService.createTeam(1,3,2,false,"title","intro","20210407", user_id);
        //then
        if (teamService.getTeamById(team.getTeamid()).isEmpty())
            Assertions.fail("fail");
        Assertions.assertThat(teamService.getTeamById(team.getTeamid()).get().getCategory()).isEqualTo(team.getCategory());
    }

    @Test
    void getTeamByCategory() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").getUserid();
        teamService.createTeam(1, 2, 2, true, "t","i", "20120318", user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", user_id2);
        teamService.createTeam(3, 3, 3, true, "t3","i3", "130317", user_id1);
        //when
        List<Team> result = teamService.getTeamByCategory(3);
        //then
        Assertions.assertThat(2).isEqualTo(result.size());
    }

    @Test
    void getTeam() {
        //given
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").getUserid();
        teamService.createTeam(1, 2, 2, true, "t","i", "20120318", user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", user_id2);
        teamService.createTeam(1, 3, 3, true, "t3","i3", "130317", user_id1);
        teamService.createTeam(1, 3, 3, true, "t3","i3", "130317", user_id1);
        teamService.createTeam(1, 3, 3, true, "t3","i3", "130317", user_id1);
        //when
        Assertions.assertThat(5).isEqualTo(teamService.getTeam().size());
    }

    @Test
    void getTeamAsOwner() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id2 = userService.createUser("acc", "pass", "nick").getUserid();
        teamService.createTeam(1, 2, 2, true, "t","i", "20120318", user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", user_id2);
        teamService.createTeam(1, 3, 3, true, "t3","i3", "130317", user_id1);
        teamService.createTeam(1, 3, 3, true, "t3","i3", "130317", user_id1);
        teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", owner_id);
        //when
        Assertions.assertThat(1).isEqualTo(teamService.getTeamAsOwner(owner_id).size());
    }

    @Test
    void getTeamAsMember() {
    }

    @Test
    void getTeamContents() {
    }

    @Test
    void deleteTeamAsOwner() {
        //given
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        int user_id1 = userService.createUser("acc", "pass", "nick").getUserid();
        Team team = teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", owner_id);
        //when

        Assertions.assertThat(false).isEqualTo(teamService.deleteTeamAsOwner(user_id1, team.getTeamid()));
        Assertions.assertThat(true).isEqualTo(teamService.deleteTeamAsOwner(owner_id, team.getTeamid()));
        Assertions.assertThat(true).isEqualTo(teamService.getTeamById(team.getTeamid()).isEmpty());
    }
    @Test
    void ifUserDelete_OwnteamDelete(){
        int owner_id = userService.createUser("acc", "pass", "nick").getUserid();
        Team team = teamService.createTeam(3, 2, 2, true, "t2","i2", "22240513", owner_id);
        userService.deleteUserById(owner_id);
        Assertions.assertThat(false).isEqualTo(teamService.deleteTeamAsOwner(owner_id, team.getTeamid()));
    }
}