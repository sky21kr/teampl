package study.templ.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@IdClass(MemberId.class)
@Table(name = "member")
public class Member {
    @Id
    @NonNull
    @Column(name = "team_id")
    private Integer teamid;
    @Id
    @NonNull
    @Column(name = "user_id")
    private Integer userid;

    @JsonBackReference
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    @NonNull
    @ManyToOne
    private Team team;

    @JsonBackReference
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @NonNull
    @ManyToOne
    private User user;

}
