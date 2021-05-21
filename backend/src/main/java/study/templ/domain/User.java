package study.templ.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userid;
    @NonNull
    @Column(name = "account_id")
    String accountid;
    @NonNull
    String password;
    @NonNull
    String nickname;
    @Column(name = "auth_token")
    @Setter
    String token;

    @JsonIgnore     //getTeamAsMember
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Member> memberteams;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Application> applications;

    @JsonIgnore     //getTeamAsOwner
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    List<Team> ownteams;
    @OneToMany(mappedBy = "writer")
    List<Comment> owncomments;


}
