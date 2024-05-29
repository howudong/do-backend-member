package hobbiedo.email.converter;

import hobbiedo.email.dto.request.EmailCheckDTO;
import hobbiedo.email.vo.request.EmailCheckVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailCheckConverter {
	public static EmailCheckDTO toDTO(EmailCheckVO emailCheckVO) {
		return EmailCheckDTO.builder()
			.email(emailCheckVO.getEmail())
			.authCode(emailCheckVO.getAuthCode())
			.build();
	}
}
