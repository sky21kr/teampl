package study.templ.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Getter
public class CreateTeamForm {
    private Integer category;
    private Integer numberofmembers;
    private Integer limit;
    private LocalDateTime datetime;
    private Boolean status;
    private String title;
    private String introduction;
    private Integer owner;
}
