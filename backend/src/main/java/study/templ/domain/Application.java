package study.templ.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MemberId.class)
@Table(name = "application")
public class Application {
    @Id
    @Column(name = "team_id")
    private Integer teamid;

    @Id
    @Column(name = "user_id")
    private Integer userid;

    private String contents;

    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private  User user;

}
