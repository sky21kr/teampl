package study.templ.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateTeamForm {
    @NonNull
    private Integer category;
    @NonNull
    private Integer numberofmembers;
    @NonNull
    private Integer limit;
    @NonNull
    private Boolean status;
    @NonNull
    private String title;
    @NonNull
    private String introduction;
    @NonNull
    private Integer owner;
}
