package study.templ.controller;

import io.swagger.models.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.*;
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

    //사용자 생성
    @PostMapping("/sign-up")
    public User createUser(@RequestBody HashMap<String,String> params){
        return userService.createUser(params.get("account_id"),params.get("password"),params.get("nickname"));
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

    //내가 만든 팀 조회
    @GetMapping("/myteam")
    public ResponseEntity<List<Team>> getMyTeam(@RequestParam("userid") int user_id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTeamAsOwner(user_id));
    }
    @GetMapping("/memberteam")
    public ResponseEntity<List<Member>> getMemberTeam(@RequestParam("userid") int user_id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTeamAsMember(user_id));
    }
    //userid로 사용자 삭제
    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestParam("userid") int user_id){
        //authentication required!
        userService.deleteUserById(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //가입 신청하기
    @PostMapping("/application")
    public ResponseEntity<Void> createApplication(@RequestBody CreateApplicationForm createApplicationForm){
        userService.createApplication(createApplicationForm);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //owner가 가입 수락/거절하기 request id == owner id 여야 함
    @PostMapping("/accept-application")
    public ResponseEntity<Void> acceptApplication(@RequestBody AcceptApplicationForm acceptApplicationForm){
        userService.acceptApplication(acceptApplicationForm);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //팀 탈퇴 request_id = user_id or team_id의 team owner_id
    @DeleteMapping("member")
    public ResponseEntity<Void> deleteTeamAsMember(@RequestParam("userid") int user_id, @RequestParam("teamid") int team_id, @RequestParam("requestid") int request_id){
        userService.deleteMember(user_id, team_id, request_id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
