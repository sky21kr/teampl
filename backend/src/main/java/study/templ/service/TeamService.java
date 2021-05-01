package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.templ.domain.Member;
import study.templ.domain.Team;
import study.templ.domain.TeamContentsForm;
import study.templ.domain.User;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationRepository applicationRepository;

    //팀 만들기
    public Optional<Team> createTeam(int category, int limit, int numberofmembers, Boolean status, String title, String introduction, String datetime, int owner) {
        Optional<User> isUser = userService.getUserById(owner);
        if (isUser.isEmpty())
            return Optional.empty();
        Team team = new Team(category, numberofmembers, limit, status, title, introduction, datetime, isUser.get());
        return Optional.of(teamRepository.save(team));
    }

    //team_id로 팀 가져오기
    public Optional<Team> getTeamById(int team_id){
        return teamRepository.findById(team_id);
    }

    //카테고리에 해당하는 팀 가져오기
    public List<Team> getTeamByCategory(int category){
        return teamRepository.findByCategory(category);
    }

    //모든 팀 가져오기
    public List<Team> getTeam(){ return teamRepository.findAll(); }

    //team_id 팀의 멤버 가져오기
    public List<Member> getMemberOfTeam(int team_id){
        Optional<Team> isTeam = teamRepository.findById(team_id);
        if (isTeam.isEmpty())
            return Collections.emptyList();
        if (isTeam.get().getMembers()==null)
            return Collections.emptyList();
        List<Member> members = new ArrayList<Member>(isTeam.get().getMembers());
        return members;

    }

    //팀 모집 글 내용 가져오기
    public Optional<TeamContentsForm> getTeamContents(int team_id){
        Optional<Team> isTeam = teamRepository.findById(team_id);
        if (isTeam.isEmpty())
            return Optional.empty();

        Team team = isTeam.get();
        TeamContentsForm contents = new TeamContentsForm(team.getCategory(),team.getDatetime(),team.getOwner().getNickname(),
                team.getStatus(),team.getTitle(),team.getIntroduction(),team.getOwncomments());
        return Optional.of(contents);
    }
    //사용자 owner이고 team 수정 (정보 주어지지 않을시 이전 값 그대로)
    public Optional<Team> updateTeam(int owner, int team_id, int category, int limit, String title, String introduction){
        Optional<Team> isTeam = teamRepository.findById(team_id);
        if (isTeam.isEmpty())
            return isTeam;
        else if (isTeam.get().getOwner().getUserid()!=owner)
            return Optional.empty();
        Team team = isTeam.get();
        if (title!=null)
            team.setTitle(title);
        if (introduction!=null)
            team.setTitle(title);
        if (limit!=0)
            team.setLimit(limit);
        if (category!=100)
            team.setTitle(title);

        teamRepository.save(team);
        return Optional.ofNullable(team);
    }
    //사용자 owner이고 팀 삭제
    //Member :: 삭제하면서 포함되어있는 유저들에서 팀 정보도 삭제 구현 해야함
    public boolean deleteTeamAsOwner(int owner, int team_id){
        Optional<Team> teamToDelete = teamRepository.findById(team_id);
        if (teamToDelete.isEmpty())
            return false;
        if (teamToDelete.get().getOwner().getUserid()!=owner)
            return false;
        teamRepository.deleteById(team_id);
        return true;
    }

    //저장소 내 모든 팀 삭제
    public void deleteAllTeam(){
        teamRepository.deleteAll();
    }

}
