package study.templ.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.Member;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.repository.UserRepository;
import study.templ.service.UserService;

import java.util.HashMap;
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

    //사용자 생성
    @PostMapping("/sign-up")
    public Optional<User> createUser(@RequestBody HashMap<String,String> params){
        return userService.createUser(params.get("account_id"),params.get("password"),params.get("nickname"));
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
    @GetMapping("/memberteam")
    public List<Member> getMemberTeam(@RequestParam("userid") int user_id){
        return userService.getTeamAsMember(user_id);
    }
    //userid로 사용자 삭제
    @DeleteMapping("/user")
    public boolean deleteUser(@RequestParam("userid") int user_id){
        //authentication required!
        return userService.deleteUserById(user_id);
    }

    @PostMapping("/application")
    public boolean createApplication(@RequestBody HashMap<String, Object> params){
        return userService.createApplication((Integer)params.get("teamid"), (Integer)params.get("userid"), (String)params.get("contents"));
    }
    @PostMapping("/accept-application")
    public int acceptApplication(@RequestBody HashMap<String, Object> params){
        return userService.acceptApplication((Integer)params.get("owner"), (Integer)params.get("teamid"),
                (Integer)params.get("userid"), (Boolean)params.get("accept"));
    }
}
