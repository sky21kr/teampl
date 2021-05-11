package study.templ.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CreateCommentForm {

    private String comment;
    private Integer teamid ;
    private Integer comment_id;



}
