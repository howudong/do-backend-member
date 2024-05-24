package hobbiedo.member.application;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.member.domain.IntegrateAuth;
import hobbiedo.member.domain.Member;
import hobbiedo.member.dto.request.IntegrateSignUpDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import hobbiedo.member.vo.response.ExistIdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public void integrateSignUp(IntegrateSignUpDTO integrateSignUpDTO) {
		Member newMember = Member.builder()
			.phoneNumber(integrateSignUpDTO.getPhoneNumber())
			.name(integrateSignUpDTO.getName())
			.uuid(UUID.randomUUID().toString())
			.email(integrateSignUpDTO.getEmail())
			.gender(integrateSignUpDTO.getGender())
			.birth(integrateSignUpDTO.getBirth())
			.build();

		memberRepository.save(IntegrateAuth.builder()
			.loginId(integrateSignUpDTO.getLoginId())
			.password(passwordEncoder.encode(integrateSignUpDTO.getPassword()))
			.member(newMember)
			.build());
	}

	public ExistIdVO isExist(String loginId) {
		Boolean isExist = memberRepository.existsByLoginId(loginId);
		return ExistIdVO.builder()
			.isPossible(!isExist)
			.build();
	}
}
