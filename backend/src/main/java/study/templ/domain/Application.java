package study.templ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MemberId.class)
@Table(name = "application")
public class Application {
    @JsonIgnore
    @Id
    @Column(name = "team_id")
    private Integer teamid;

    @JsonIgnore
    @Id
    @Column(name = "user_id")
    private Integer userid;

    private String contents;

    @JsonIgnore
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private  User user;

}
