package hobbiedo.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.ActiveMemberRegion;

public interface ActiveMemberRegionRepository extends JpaRepository<ActiveMemberRegion, Long> {

}
