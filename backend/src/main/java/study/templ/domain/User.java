package study.templ.domain;
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

    @ManyToMany(mappedBy = "members")
    Set<Team> teams;


    @JsonManagedReference
    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<Team> ownteams;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    List<Comment> owncomments;
    @Column(name = "auth_token")
    String token;
}
