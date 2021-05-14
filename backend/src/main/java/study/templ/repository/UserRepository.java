package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByAccountid(String id);
}
