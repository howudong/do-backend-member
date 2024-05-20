package hobbiedo.global.exception;

import hobbiedo.global.base.BaseErrorCode;

public class ExampleHandler extends GeneralException {
	public ExampleHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
