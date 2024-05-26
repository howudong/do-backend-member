package hobbiedo.email.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hobbiedo.email.application.EmailService;
import hobbiedo.email.converter.EmailAuthConverter;
import hobbiedo.email.vo.request.EmailAuthVO;
import hobbiedo.email.vo.request.EmailCheckVO;
import hobbiedo.global.ApiResponse;
import hobbiedo.global.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Email", description = "이메일 인증 관련 서비스입니다.")
public class EmailController {
	private final EmailService emailService;

	@PostMapping("/email/auth")
	@Operation(summary = "이메일 인증 코드 전송",
		description = "이메일 인증 코드를 해당 이메일로 전송합니다.")
	public ApiResponse<Void> sendEmailAuthApi(@RequestBody EmailAuthVO emailAuthVO) {
		emailService.sendAuthCode(EmailAuthConverter.toDTO(emailAuthVO));
		return ApiResponse.onSuccess(
			SuccessStatus.SEND_AUTH_MAIL_SUCCESS,
			null
		);
	}

	@GetMapping("/email/check")
	@Operation(summary = "이메일 인증 코드 일치 확인",
		description = "인증 코드가 일치하는지 확인합니다.")
	public ApiResponse<Boolean> checkEmailCode(
		@RequestParam(value = "email") String email,
		@RequestParam(value = "authCode") String authCode) {
		Boolean isMatch = emailService.checkAuthCode(email, authCode);
		return ApiResponse.onSuccess(
			isMatch ? SuccessStatus.EMAIL_AUTH_MATCH :
				SuccessStatus.EMAIL_AUTH_NOT_MATCH,
			isMatch
		);
	}
}
