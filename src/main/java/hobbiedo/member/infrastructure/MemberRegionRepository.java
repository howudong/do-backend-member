package hobbiedo.member.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hobbiedo.member.domain.MemberRegion;
import io.swagger.v3.oas.annotations.Operation;

public interface MemberRegionRepository extends JpaRepository<MemberRegion, Long> {
	Optional<List<MemberRegion>> findByUuid(String uuid);
}
