package study.templ.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.templ.domain.Alarm;
import study.templ.domain.CreateTeamForm;
import study.templ.domain.Team;
import study.templ.domain.User;
import java.util.List;


@SpringBootTest
class AlarmServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;
    @Autowired
    AlarmService alarmService;

    @Test
    public void 알림생성1() throws Exception {

        //given

        User user = userService.createUser("acc", "pass", "nick"); //댓글 단 유저
        User user1 = userService.createUser("acc", "pass", "nick"); //팀 주인 유저
        int commentUser = user.getUserid();
        CreateTeamForm createTeamForm = new CreateTeamForm(1, 1, 1, true, "3", "3", user1.getUserid());
        Team team = teamService.createTeam(createTeamForm);

        alarmService.sendMessage1(user1.getUserid(), team.getTeamid());

        int userid = team.getOwner().getUserid();
        List<Alarm> alarmList = (List<Alarm>) alarmService.updateAlarm(userid);

        //then
        Assertions.assertEquals(1, alarmList.size());

    }

}