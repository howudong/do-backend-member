package hobbiedo.member.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.ActiveMemberRegion;

public interface ActiveMemberRegionRepository extends JpaRepository<ActiveMemberRegion, Long> {
	Optional<ActiveMemberRegion> findByUuid(String uuid);
}
