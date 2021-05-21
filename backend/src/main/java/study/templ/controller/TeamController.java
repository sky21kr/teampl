package study.templ.controller;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.templ.domain.*;
import study.templ.service.TeamService;

import java.time.LocalDateTime;
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
    public ResponseEntity<Team> createTeam(@RequestBody CreateTeamForm createTeamForm){
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(createTeamForm));
    }
    //조회
    //request parameter에 따라 다른 함수 호출하도록 최종 구현
    // 최신순으로 조회
    @GetMapping("team")
    public ResponseEntity<Page<Team>> getTeam(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                              @RequestParam(name = "ascending",required = false,defaultValue = "false") boolean ascending){
        Pageable pageable;
        if (ascending==true)
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", true);
        else
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", false);
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeam(pageable));
    }
    //카테고리로 조회
    @GetMapping("team/category")
    public ResponseEntity<Page<Team>> getTeamByCategory(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("category") int category,
                                        @RequestParam(name = "ascending",required = false,defaultValue = "false") boolean ascending){
        Pageable pageable;
        if (ascending==true)
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", true);
        else
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", false);
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeamByCategory(pageable, category));
    }
    //검색어로 조회
    @GetMapping("team/search")
    public ResponseEntity<Page<Team>> getTeamBySearch(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("keyword") String keyword,
                                      @RequestParam(name = "ascending",required = false,defaultValue = "false") boolean ascending){
        Pageable pageable;
        if (ascending==true)
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", true);
        else
            pageable = teamService.createPageRequest(pageNumber, pageSize, "datetime", false);
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeamBySearch(pageable, keyword));
    }
    //글 조회
    @GetMapping("teamcontents")
    public ResponseEntity<TeamContentsForm> getTeamContents(@RequestParam("teamid") int team_id){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getTeamContents(team_id));
    }
    @GetMapping("teammember")
    public ResponseEntity<List<Member>> getTeamMember(@RequestParam("teamid") int team_id){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.getMemberOfTeam(team_id));
    }

    //수정
    //팀 정보 수정
    @PutMapping("team")
    public ResponseEntity<Team> updateTeam(@RequestBody UpdateTeamForm updateTeamForm) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.updateTeam(updateTeamForm));
    }

    //삭제
    //팀 삭제
    @DeleteMapping("team")
    public ResponseEntity<Void> deleteTeamAsOwner(@RequestParam("userid") int owner, @RequestParam("teamid") int teamid){
        teamService.deleteTeamAsOwner(owner, teamid);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
