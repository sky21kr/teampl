package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.templ.domain.*;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.MemberRepository;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.infra.JwtUtil;
import study.templ.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AlarmService alarmService;
    @Autowired
    TeamService teamService;
    @Autowired
    JwtUtil jwtUtil;

    public User login(String account_id, String password) {
        User user = userRepository.findByAccountid(account_id)
                .orElseThrow(() -> new IllegalArgumentException("id wrong"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("password wrong");
        }

        user.setToken(jwtUtil.createToken());
        userRepository.save(user);

        return user;
    }

    //사용자 생성
    public User createUser(String account_id, String password, String nickname){
        User user = new User(account_id, password, nickname);
        return userRepository.save(user);
    }

    //user_id로 사용자 조회
    public User getUserById(int user_id){
        Optional<User> isUser = userRepository.findById(user_id);
        if (isUser.isEmpty())
            throw new EntityNotFoundException("user not found. check user id.");
        return isUser.get();
    }

    //user_id가 member인 팀 가져오기
    @Transactional
    public List<Team> getTeamAsMember(int user_id){
        User user = getUserById(user_id);

        List<Member> members = new ArrayList<Member>(user.getMemberteams());
        List<Team> teams = new ArrayList<>();
        for (Member m: members){
            teams.add(m.getTeam());
        }
        return teams;
    }

    //user_id가 owner인 팀 가져오기
    @Transactional
    public List<Team> getTeamAsOwner(int owner){
        User user = getUserById(owner);

        if (user.getOwnteams().size()==0)
            return Collections.emptyList();
        return user.getOwnteams();
    }

    //가입신청
    public void createApplication(CreateApplicationForm createApplicationForm){
        Team team = teamService.getTeamById(createApplicationForm.getTeamid());
        User user= getUserById(createApplicationForm.getUserid());

        Application application = new Application(createApplicationForm.getTeamid(), createApplicationForm.getUserid(),
                createApplicationForm.getContents(), team, user);
        applicationRepository.save(application);
        alarmService.sendMessage2(createApplicationForm.getUserid(), createApplicationForm.getTeamid());
    }
    //가입신청 수락/거절
    public void acceptApplication(AcceptApplicationForm acceptApplicationForm){
        //::team의 owner만 가입신청 수락/거절 가능
        Team team = teamService.getTeamById(acceptApplicationForm.getTeamid());
        User user = getUserById(acceptApplicationForm.getUserid());
        User req_user = getUserById(acceptApplicationForm.getRequestid());
        int team_id = team.getTeamid();
        int user_id = user.getUserid();
        int req_user_id = req_user.getUserid();

        if (!team.getOwner().getUserid().equals(req_user_id))
            throw new IllegalArgumentException("request user is not owner of team.");
        Optional<Application> isApplication = applicationRepository.findById(new MemberId(team_id, user_id));
        if (isApplication.isEmpty())
            throw new EntityNotFoundException("application not found. check team id and user id.");

        if (acceptApplicationForm.getAccept()) {
            MemberId memberId = new MemberId(team_id, user_id);
            applicationRepository.deleteById(memberId);
            memberRepository.save(new Member(team_id,user_id, team, user));
            alarmService.sendMessage3(acceptApplicationForm.getUserid(), acceptApplicationForm.getTeamid());
        }
        else {
            applicationRepository.deleteById(new MemberId(team_id,user_id));
            alarmService.sendMessage3(acceptApplicationForm.getUserid(), acceptApplicationForm.getTeamid());
        }
    }
    //user_id로 사용자 삭제
    public void deleteUserById(int user_id){
        User user = getUserById(user_id);

        userRepository.delete(user);
    }
    //user_id, team_id로 Member 삭제
    public void deleteMember(int user_id, int team_id, int request_id){
        Team team = teamService.getTeamById(team_id);
        User member_user = getUserById(user_id);
        try {
            User request_user = getUserById(request_id);
        } catch (Exception e){
            throw new EntityNotFoundException("request user not found. check request id.");
        }

        if (member_user.getUserid()!=request_id&&team.getOwner().getUserid()!=request_id)
            throw new IllegalArgumentException("requesting user must be team owner or member to be deleted.");

        Optional<Member> isMember = memberRepository.findById(new MemberId(team_id, user_id));
        if (isMember.isEmpty())
            throw new EntityNotFoundException("member not found. check team id and user id.");
        memberRepository.delete(isMember.get());
    }

    //모든 사용자 삭제
    public void deleteAllUser(){//테스트용 팀 테이블 초기화
        userRepository.deleteAll();
    }
}

