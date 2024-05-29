package hobbiedo.email.application;

import org.springframework.stereotype.Service;

import hobbiedo.email.domain.entity.EmailCode;
import hobbiedo.email.dto.request.EmailCheckDTO;
import hobbiedo.email.infrastructure.EmailRepository;
import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.MemberExceptionHandler;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailCheckService {
	private final EmailRepository emailRepository;

	public Boolean checkAuthCode(EmailCheckDTO emailCheckDTO) {
		EmailCode emailCode = emailRepository
			.findByEmail(emailCheckDTO.getEmail())
			.orElseThrow(() -> new MemberExceptionHandler(ErrorStatus.EMAIL_AUTH_NOT_MATCH));

		return emailCode.getAuthCode()
			.equals(emailCheckDTO.getAuthCode());
	}
}
