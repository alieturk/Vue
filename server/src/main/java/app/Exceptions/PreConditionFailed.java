package app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PreConditionFailed extends RuntimeException {

    public PreConditionFailed(String message) {super(message);}
}
