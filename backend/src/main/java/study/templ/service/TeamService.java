package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import study.templ.domain.*;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.MemberRepository;
import study.templ.repository.TeamRepository;

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
    public Optional<Team> createTeam(CreateTeamForm createTeamForm) {
        Optional<User> isUser = userService.getUserById(createTeamForm.getOwner());
        if (isUser.isEmpty())
            return Optional.empty();
        Team team = new Team (createTeamForm.getCategory(), createTeamForm.getNumberofmembers(),
                createTeamForm.getLimit(), createTeamForm.getStatus(), createTeamForm.getTitle(),
                createTeamForm.getIntroduction(), LocalDateTime.now(), isUser.get());
        teamRepository.save(team);
        memberRepository.save(new Member(team.getTeamid(), isUser.get().getUserid(), team, isUser.get()));
        return Optional.of(teamRepository.save(team));
    }

    //team_id로 팀 가져오기
    public Optional<Team> getTeamById(int team_id){
        return teamRepository.findById(team_id);
    }

    //카테고리에 해당하는 팀 가져오기
    public Page<Team> getTeamByCategory(Pageable pageable, int category){
        return teamRepository.findByCategory(pageable, category);
    }
    //검색으로 title에 해당 내용 있는 팀 가져오기
    public Page<Team> getTeamBySearch(Pageable pageable, String keyword){
        return teamRepository.findByTitleContains(pageable, keyword);
    }
    //모든 팀 가져오기
    public Page<Team> getTeam(Pageable pageable){ return teamRepository.findAll(pageable); }

    //team_id 팀의 멤버 가져오기
    @Transactional
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
    public Optional<Team> updateTeam(UpdateTeamForm updateTeamForm){
        int team_id = updateTeamForm.getTeamid();
        int owner = updateTeamForm.getOwner();
        Optional<Team> isTeam = teamRepository.findById(team_id);
        if (isTeam.isEmpty())
            return isTeam;
        else if (isTeam.get().getOwner().getUserid()!=owner)
            return Optional.empty();
        Team team = isTeam.get();
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
        return Optional.ofNullable(team);
    }
    //사용자 owner이고 팀 삭제
    public boolean deleteTeamAsOwner(int owner, int team_id){
        Optional<Team> teamToDelete = teamRepository.findById(team_id);
        if (teamToDelete.isEmpty())
            return false;
        if (teamToDelete.get().getOwner().getUserid()!=owner)
            return false;
        teamRepository.deleteById(team_id);
        return true;
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
