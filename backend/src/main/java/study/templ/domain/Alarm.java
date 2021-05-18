package study.templ.domain;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Alarm {

    @Id
    @GeneratedValue
    @NonNull
    private Integer alarm_id;

    @NonNull
    private LocalDateTime datetime;

    @NonNull
    private Integer receiver;


    @JoinColumn(name = "userid")
    private Integer target_user; //가입 신청 수락시


    @JoinColumn(name = "teamid")
    private Integer target_team; //가입 신청, 댓글 남겼을 때 팀 주인한테

    @NonNull
    private String contents;



}
