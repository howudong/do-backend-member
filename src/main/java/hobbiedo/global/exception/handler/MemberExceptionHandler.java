package hobbiedo.global.exception.handler;

import hobbiedo.global.code.BaseErrorCode;
import hobbiedo.global.exception.GeneralException;

public class MemberExceptionHandler extends GeneralException {
	public MemberExceptionHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}

