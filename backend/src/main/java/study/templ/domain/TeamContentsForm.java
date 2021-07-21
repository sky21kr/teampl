package study.templ.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeamContentsForm {
    private Integer userid;
    private Integer category;
    private LocalDateTime datetime;
    private String nickname;
    private Boolean status;
    private String title;
    private String introduction;
    private List<Comment> comments;
}
