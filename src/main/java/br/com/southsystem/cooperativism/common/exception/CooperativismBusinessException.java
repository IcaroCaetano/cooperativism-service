package br.com.southsystem.cooperativism.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class CooperativismBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final String errorCode;

	
	public CooperativismBusinessException(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}