package hobbiedo.email.infrastructure;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import hobbiedo.email.domain.EmailCode;

public interface EmailRepository extends CrudRepository<EmailCode, String> {
	void deleteByEmail(String email);

	Optional<EmailCode> findByEmail(String email);
}
