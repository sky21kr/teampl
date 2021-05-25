package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import study.templ.domain.Alarm;
import study.templ.domain.User;

import java.util.List;


public interface AlarmRepository extends JpaRepository<Alarm,Integer> {

     List<Alarm> findByTargetuser(int targetuser);


}
