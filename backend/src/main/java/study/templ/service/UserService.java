package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.templ.domain.*;
import study.templ.repository.ApplicationRepository;
import study.templ.repository.MemberRepository;
import study.templ.repository.UserRepository;

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
    TeamService teamService;
    
    //사용자 생성
    public Optional<User> createUser(String account_id, String password, String nickname){
        User user = new User(account_id, password, nickname);
        return Optional.of(userRepository.save(user));
    }

    //user_id로 사용자 조회
    public Optional<User> getUserById(int user_id){
        return userRepository.findById(user_id);
    }

    //user_id가 member인 팀 가져오기
    public List<Member> getTeamAsMember(int user_id){
        Optional<User> isUser = userRepository.findById(user_id);
        if (isUser.isEmpty())
            return Collections.emptyList();
        if (isUser.get().getMemberteams()==null)
            return Collections.emptyList();
        List<Member> teams = new ArrayList<Member>(isUser.get().getMemberteams());
        return teams;
    }

    //user_id가 owner인 팀 가져오기
    public List<Team> getTeamAsOwner(int owner){
        Optional<User> user = userRepository.findById(owner);
        if (user.isEmpty())
            return Collections.emptyList();
        return user.get().getOwnteams();
    }

    //가입신청
    public boolean createApplication(int team_id, int user_id, String contents){
        //::user_id==자신 id
        Optional<Team> isTeam = teamService.getTeamById(team_id);
        Optional<User> isUser = getUserById(user_id);
        if (isTeam.isEmpty())
            return false;
        Team team = isTeam.get();

        if (isUser.isEmpty())
            return false;
        Application application = new Application(team_id, user_id, contents, isTeam.get(), isUser.get());
        applicationRepository.save(application);
        return true;
    }
    //가입신청 수락/거절
    public int acceptApplication(int owner, int team_id, int user_id, boolean accept){
        //::team의 owner만 가입신청 수락/거절 가능
        Optional<Team> isTeam = teamService.getTeamById(team_id);
        Optional<User> isUser = getUserById(user_id);
        if (isTeam.isEmpty())
            return -1;
        if (isUser.isEmpty())
            return -1;
        if (isTeam.get().getOwner().getUserid()!=owner)
            return -1;
        Optional<Application> isApplication = applicationRepository.findById(new MemberId(team_id,user_id));
        if (isApplication.isEmpty())
            return -1;
        if (accept) {
            applicationRepository.deleteById(new MemberId(team_id, user_id));
            memberRepository.save(new Member(team_id, user_id, isTeam.get(), isUser.get()));
            return 1;
        }
        else {
            applicationRepository.deleteById(new MemberId(team_id,user_id));
            return 0;
        }
    }
    //user_id로 사용자 삭제
    public boolean deleteUserById(int user_id){
        Optional<User> isUser = userRepository.findById(user_id);
        if (isUser.isEmpty())
            return false;
        else{
            userRepository.delete(isUser.get());
            return true;
        }
    }

    //모든 사용자 삭제
    public void deleteAllUser(){//테스트용 팀 테이블 초기화
        userRepository.deleteAll();
    }
}

