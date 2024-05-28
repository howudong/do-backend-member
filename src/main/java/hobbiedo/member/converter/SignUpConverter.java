package hobbiedo.member.converter;

import java.util.UUID;

import hobbiedo.member.domain.Member;
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

	public static Member toEntity(IntegrateSignUpDTO integrateSignUpDTO) {
		return Member.builder()
			.phoneNumber(integrateSignUpDTO.getPhoneNumber())
			.name(integrateSignUpDTO.getName())
			.uuid(UUID.randomUUID().toString())
			.email(integrateSignUpDTO.getEmail())
			.gender(integrateSignUpDTO.getGender())
			.birth(integrateSignUpDTO.getBirth())
			.build();
	}
}
