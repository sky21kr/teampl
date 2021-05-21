package study.templ.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UpdateTeamForm {
    private Integer owner;
    private Integer teamid;
    private Integer category;
    private Integer numberofmembers;
    private Integer limit;
    private Boolean status;
    private String title;
    private String introduction;
}
