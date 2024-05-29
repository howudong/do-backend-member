package hobbiedo.email.application;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.domain.entity.EmailCode;
import hobbiedo.email.dto.request.EmailAuthDTO;
import hobbiedo.email.infrastructure.EmailRepository;
import hobbiedo.email.type.MailType;
import hobbiedo.email.util.MailFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailCodeService implements EmailService {
	public static final int EMAIL_CODE_DIGIT = 4;
	private static final String DIGIT_SET_FORMAT = "%%0%dd";

	private final MailFormatter mailFormatter;
	private final EmailRepository emailRepository;

	@Override
	@Transactional
	public void sendMail(Object object) {
		EmailAuthDTO emailAuthDTO = getEmailAuthDTO(object);
		String emailCode = generate();
		mailFormatter.createMail(emailAuthDTO.getEmail(), MailType.EMAIL_CODE, emailCode);
		replaceNewCodeToRedis(emailAuthDTO.getEmail(), emailCode);
	}

	@Override
	public String generate() {
		Random random = new Random();
		int code = random.nextInt((int)Math.pow(10, EMAIL_CODE_DIGIT));
		String format = String.format(DIGIT_SET_FORMAT, EMAIL_CODE_DIGIT);
		return String.format(format, code);
	}

	private EmailAuthDTO getEmailAuthDTO(Object object) {
		EmailAuthDTO emailAuthDTO = null;
		if (object instanceof EmailAuthDTO) {
			emailAuthDTO = (EmailAuthDTO)object;
		}
		return emailAuthDTO;
	}

	private void replaceNewCodeToRedis(String email, String authCode) {
		emailRepository.deleteByEmail(email);
		emailRepository.save(EmailCode.builder()
			.email(email)
			.authCode(authCode)
			.build());
	}

}
