package study.templ.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "target_user")
    private Integer target_user;


    @ManyToOne
    @JoinColumn(name = "target_team")
    private Team target_team;

    @NonNull
    private String contents;



}
