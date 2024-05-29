package hobbiedo.email.converter;

import hobbiedo.email.dto.request.EmailAuthDTO;
import hobbiedo.email.vo.request.EmailAuthVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAuthConverter {
	public static EmailAuthDTO toDTO(EmailAuthVO emailAuthVO) {
		return EmailAuthDTO.builder()
			.email(emailAuthVO.getEmail())
			.build();
	}
}
