package study.templ.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import study.templ.domain.User;
import study.templ.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthIntercepter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = userRepository.findById(Integer.parseInt(request.getHeader("userId"))).orElseThrow(() -> new IllegalArgumentException("유저 아님"));

        String token = request.getHeader(HEADER_TOKEN_KEY);

        if (!token.equals(user.getToken())) {

            throw new IllegalArgumentException("토큰 불일치");
        }

        return true;
    }
}