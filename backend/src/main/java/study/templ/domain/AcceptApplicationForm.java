package study.templ.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AcceptApplicationForm {
    @NonNull
    private Integer requestid;
    @NonNull
    private Integer teamid;
    @NonNull
    private Integer userid;
    @NonNull
    private Boolean accept;
}
