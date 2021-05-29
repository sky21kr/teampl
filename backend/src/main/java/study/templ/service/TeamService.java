package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import study.templ.domain.*;
import org.springframework.stereotype.Service;
import study.templ.domain.Member;
import study.templ.domain.Team;
import study.templ.domain.TeamContentsForm;
import study.templ.domain.User;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.MemberRepository;
import study.templ.repository.TeamRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    @Autowired
    private MemberRepository memberRepository;
    //팀 만들기
    public Team createTeam(CreateTeamForm createTeamForm) {
        User user = userService.getUserById(createTeamForm.getOwner());

        Team team = new Team (createTeamForm.getCategory(), createTeamForm.getNumberofmembers(),
                createTeamForm.getLimit(), createTeamForm.getStatus(), createTeamForm.getTitle(),
                createTeamForm.getIntroduction(), LocalDateTime.now(), user);
        teamRepository.save(team);
        memberRepository.save(new Member(team.getTeamid(), user.getUserid(), team, user));
        return team;
    }

    //team_id로 팀 가져오기
    public Team getTeamById(int team_id){
        Optional<Team> isTeam = teamRepository.findById(team_id);
        if (isTeam.isEmpty())
            throw new EntityNotFoundException("team not found. check team id.");
        return isTeam.get();
    }

    //카테고리에 해당하는 팀 가져오기
    public Page<Team> getTeamByCategory(Pageable pageable, int category){
        return teamRepository.findByCategory(pageable, category);
    }
    //검색으로 title 에 해당 내용 있는 팀 가져오기
    public Page<Team> getTeamBySearch(Pageable pageable, String keyword){
        return teamRepository.findByTitleContains(pageable, keyword);
    }
    //모든 팀 가져오기
    public Page<Team> getTeam(Pageable pageable){ return teamRepository.findAll(pageable); }

    //team_id 팀의 멤버 가져오기
    @Transactional      //team fetch.lazy 이므로 team.members proxy -> proxy 초기화할 수 있도록 transaction 하나로
    public List<User> getMemberOfTeam(int team_id){
        Team team = getTeamById(team_id);
        List<Member> members = new ArrayList<Member>(team.getMembers());
        List<User> users = new ArrayList<>();
        for (Member m : members){
            users.add(m.getUser());
        }
        return users;

    }

    //팀 모집 글 내용 가져오기
    public TeamContentsForm getTeamContents(int team_id){
        Team team = getTeamById(team_id);

        TeamContentsForm contents = new TeamContentsForm(team.getCategory(),team.getDatetime(),team.getOwner().getNickname(),
                team.getStatus(),team.getTitle(),team.getIntroduction(),team.getOwncomments());
        return contents;
    }
    //사용자 owner이고 team 수정 (정보 주어지지 않을시 이전 값 그대로)
    public Team updateTeam(UpdateTeamForm updateTeamForm){
        int team_id = updateTeamForm.getTeamid();
        int owner = updateTeamForm.getOwner();
        Team team = getTeamById(team_id);                       //check team EntityNotFoundException

        userService.getUserById(updateTeamForm.getOwner());     //check user EntityNotFoundException
        if (team.getOwner().getUserid()!=owner)
            throw new IllegalArgumentException("user is not owner of team.");

        if (updateTeamForm.getStatus()!=null)
            team.setStatus(updateTeamForm.getStatus());
        if (updateTeamForm.getLimit()!=null)
            team.setLimit(updateTeamForm.getLimit());
        if (updateTeamForm.getNumberofmembers()!=null)
            team.setNumberofmembers(updateTeamForm.getNumberofmembers());
        if (updateTeamForm.getCategory()!=null)
            team.setCategory(updateTeamForm.getCategory());
        if (updateTeamForm.getTitle()!=null)
            team.setTitle(updateTeamForm.getTitle());
        if (updateTeamForm.getIntroduction()!=null)
            team.setIntroduction(updateTeamForm.getIntroduction());

        teamRepository.save(team);
        return team;
    }
    //사용자 owner이고 팀 삭제
    public void deleteTeamAsOwner(int owner, int team_id){
        Team team = getTeamById(team_id);

        if (team.getOwner().getUserid()!=owner)
            throw new IllegalArgumentException("user is not owner of team.");
        teamRepository.deleteById(team_id);
    }

    public Pageable createPageRequest(int pageNumber, int pageSize, String sortParameter, Boolean ascending){
        if (ascending)
            return PageRequest.of(pageNumber, pageSize, Sort.by(sortParameter).ascending());
        return PageRequest.of(pageNumber, pageSize, Sort.by(sortParameter).descending());
    }


    //저장소 내 모든 팀 삭제
    public void deleteAllTeam(){
        teamRepository.deleteAll();
    }
}
