package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
