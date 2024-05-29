package hobbiedo.member.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hobbiedo.member.domain.IntegrateAuth;

public interface MemberRepository extends JpaRepository<IntegrateAuth, Long> {
	Boolean existsByLoginId(String loginId);

	Boolean existsByMember_Email(String email);

	Boolean existsByMember_PhoneNumber(String phoneNumber);

	@Query("SELECT i.loginId FROM IntegrateAuth i JOIN i.member m WHERE m.name = :name AND m.email = :email")
	Optional<String> findLoginIdByNameAndEmail(
		@Param("name") String name,
		@Param("email") String email);

	@Query("SELECT COUNT(i) > 0 FROM IntegrateAuth i JOIN i.member m "
		+ "WHERE m.name = :name AND m.email = :email AND i.loginId = :loginId")
	Boolean existPasswordBy(@Param("name") String name, @Param("email") String email, @Param("loginId") String loginId);
}
