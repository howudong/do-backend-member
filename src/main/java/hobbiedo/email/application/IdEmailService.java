package hobbiedo.email.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.type.MailType;
import hobbiedo.email.util.MailFormatter;
import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.MemberExceptionHandler;
import hobbiedo.member.dto.request.FindLoginIdDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service(value = "idEmailService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IdEmailService implements EmailService {
	private final MemberRepository memberRepository;
	private final MailFormatter mailFormatter;

	@Override
	public void sendMail(Object object) {
		FindLoginIdDTO findLoginIdDTO = getDTO(object);
		mailFormatter.sendMail(findLoginIdDTO.getEmail(), MailType.LOGIN_ID, getLoginId(findLoginIdDTO));
	}

	private FindLoginIdDTO getDTO(Object object) {
		FindLoginIdDTO findLoginIdDTO = null;
		if (object instanceof FindLoginIdDTO) {
			findLoginIdDTO = (FindLoginIdDTO)object;
		}
		return findLoginIdDTO;
	}

	private String getLoginId(FindLoginIdDTO findLoginIdDTO) {
		return memberRepository
			.findLoginIdByNameAndEmail(findLoginIdDTO.getName(), findLoginIdDTO.getEmail())
			.orElseThrow(() -> new MemberExceptionHandler(ErrorStatus.FIND_LOGIN_ID_FAIL));
	}
}
