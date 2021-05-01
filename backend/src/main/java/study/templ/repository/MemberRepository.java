package study.templ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.templ.domain.Member;
import study.templ.domain.MemberId;

public interface MemberRepository extends JpaRepository<Member, MemberId> {
}
