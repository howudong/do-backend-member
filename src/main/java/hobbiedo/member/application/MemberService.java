package hobbiedo.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.MemberExceptionHandler;
import hobbiedo.member.converter.SignUpConverter;
import hobbiedo.member.domain.IntegrateAuth;
import hobbiedo.member.domain.Member;
import hobbiedo.member.dto.request.IntegrateSignUpDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import hobbiedo.member.vo.response.CheckLoginIdVO;
import hobbiedo.member.vo.response.SignUpVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public SignUpVO integrateSignUp(IntegrateSignUpDTO integrateSignUpDTO) {
		Member newMember = SignUpConverter.toEntity(integrateSignUpDTO);

		memberRepository.save(IntegrateAuth.builder()
			.loginId(integrateSignUpDTO.getLoginId())
			.password(passwordEncoder.encode(integrateSignUpDTO.getPassword()))
			.member(newMember)
			.build());

		return SignUpVO.builder()
			.uuid(newMember.getUuid())
			.build();
	}

	public CheckLoginIdVO isDuplicated(String loginId) {
		if (memberRepository.existsByLoginId(loginId)) {
			throw new MemberExceptionHandler(ErrorStatus.NOT_USE_LOGIN_ID);
		}

		return CheckLoginIdVO.builder()
			.isPossible(true)
			.build();
	}
}
