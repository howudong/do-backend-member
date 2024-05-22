package hobbiedo.global.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import hobbiedo.global.ApiResponse;
import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.dto.ErrorReasonDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		log.info("Handling ConstraintViolationException");
		String errorMessage = ex.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage)
				.findFirst()
				.orElse("ConstraintViolation extraction error");

		return handleExceptionInternalConstraint(ex, errorMessage, HttpHeaders.EMPTY, request);
	}

	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<Object> handleCustomException(GeneralException ex, WebRequest request) {
		log.info("Handling GeneralException: {}", ex.getErrorReasonHttpStatus());
		ErrorReasonDto errorReasonDto = ex.getErrorReasonHttpStatus();
		ApiResponse<Object> body = ApiResponse.onFailure(errorReasonDto.getCode(),
				errorReasonDto.getMessage(), null);
		return toResponseEntity(ex, HttpHeaders.EMPTY, request, errorReasonDto.getHttpStatus(),
				body);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		log.info("Handling MethodArgumentNotValidException");
		ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);

		ApiResponse<Object> body = ApiResponse.onFailure(objectError.getCode(),
				objectError.getDefaultMessage(), null);

		return toResponseEntity(ex, HttpHeaders.EMPTY, request, HttpStatus.BAD_REQUEST, body);
	}

	private ResponseEntity<Object> handleExceptionInternalConstraint(Exception ex,
			String errorMessage, HttpHeaders headers, WebRequest request) {
		ApiResponse<Object> body = ApiResponse.onFailure(ErrorStatus.VALID_EXCEPTION.getStatus(),
				errorMessage, null);

		return toResponseEntity(ex, headers, request, ErrorStatus.VALID_EXCEPTION.getHttpStatus(),
				body);
	}

	private ResponseEntity<Object> toResponseEntity(Exception ex, HttpHeaders headers,
			WebRequest request,
			HttpStatus httpStatus, ApiResponse<Object> body) {
		log.error("Exception handled: {}", ex.getMessage());
		return super.handleExceptionInternal(ex, body, headers, httpStatus, request);
	}
}
