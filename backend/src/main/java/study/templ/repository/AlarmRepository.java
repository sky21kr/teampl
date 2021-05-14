package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm,Integer> {
}
