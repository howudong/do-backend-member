package hobbiedo.global.exception.handler;

import hobbiedo.global.code.BaseErrorCode;
import hobbiedo.global.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleHandler extends GeneralException {
	public ExampleHandler(BaseErrorCode errorCode) {
		super(errorCode);
		log.error("ExampleHandler exception with error code: {}", errorCode.getReasonHttpStatus());
	}
}
