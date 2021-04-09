package study.templ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.Team;
import study.templ.service.TeamService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TeamController {
    @Autowired
    private TeamService teamService;
    //생성
    //팀 생성
    @PostMapping("make-team")
    public Optional<Team> createTeam(@RequestBody HashMap<String, Object> param){
        if (param.get("category")==null||param.get("limit")==null||param.get("numberofmembers")==null|| param.get("status")==null ||
                param.get("title")==null|| param.get("introduction")==null|| param.get("datetime")==null || param.get("userid")==null)
            return Optional.empty();

        return Optional.ofNullable(teamService.createTeam(
                (int)param.get("category"), (int)param.get("limit"), (int)param.get("numberofmembers"),
                (boolean)param.get("status"), (String) param.get("title"), (String) param.get("introduction"),
                (String) param.get("datetime"), (int) param.get("userid")));
    }
    //조회
    //request parameter에 따라 다른 함수 호출하도록 최종 구현
    // 최신순으로 조회
    @GetMapping("team")
    public List<Team> getTeam(){
        return teamService.getTeam();
    }
    //카테고리로 조회
    @GetMapping("team/category")
    public List<Team> getTeamByCategory(@RequestParam("category") int category){
        return teamService.getTeamByCategory(category);
    }
    //내가 만든 팀 조회
    @GetMapping("team/owner")
    public List<Team> getTeamAsOwner(@RequestParam("owner") int owner){
        return  teamService.getTeamAsOwner(owner);
    }
    //검색어로 조회
    @GetMapping
    public void getTeamBySearch(){

    }
    //글 조회
    @GetMapping("teamcontents")
    public void getTeamContents(){

    }

    //수정
    //팀 정보 수정
    @PutMapping("team")
    public boolean updateTeam(@RequestBody HashMap<String, Object> param) {
        if (param.get("userid")==null||param.get("teamid")==null)
            return false;

        int category, limit;
        String title = null, introduction = null;
        if (param.get("category")==null) category = 100; else category = (int)param.get("category");
        if (param.get("limit")==null) limit = 0; else limit = (int)param.get("limit");
        if (param.get("title")!=null) title = (String) param.get("title");
        if (param.get("introcution")!=null) introduction = (String) param.get("introduction");

        teamService.updateTeam((int)param.get("userid"), (int)param.get("teamid"), category, limit, title, introduction);
        return true;
    }

    //삭제
    //팀 삭제
    @DeleteMapping("team")
    public boolean deleteTeamAsOwner(@RequestParam("userid") int owner, @RequestParam("teamid") int teamid){
        return teamService.deleteTeamAsOwner(owner, teamid);
    }

    /*
    //가입 신청
    @PostMapping("/join")
    public void joinTeam(){

    }
    //가입 수락/거절
    @PostMapping("/accept")
    public void acceptJoinTeam(){

    }

    //내가 가입한 팀 조회
    @GetMapping
    public void getTeamAsMember(){

    }

    //팀 탈퇴
    @DeleteMapping
    public void deleteTeamAsMember(){

    }
    */


}
