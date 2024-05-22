package hobbiedo.member.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.MemberRegion;

public interface MemberRegionRepository extends JpaRepository<MemberRegion, Long> {
	List<MemberRegion> findByUuid(String uuid);

}
