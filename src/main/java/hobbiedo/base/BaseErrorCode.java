package hobbiedo.base;

import hobbiedo.base.dto.ReasonErrorDto;

public interface BaseErrorCode {
	ReasonErrorDto getReason();

	ReasonErrorDto getReasonHttpStatus();
}
