package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
