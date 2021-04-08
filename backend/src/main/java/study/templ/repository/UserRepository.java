package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
