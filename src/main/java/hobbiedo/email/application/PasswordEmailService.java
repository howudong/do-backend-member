package hobbiedo.email.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.type.MailType;
import hobbiedo.email.util.MailFormatter;
import hobbiedo.email.util.RandomPasswordUtil;
import hobbiedo.member.dto.request.FindPasswordDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service(value = "passwordEmailService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PasswordEmailService implements EmailService {
	public static final int MAX_PASSWORD_LENGTH = 20;

	private final MailFormatter mailFormatter;
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void sendMail(Object object) {
		FindPasswordDTO findPasswordDTO = getDTO(object);
		String tempPassword = RandomPasswordUtil.generate(MAX_PASSWORD_LENGTH);

		send(tempPassword, findPasswordDTO);
		updatePassword(findPasswordDTO, tempPassword);
	}

	private void updatePassword(FindPasswordDTO findPasswordDTO, String tempPassword) {
		String password = passwordEncoder.encode(tempPassword);
		memberRepository.updatePasswordByLoginId(password, findPasswordDTO.getLoginId());
	}

	private FindPasswordDTO getDTO(Object object) {
		FindPasswordDTO findPasswordDTO = null;
		if (object instanceof FindPasswordDTO) {
			findPasswordDTO = (FindPasswordDTO)object;
		}
		return findPasswordDTO;
	}

	private void send(String tempPassword, FindPasswordDTO findPasswordDTO) {
		mailFormatter.sendMail(findPasswordDTO.getEmail(), MailType.TEMP_PASSWORD, tempPassword);
	}
}
