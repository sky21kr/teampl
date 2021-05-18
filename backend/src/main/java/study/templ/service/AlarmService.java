package study.templ.service;


import org.springframework.stereotype.Service;

import study.templ.domain.Alarm;

import study.templ.repository.AlarmRepository;

import java.time.LocalDateTime;


@Service
public class AlarmService {

    TeamService teamService;
    UserService userService;
    AlarmRepository alarmRepository;

    //1. 댓글 달렸을 때 팀 주인에게 알림 가는 것
    public void sendMessage1(int userid, int teamid ){

        String userName = userService.getUserById(userid).get().getNickname();
        String teamName = teamService.getTeamById(teamid).get().getTitle();
        String contents = userName+"님이 "+teamName+"에 댓글을 달았습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(userid,datetime,teamid,contents);
        alarmRepository.save(alarm);


    }
    //2.가입 신청 했을 때 가입자 신청 버튼 -> 팀 주인한테 메시지가 가는 방식이어야함.
    public void sendMessage2(int userid, int teamid){

        String userName = userService.getUserById(userid).get().getNickname();
        String teamName = teamService.getTeamById(teamid).get().getTitle();
        String contents = userName+"님이 "+teamName+"에 가입신청을 했습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(userid,datetime,teamid,contents);
        alarmRepository.save(alarm);


    }

    //3. 가입 수락이 되었을 때 target_user한테 연락이 가게 됨.
    public void sendMessage3(int userid, int teamid){

        String userName = userService.getUserById(userid).get().getNickname();
        String teamName = teamService.getTeamById(teamid).get().getTitle();
        String contents = userName+"이 가입신청한"+teamName+"팀에 가입되었습니다. ";
        LocalDateTime datetime = LocalDateTime.now();
        Alarm alarm = new Alarm(userid,datetime,teamid,contents);
        alarmRepository.save(alarm);


    }
}
