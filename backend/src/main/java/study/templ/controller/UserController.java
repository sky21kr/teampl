package study.templ.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import study.templ.domain.User;
import study.templ.repository.UserRepository;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/test")
    @ResponseBody
    public String hello() {
        return "Hello";
    }


    @PostMapping("/login")
    @ResponseBody
    public LoginResult loginApi(@RequestBody LoginRequest loginInfo) {
        Optional<User> user;
        user = userRepository.findByaccountid(loginInfo.id);
        return new LoginResult(true, "1234");
    }

    @Setter
    @Getter
    static class LoginRequest {
        private String id;
        private String password;
    }

    @Getter
    static class LoginResult {
        private boolean success;
        private String token;

        public LoginResult(boolean success, String token) {
            this.success = success;
            this.token = token;
        }
    }
}
