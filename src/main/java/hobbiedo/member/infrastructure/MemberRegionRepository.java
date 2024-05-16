package hobbiedo.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.MemberRegion;

public interface MemberRegionRepository extends JpaRepository<MemberRegion, Long> {

}
