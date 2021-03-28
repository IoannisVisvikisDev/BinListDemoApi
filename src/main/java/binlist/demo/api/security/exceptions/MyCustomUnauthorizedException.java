package binlist.demo.api.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MyCustomUnauthorizedException extends AbstractCustomException{
	
	public MyCustomUnauthorizedException(String errorMessage) {
		super(errorMessage);
	}

	public MyCustomUnauthorizedException() {
		super();
	}
	
}
