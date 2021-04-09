package study.templ.domain;

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

    String token;
}