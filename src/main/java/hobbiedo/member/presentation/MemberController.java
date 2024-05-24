package hobbiedo.member.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.global.ApiResponse;
import hobbiedo.global.code.status.SuccessStatus;
import hobbiedo.member.application.MemberService;
import hobbiedo.member.converter.SignUpConverter;
import hobbiedo.member.vo.request.IntegrateSignUpVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Member", description = "사용자 정보 관련 API 입니다.")
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/sign-up/integration")
	@Operation(summary = "통합 회원가입",
			description = "통합 회원가입을 진행하여 새로운 회원을 등록합니다.")
	public ApiResponse<Void> integrationSignUpApi(@RequestBody @Valid IntegrateSignUpVO signUpVO) {
		memberService.integrateSignUp(SignUpConverter.toDTO(signUpVO));
		return ApiResponse.onSuccess(
				SuccessStatus.INTEGRATE_SIGN_UP_SUCCESS,
				null
		);
	}
}
