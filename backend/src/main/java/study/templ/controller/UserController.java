package study.templ.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.repository.UserRepository;
import study.templ.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

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

    //내가 만든 팀 조회
    @GetMapping("/myteam")
    public List<Team> getMyTeam(@RequestParam("userid") int user_id){
        return userService.getTeamAsOwner(user_id);

    }
    @DeleteMapping("/user")
    public boolean deleteUser(@RequestParam("userid") int user_id){
        //authentication required!

        return userService.deleteUserById(user_id);
    }
}
