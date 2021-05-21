package study.templ.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.templ.domain.Comment;
import study.templ.domain.CreateCommentForm;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.repository.CommentRepository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TeamService teamService;

    /*@AfterEach
    public void after() {
        userService.deleteAllUser();
        teamService.deleteAllTeam();
        commentRepository.deleteAll();
    }*/

        @Test
    public void 댓글생성 () throws Exception{
        User user = userService.createUser("acc", "pass", "nick").get();
        int userid = user.getUserid();
        Team team = teamService.createTeam(1,2,3,true,"1","1",userid).get();
        Comment comment = new Comment();
        comment.setDatetime(LocalDateTime.now());
        comment.setComment("contents");
        comment.setWriter(user);
        comment.setTarget_team(team);

        commentRepository.save(comment);

        Optional<Comment> result = commentRepository.findById(comment.getComment_id());
        Comment realresult = result.get();
        assertEquals(comment,realresult);



    }



    @Test
    public void 댓글삭제() throws Exception{


        Optional<User> user = Optional.ofNullable(userService.createUser("acc", "pass", "nick")).get();
        int userid= user.get().getUserid();
        Optional<Team> target_team = Optional.ofNullable(teamService.createTeam(0,0,0,false,"1","2",userid)).get();
        CreateCommentForm createCommentForm = new CreateCommentForm();
        createCommentForm.setComment("123");

        Comment comment = (Comment) commentService.createComment(createCommentForm,target_team,user);


        int owner_id = userid;
        int user_id1 = userService.createUser("acc","pass","nick").get().getUserid();


        Assertions.assertEquals(false,commentService.deleteComment(user_id1,comment.getComment_id()));
        Assertions.assertEquals(true, commentService.deleteComment(owner_id,comment.getComment_id()));



//위에서 유저 만들었는데 밑에도 다시 만듦. 그래서 테스트 결과가 false. 밑에거 지우고, 위에서 삽입. 맨 밑에 userid 두개로 겹침. commentid로 바꿈.





    }






}