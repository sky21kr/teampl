package study.templ.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import study.templ.domain.*;
import study.templ.service.CommentService;
import study.templ.service.TeamService;
import study.templ.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class CommentController {


    @Autowired
    private  CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;



    @PostMapping("comment/create")
    public Object createComment(@RequestBody CreateCommentForm createCommentForm) {

        int user_id= createCommentForm.getUserid();
        User writer = userService.getUserById(user_id);
        Team target_team = teamService.getTeamById(createCommentForm.getTeamid());

        return commentService.createComment(createCommentForm,target_team,writer);
    }

    @PostMapping("comment/edit")
    public String editComment(@RequestBody EditCommentForm editCommentForm){

        commentService.editComment(editCommentForm);
        Team team_id= commentService.findById(editCommentForm.getComment_id()).get().getTarget_team();
        return "redirect:/team/"+team_id;
    }


        @DeleteMapping("comment/delete")
        public String deleteComment(@RequestParam("comment_id") int comment_id){

            Optional<Comment> commentToDelete =commentService.findById(comment_id);
            User owner = userService.getUserById(commentToDelete.get().getWriter().getUserid());

            int team_id= commentToDelete.get().getTarget_team().getTeamid();
            commentService.deleteComment( owner.getUserid(), comment_id);
            return "redirect:/team/" +team_id;
    }

    @GetMapping("team")
    public Integer numberOfComments(@RequestParam("team_id") int team_id){

        Team team = teamService.getTeamById(team_id);
        int number = team.getOwncomments().size();

        return number;
    }


}
