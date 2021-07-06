package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.templ.domain.*;
import study.templ.repository.AlarmRepository;
import study.templ.repository.CommentRepository;
import study.templ.repository.TeamRepository;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AlarmService alarmService;

    public CommentService() {
    }

    @Transactional
    public Object createComment(CreateCommentForm createCommentForm, Team target_team, User user){

        Comment newComment = new Comment();


        if(createCommentForm.getRefCommentId() ==null){
            newComment.setLevel(1);
        }
        else{
            int super_comment_id = createCommentForm.getRefCommentId();
            Optional<Comment> super_comment = commentRepository.findById(super_comment_id);

            if(!super_comment.get().getLive()){
                throw new RuntimeException("Super_Comment is already dead");
            }
            newComment.setLevel(super_comment.get().getLevel()+1);
            newComment.setComment1(super_comment.get());
            super_comment.get().getSubComment().add(newComment);

            alarmService.sendMessage1(user.getUserid(),target_team.getTeamid());

        }

        newComment.setComment(createCommentForm.getComment());
        newComment.setTarget_team(target_team);
        newComment.setWriter(user);
        newComment.setDatetime(LocalDateTime.now());

        newComment.setLive(true);
        commentRepository.save(newComment);

        return newComment;
    }



    @Transactional
    public boolean deleteComment( int owner, int comment_id) {

        Optional<Comment> commentToDelete = commentRepository.findById(comment_id);

        if(commentToDelete.isEmpty())
            return false;

        //댓글 작성자가 아닌 사용자가 삭제하려 할 때
        if (commentToDelete.get().getWriter().getUserid() != owner) {
            return false;

            //댓글 작성자가 삭제하려 할 때
        }else{
            //대댓글이 있을 때
            if (commentToDelete.get().getSubComment() != null) {
                commentToDelete.get().setComment("삭제된 댓글입니다. ");

                if(commentToDelete.get().getComment1() != null){
                    commentToDelete.get().setComment("삭제된 댓글입니다.");
                }
            }//대댓글이 없는 경우 그냥 삭제 하기.
            else {
                commentToDelete.get().setComment("삭제된 댓글입니다. ");
                commentToDelete.get().setLive(false);
            }
            commentRepository.deleteById(commentToDelete.get().getComment_id());

        }
        return true;
    }


    public Optional<Comment> findById(Integer comment_id) {

        return commentRepository.findById(comment_id);
    }

    @Transactional
    public void editComment(EditCommentForm editCommentForm){
        Optional<Comment> comment1 = commentRepository.findById(editCommentForm.getComment_id());
        comment1.get().setComment(editCommentForm.getComment());
    }
}

