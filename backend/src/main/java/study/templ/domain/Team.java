package study.templ.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime datetime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "\"owner\"")
    @NonNull
    private User owner;

    @JsonIgnore     //getMember
    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<Member> members;
    @JsonIgnore     //getApplication?
    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    List<Application> applications;
    @JsonIgnore     //getTeamContents
    @OneToMany(mappedBy = "target_team", cascade = CascadeType.REMOVE)
    private List<Comment> owncomments;
    //코멘트 알람에 OneToMany 설정

}
