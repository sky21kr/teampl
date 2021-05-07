package study.templ.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {
    private String ISSUER = "Penguin";
    private String SECRET_KEY = "asdf";
    private int EXP_DURATION = 3600;

    public String createToken() {
        return JWT.create()
                .withSubject("userid")
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * EXP_DURATION))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public void verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withIssuer(ISSUER)
                .build();

        verifier.verify(token);
    }
}
