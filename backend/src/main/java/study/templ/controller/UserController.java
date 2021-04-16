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
import study.templ.service.UserService;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping("/test1")
    @ResponseBody
    public String hello1() {
        return "Hello1";
    }

    @GetMapping("/test2")
    @ResponseBody
    public String hello2() {
        return "Hello2";
    }

    @GetMapping("/test3")
    @ResponseBody
    public String hello3() {
        return "Hello3";
    }


    @PostMapping("/login")
    @ResponseBody
    public LoginResult loginApi(@RequestBody LoginRequest loginInfo) {
        User user = userService.login(loginInfo.getId(), loginInfo.getPassword());
        return new LoginResult(true, user.getToken(), user.getUserid());
    }

    @PostMapping("/signup")
    @ResponseBody
    public SignupResponse signupApi(@RequestBody SignupRequest signupInfo) {
        userService.createUser(signupInfo.getId(), signupInfo.getPassword(), signupInfo.getNickname());
        return new SignupResponse(true);
    }


    @Setter
    @Getter
    static class SignupRequest {
        private String id;
        private String password;
        private String nickname;
    }

    @Getter
    static class SignupResponse {
        private boolean success;

        public SignupResponse(boolean success) {
            this.success = success;
        }
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
        private int userId;

        public LoginResult(boolean success, String token, int userId) {
            this.success = success;
            this.token = token;
            this.userId = userId;
        }
    }
}
