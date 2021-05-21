package study.templ.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.*;
import study.templ.service.CommentService;
import study.templ.service.TeamService;
import study.templ.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class CommentController {


    @Autowired
    private final CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CommentController(@Lazy CommentService commentService) {
        this.commentService=commentService;
    }


//httpsession: 인터페이스, 둘 이상의 페이지 리퀘스트에서 사용자를 식별하거나, 웹 사이트를 방문하 해당 사용자에 대한 정보저장방법 제공
    @PostMapping("/create")
    public Object createComment(@Validated @RequestBody CreateCommentForm createCommentForm, @RequestHeader("userId") int user_id, HttpSession httpSession) {

        User writer = userService.getUserById(user_id);
        int team_id = createCommentForm.getTeamid();
        Team target_team = teamService.getTeamById(createCommentForm.getTeamid());

        return commentService.createComment(createCommentForm,Optional.of(target_team),Optional.of(writer));
    }

    @PostMapping("/edit")
    public String editComment(@RequestParam String datgeul, @RequestParam String comment, HttpSession httpSession){

        Integer comment_id = Integer.parseInt(datgeul);

        commentService.editComment(comment_id,comment);
        Team team_id= commentService.findById(comment_id).get().getTarget_team();
        return "redirect:/team/="+team_id;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam String comment, HttpSession httpSession){

        int comment_id=  Integer.parseInt(comment);
        Optional<Comment> commentToDelete =commentService.findById(comment_id);
        User owner = userService.getUserById(commentToDelete.get().getWriter().getUserid());


        int team_id= commentToDelete.get().getTarget_team().getTeamid(); //팀페이지 아이디 얻어오기
        commentService.deleteComment( owner.getUserid(), comment_id);
        return "redirect:/post/read?post=" +team_id;
    }
}
