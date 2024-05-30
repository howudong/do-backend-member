package hobbiedo.member.converter;

import hobbiedo.member.dto.request.ResetPasswordDTO;
import hobbiedo.member.vo.request.ResetPasswordVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResetPasswordConverter {
	public static ResetPasswordDTO toDTO(ResetPasswordVO resetPasswordVO) {
		return ResetPasswordDTO.builder()
			.name(resetPasswordVO.getName())
			.email(resetPasswordVO.getEmail())
			.loginId(resetPasswordVO.getLoginId())
			.build();
	}
}
