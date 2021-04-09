package study.templ.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.Comment;
import study.templ.domain.CreateCommentForm;
import study.templ.domain.Team;
import study.templ.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class CommentController {


    private final CommentService commentService;

    @Autowired
    private CommentController(@Lazy CommentService commentService) {
        this.commentService=commentService;
    }


//httpsession: 인터페이스, 둘 이상의 페이지 리퀘스트에서 사용자를 식별하거나, 웹 사이트를 방문하 해당 사용자에 대한 정보저장방법 제공
    @PostMapping("/create")
    public String createComment(CreateCommentForm createCommentForm) {

        commentService.createComment(createCommentForm);
        long team_id = Long.parseLong(createCommentForm.getTarget_team());

        return "redirect:/team/"+team_id; //어디로 반환해야할지 잘 모르겠음.
    }

    @PostMapping("/edit")
    public String editComment(@RequestParam String datgeul, @RequestParam String comment, HttpSession httpSession){

        Integer comment_id = Integer.parseInt(datgeul);

        commentService.editComment(comment_id,comment);
        @NonNull Team team_id= commentService.findById(comment_id).get().getTarget_team();
        return "redirect:/team/="+team_id;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam String comment, HttpSession httpSession){
        int comment_id = Integer.parseInt(comment);
        Optional<Comment> commentToDelete =commentService.findById(comment_id);

        long team_id= commentToDelete.get().getTarget_team().getTeamid(); //팀페이지 아이디 얻어오기
        commentService.deleteComment(comment_id);
        return "redirect:/post/read?post=" +team_id;
    }
}
