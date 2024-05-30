package hobbiedo.email.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.type.MailType;
import hobbiedo.email.util.MailFormatter;
import hobbiedo.member.dto.response.ResetPasswordDTO;
import lombok.RequiredArgsConstructor;

@Service(value = "passwordEmailService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PasswordEmailService implements EmailService {

	private final MailFormatter mailFormatter;

	@Override
	@Transactional
	public void sendMail(Object object) {
		ResetPasswordDTO resetPasswordDTO = getDTO(object);
		send(resetPasswordDTO);
	}

	private ResetPasswordDTO getDTO(Object object) {
		ResetPasswordDTO resetPasswordDTO = null;
		if (object instanceof ResetPasswordDTO) {
			resetPasswordDTO = (ResetPasswordDTO)object;
		}
		return resetPasswordDTO;
	}

	private void send(ResetPasswordDTO resetPasswordDTO) {
		mailFormatter.sendMail(
			resetPasswordDTO.getEmail(),
			MailType.TEMP_PASSWORD,
			resetPasswordDTO.getTempPassword());
	}
}
