package study.templ.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Team> ownteams;

}
