package study.templ.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamid;
    @NonNull
    private Integer category;
    @NonNull
    private Integer numberofmembers;
    @NonNull
    @Column(name = "\"limit\"")
    private Integer limit;
    @NonNull
    private Boolean status;
    @NonNull
    private String title;
    @NonNull
    private String introduction;
    @NonNull
    private String datetime;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "\"owner\"")
    @NonNull
    private User owner;

    @ManyToMany
    @JoinTable(name = "member", joinColumns = @JoinColumn(name = "team_id"),
                        inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<User> members;
    @OneToMany(mappedBy = "target_team", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Comment> owncomments;
    //코멘트 알람에 OneToMany 설정

}
