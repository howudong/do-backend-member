package hobbiedo.email.application;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.domain.EmailCode;
import hobbiedo.email.infrastructure.EmailRepository;
import hobbiedo.email.util.CodeGeneratorUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailService {
	public static final int EMAIL_CODE_DIGIT = 4;
	public static final String EMAIL_HTLM_FORMAT = """
		   <h3> [취미한다] 이메일 인증 코드입니다. </h3>
		   <h1> %s </h1>
		   <h3> 서비스를 이용해주셔서 감사합니다!! </h3>
		""";

	private final JavaMailSender javaMailSender;
	private final EmailRepository emailRepository;

	@Value("${spring.mail.username}")
	private String senderEmail;

	@Transactional
	public void sendAuthCode(String email) {
		String authCode = CodeGeneratorUtil.generate(EMAIL_CODE_DIGIT);
		MimeMessage mail = createMail(email, authCode);

		javaMailSender.send(mail);
		replaceNewCodeToRedis(email, authCode);
	}

	public Boolean checkAuthCode(String email, String authCode) {
		EmailCode emailCode = emailRepository.findByEmail(email)
			.orElseThrow(NoSuchElementException::new);

		return emailCode.getAuthCode()
			.equals(authCode);
	}

	private void replaceNewCodeToRedis(String email, String authCode) {
		emailRepository.deleteByEmail(email);
		emailRepository.save(EmailCode.builder()
			.email(email)
			.authCode(authCode)
			.build());
	}

	private MimeMessage createMail(String email, String authCode) {
		MimeMessage message = javaMailSender.createMimeMessage();

		String title = "취미한다 이메일 인증 코드 발송";
		String text = EMAIL_HTLM_FORMAT.formatted(authCode);

		try {
			message.setFrom(senderEmail);
			message.setRecipients(MimeMessage.RecipientType.TO, email);
			message.setSubject(title);
			message.setText(text, "UTF-8", "html");
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return message;
	}
}
