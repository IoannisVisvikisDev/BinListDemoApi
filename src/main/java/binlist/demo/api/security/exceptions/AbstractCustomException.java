package binlist.demo.api.security.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public abstract class AbstractCustomException extends RuntimeException{

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public AbstractCustomException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public AbstractCustomException() {
		super();
	}
	
}
