package study.templ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.templ.domain.User;
import study.templ.repository.UserRepository;

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
}