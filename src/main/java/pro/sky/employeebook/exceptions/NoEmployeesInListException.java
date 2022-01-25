package pro.sky.employeebook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoEmployeesInListException extends RuntimeException {
    public NoEmployeesInListException(String message) {
        super(message);
    }
}
