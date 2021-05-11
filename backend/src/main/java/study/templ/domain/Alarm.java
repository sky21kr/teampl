package study.templ.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Alarm {

    @Id
    @GeneratedValue
    private Integer alarm_id;

    private Integer type;

    private LocalDateTime datetime;

    /*private Integer receiver; //알림을 받는 사람 owner 또는 신청자*/


    @JoinColumn(name = "userid")
    private Integer target_user; //가입 신청 수락시


    @JoinColumn(name = "teamid")
    private Integer target_team; //가입 신청, 댓글 남겼을 때 팀 주인한테



}
