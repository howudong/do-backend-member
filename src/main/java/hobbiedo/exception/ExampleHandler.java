package hobbiedo.exception;

import hobbiedo.base.BaseErrorCode;

public class ExampleHandler extends GeneralException {
	public ExampleHandler(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
