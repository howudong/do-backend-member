package hobbiedo.member.converter;

import hobbiedo.member.dto.request.IntegrateSignUpDTO;
import hobbiedo.member.vo.request.IntegrateSignUpVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpConverter {
	public static IntegrateSignUpDTO toDTO(IntegrateSignUpVO signUpVO) {
		return IntegrateSignUpDTO.builder()
				.loginId(signUpVO.getLoginId())
				.name(signUpVO.getName())
				.email(signUpVO.getEmail())
				.phoneNumber(signUpVO.getPhoneNumber())
				.gender(signUpVO.getGender())
				.loginId(signUpVO.getLoginId())
				.password(signUpVO.getPassword())
				.birth(signUpVO.getBirth())
				.build();
	}
}
