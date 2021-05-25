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
@NoArgsConstructor
@Table(name = "alarm")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alarm_id;

    @NonNull
    private LocalDateTime datetime;

    @NonNull
    private Integer targetuser;

    @ManyToOne
    @JoinColumn(name = "target_team")
    private Team target_team;

    @NonNull
    private String contents;


}
