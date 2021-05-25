package study.templ.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import study.templ.domain.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Page<Team> findByCategory(Pageable pageable, int category);
    Page<Team> findByTitleContains(Pageable pageable, String title);
}
