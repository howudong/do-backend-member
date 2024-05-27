package hobbiedo.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.IntegrateAuth;

public interface MemberRepository extends JpaRepository<IntegrateAuth, Long> {
	Boolean existsByLoginId(String loginId);
}
