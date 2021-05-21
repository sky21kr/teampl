package study.templ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import study.templ.domain.*;
import study.templ.repository.AlarmRepository;
import study.templ.service.AlarmService;
import study.templ.service.CommentService;
import study.templ.service.TeamService;
import study.templ.service.UserService;

import java.util.Optional;

//알림 정보를 db에 저장하는 방식으로 해야함 로그인시 사용자한테 보내는 용도
@Controller
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

    //댓글 달았을 때
    @PostMapping("/")
    public void getComments(CreateCommentForm createCommentForm , Optional<User> writer, Optional<Team> target_team){

        Comment newComment= (Comment) commentService.createComment(createCommentForm, target_team, writer);
        int userid= newComment.getWriter().getUserid();
        int teamid = newComment.getTarget_team().getTeamid();

        alarmService.sendMessage1(userid,teamid);
    }
    //가입 신청했을 때
    public void apply(int team_id, int user_id, String contents){

        boolean application =userService.createApplication(team_id,user_id,contents);
        Optional<Team> teamid = teamService.getTeamById(team_id);
        Optional<User> userid = userService.getUserById(user_id);
        if( application == true ) {
            alarmService.sendMessage2(userid.get().getUserid(), teamid.get().getTeamid());
        }

    }

    //가입 수락이 되었을 경우
    public void accepted(int owner, int team_id, int user_id, boolean accept){

        Optional<Team> teamid = teamService.getTeamById(team_id);
        Optional<User> userid = userService.getUserById(user_id);
        int admitted = userService.acceptApplication(owner, team_id,user_id,accept);

        if(admitted == 1){

            alarmService.sendMessage3(userid.get().getUserid(),teamid.get().getTeamid());
        }
    }




}
