package hobbiedo.member.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.global.ApiResponse;
import hobbiedo.global.code.status.SuccessStatus;
import hobbiedo.member.application.MemberService;
import hobbiedo.member.converter.SignUpConverter;
import hobbiedo.member.vo.request.IntegrateSignUpVO;
import hobbiedo.member.vo.response.CheckLoginIdVO;
import hobbiedo.member.vo.response.SignUpVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/non-users")
@RequiredArgsConstructor
@Tag(name = "Member", description = "사용자 정보 관련 API 입니다.")
public class MemberController {
	public static final String LOGIN_ID_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";

	private final MemberService memberService;

	@PostMapping("/sign-up")
	@Operation(summary = "통합 회원가입",
		description = "통합 회원가입을 진행하여 새로운 회원을 등록합니다.")
	public ApiResponse<SignUpVO> integrationSignUpApi(@RequestBody @Valid IntegrateSignUpVO signUpVO) {
		return ApiResponse.onSuccess(
			SuccessStatus.INTEGRATE_SIGN_UP_SUCCESS,
			memberService.integrateSignUp(SignUpConverter.toDTO(signUpVO))
		);
	}

	@GetMapping("/duplication")
	@Operation(summary = "아이디 중복 확인",
		description = "해당 아이디가 중복인지 확인합니다. 사용 가능하면 true, 그렇지 않으면 false를 반환합니다.")
	public ApiResponse<CheckLoginIdVO> checkIdApi(
		@RequestParam("loginId")
		@Pattern(regexp = LOGIN_ID_PATTERN,
			message = "아이디는 8~20자리의 영어+숫자로만 이뤄져야합니다.(특수 문자x)") String loginId) {
		return ApiResponse.onSuccess(
			SuccessStatus.CAN_USE_LOGIN_ID,
			memberService.isDuplicated(loginId)
		);
	}
}
