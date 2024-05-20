package hobbiedo.global.exception;

import hobbiedo.global.base.BaseErrorCode;
import hobbiedo.global.base.dto.ReasonErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
	private BaseErrorCode errorCode;

	public ReasonErrorDto getErrorReason() {
		return errorCode.getReason();
	}

	public ReasonErrorDto getErrorReasonHttpStatus() {
		return errorCode.getReasonHttpStatus();
	}
}
