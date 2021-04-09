package study.templ.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NonNull
public class CreateCommentForm {

    private String comment;
    private String target_team;
    private Integer comment_id;
    private String writer;
    private LocalDateTime dateTime;



}
