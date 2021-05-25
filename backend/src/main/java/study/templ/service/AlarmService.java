package study.templ.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.templ.domain.Alarm;

import study.templ.domain.Team;
import study.templ.repository.AlarmRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class AlarmService {

    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    AlarmRepository alarmRepository;

    //1. 댓글 달렸을 때 팀 주인에게 알림 가는 것
    public void sendMessage1(int userid, int teamid ){


        Team team = teamService.getTeamById(teamid);
        String userName = userService.getUserById(userid).getNickname();
        String teamName = team.getTitle();
        String contents = userName+"님이 "+teamName+"에 댓글을 달았습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(datetime, userid,contents);

        System.out.println(contents);
        System.out.println(alarm.getContents());

        alarmRepository.save(alarm);



    }
    //2.가입 신청 했을 때 팀 주인한테 메시지가 가는 방식이어야함.
    public String sendMessage2(int userid, int teamid){

        String userName = userService.getUserById(userid).getNickname();
        String teamName = teamService.getTeamById(teamid).getTitle();
        String contents = userName+"님이 "+teamName+"에 가입신청을 했습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(datetime, userid,contents);
        alarmRepository.save(alarm);
        return alarm.getContents();


    }

    //3. 가입 수락이 되었을 때 targetuser한테 연락이 가게 됨.
    public String sendMessage3(int userid, int teamid){

        String userName = userService.getUserById(userid).getNickname();
        String teamName = teamService.getTeamById(teamid).getTitle();
        String contents = userName+"이 가입신청한"+teamName+"팀에 가입되었습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(datetime, userid,contents);
        alarmRepository.save(alarm);
        return alarm.getContents();

    }


    public List<Alarm> updateAlarm(int userid) {

        userService.getUserById(userid);
        List<Alarm> alarms = alarmRepository.findByTargetuser(userid);
        return alarms;
    }



}
