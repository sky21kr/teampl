package study.templ.intercepter;

import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import study.templ.domain.User;
import study.templ.infra.AuthenticationFailedException;
import study.templ.repository.UserRepository;
import study.templ.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtAuthIntercepter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        String userId = request.getHeader("userId");
        String token = request.getHeader(HEADER_TOKEN_KEY);

        if (userId == null) {
            throw new AuthenticationFailedException("userId 헤더 없음");
        }

        if (token == null) {
            throw new AuthenticationFailedException("token 헤더 없음");
        }

        User user = userRepository.findById(Integer.parseInt(userId)).orElseThrow(() -> new AuthenticationFailedException("유저 아님"));

        if (!token.equals(user.getToken())) {
            throw new AuthenticationFailedException("토큰 불일치");
        }

        return true;
    }
}