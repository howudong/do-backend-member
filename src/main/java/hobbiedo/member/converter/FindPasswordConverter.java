package hobbiedo.member.converter;

import hobbiedo.member.dto.request.FindPasswordDTO;
import hobbiedo.member.vo.request.FindPasswordVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindPasswordConverter {
	public static FindPasswordDTO toDTO(FindPasswordVO findPasswordVO) {
		return FindPasswordDTO.builder()
			.name(findPasswordVO.getName())
			.email(findPasswordVO.getEmail())
			.loginId(findPasswordVO.getLoginId())
			.build();
	}
}
