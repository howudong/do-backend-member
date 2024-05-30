package hobbiedo.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.email.util.RandomPasswordUtil;
import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.MemberExceptionHandler;
import hobbiedo.member.converter.SignUpConverter;
import hobbiedo.member.domain.IntegrateAuth;
import hobbiedo.member.domain.Member;
import hobbiedo.member.dto.request.IntegrateSignUpDTO;
import hobbiedo.member.dto.request.ResetPasswordDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import hobbiedo.member.vo.response.CheckLoginIdVO;
import hobbiedo.member.vo.response.SignUpVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	public static final int MAX_PASSWORD_LENGTH = 20;

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public SignUpVO integrateSignUp(IntegrateSignUpDTO integrateSignUpDTO) {
		validateLoginId(integrateSignUpDTO);
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
			throw new MemberExceptionHandler(ErrorStatus.ALREADY_USE_LOGIN_ID);
		}

		return CheckLoginIdVO.builder()
			.isPossible(true)
			.build();
	}

	@Transactional
	public hobbiedo.member.dto.response.ResetPasswordDTO resetPassword(ResetPasswordDTO resetPasswordDTO) {
		validatePassword(resetPasswordDTO);
		return hobbiedo.member.dto.response.ResetPasswordDTO.builder()
			.email(resetPasswordDTO.getEmail())
			.tempPassword(updatePassword(resetPasswordDTO))
			.build();
	}

	private void validatePassword(ResetPasswordDTO resetPasswordDTO) {
		Boolean isExist = memberRepository.existPasswordBy(
			resetPasswordDTO.getName(),
			resetPasswordDTO.getEmail(),
			resetPasswordDTO.getLoginId());
		if (!isExist) {
			throw new MemberExceptionHandler(ErrorStatus.RESET_PASSWORD_FAIL);
		}
	}

	private String updatePassword(ResetPasswordDTO resetPasswordDTO) {
		String tempPassword = RandomPasswordUtil.generate(MAX_PASSWORD_LENGTH);
		String password = passwordEncoder.encode(tempPassword);

		memberRepository.updatePasswordByLoginId(password, resetPasswordDTO.getLoginId());
		return tempPassword;
	}

	private void validateLoginId(IntegrateSignUpDTO integrateSignUpDTO) {
		if (memberRepository.existsByMember_Email(integrateSignUpDTO.getEmail())) {
			throw new MemberExceptionHandler(ErrorStatus.ALREADY_USE_EMAIL);
		}
		if (memberRepository.existsByMember_PhoneNumber(integrateSignUpDTO.getPhoneNumber())) {
			throw new MemberExceptionHandler(ErrorStatus.ALREADY_USE_PHONE_NUMBER);
		}
	}
}
