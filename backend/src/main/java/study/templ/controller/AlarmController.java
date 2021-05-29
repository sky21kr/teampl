package study.templ.controller;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.*;
import study.templ.repository.AlarmRepository;
import study.templ.service.AlarmService;
import study.templ.service.CommentService;
import study.templ.service.TeamService;
import study.templ.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//알림 정보를 db에 저장하는 방식으로 해야함 로그인시 사용자한테 보내는 용도
@RestController
public class AlarmController {

    @Autowired
    AlarmRepository alarmRepository;
    @Autowired
    AlarmService alarmService;
    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;
    @Autowired
    CommentService commentService;



    @GetMapping("/alarm")
    public List<Alarm> sendMessages(@RequestParam("userid") int userid){


        userService.getUserById(userid);

        return alarmService.updateAlarm(userid);

    }
    @Getter
    static class requestAlarm {
        @NonNull
        Integer userid;
    }

}


















