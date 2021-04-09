package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.templ.domain.Team;
import study.templ.domain.User;
import study.templ.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamService teamService;
    //사용자 생성
    public User createUser(String account_id, String password, String nickname){
        User user = new User(account_id, password, nickname);
        return userRepository.save(user);
    }

    //user_id로 사용자 조회
    public Optional<User> getUserById(int user_id){
        return userRepository.findById(user_id);
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
