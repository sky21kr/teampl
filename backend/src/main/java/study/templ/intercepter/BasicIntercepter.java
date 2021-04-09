package study.templ.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import study.templ.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicIntercepter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    private String HEADER_TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("hahaha");

        String token = request.getHeader(HEADER_TOKEN_KEY);
        System.out.println("token: " + token);

        if (!token.equals("1234")) {

            throw new IllegalArgumentException("토큰 불일치");
        }

        return true;
    }
}
