package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.Application;
import study.templ.domain.MemberId;

public interface ApplicationRepository extends JpaRepository<Application, MemberId> {
}
