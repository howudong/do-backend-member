package hobbiedo.global.base;

import hobbiedo.global.base.dto.ReasonErrorDto;

public interface BaseErrorCode {
	ReasonErrorDto getReason();

	ReasonErrorDto getReasonHttpStatus();
}
