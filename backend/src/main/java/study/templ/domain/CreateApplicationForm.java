package study.templ.domain;

import lombok.*;

@RequiredArgsConstructor
@Getter
public class CreateApplicationForm {
    @NonNull
    private Integer teamid;
    @NonNull
    private Integer userid;
    @NonNull
    private String contents;
}
