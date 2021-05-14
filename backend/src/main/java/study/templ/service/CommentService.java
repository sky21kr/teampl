package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.templ.domain.Comment;
import study.templ.domain.CreateCommentForm;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(@Lazy CommentRepository commentRepository){

        this.commentRepository =commentRepository;
    }

    @Transactional
    public Object createComment(CreateCommentForm createCommentForm, Optional<Team> target_team, Optional<User> user){

        Comment newComment = new Comment();


        if(createCommentForm.getComment_id() ==null){
            newComment.setLevel(1);
        }
        else{
            int super_comment_id = createCommentForm.getComment_id();
            Optional<Comment> super_comment = commentRepository.findById(super_comment_id);

            if(!super_comment.get().getLive()){
                throw new RuntimeException("Super_Comment is already dead");
            }
            newComment.setLevel(super_comment.get().getLevel()+1);
            newComment.setComment1(super_comment.get());
            super_comment.get().getSubComment().add(newComment);
        }

        newComment.setComment(createCommentForm.getComment());
        newComment.setTarget_team(target_team.get());
        newComment.setWriter(user.get());
        newComment.setDatetime(LocalDateTime.now());

        newComment.setLive(true);
        commentRepository.save(newComment);

        return newComment;
    }



    @Transactional
    public boolean deleteComment( int owner, int comment_id) {

        Optional<Comment> commentToDelete = commentRepository.findById(comment_id);

        //대댓글이 있는 경우 상위 댓글 삭제된 댓글로 바꾸기
        if(commentToDelete.isEmpty())
            return false;

        if (commentToDelete.get().getWriter().getUserid() != owner) {
            return false;

        }else{
            if (commentToDelete.get().getSubComment() != null) {
                commentToDelete.get().setComment("삭제된 댓글입니다. ");
                commentToDelete.get().setLive(false);
            }//대댓글이 없는 경우 그냥 삭제 하기.
            else {
                commentToDelete.get().setComment("삭제된 댓글입니다. ");
            }
            commentRepository.deleteById(comment_id);
        }
        return true;
    }


    public Optional<Comment> findById(Integer comment_id) {

        return commentRepository.findById(comment_id);
    }

    @Transactional
    public void editComment(Integer comment_id, String comment){
        Optional<Comment> comment1 = commentRepository.findById(comment_id);
        comment1.get().setComment(comment);
    }


}

