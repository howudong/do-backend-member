package hobbiedo.exception;

import hobbiedo.base.BaseErrorCode;
import hobbiedo.base.dto.ReasonErrorDto;
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
