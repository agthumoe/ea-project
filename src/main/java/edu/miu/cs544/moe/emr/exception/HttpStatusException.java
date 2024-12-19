package edu.miu.cs544.moe.emr.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@ResponseBody
@Getter
public class HttpStatusException extends RuntimeException {
    private final HttpStatus httpStatus;

    public HttpStatusException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatusException(int httpStatus, String message) {
        super(message);
        this.httpStatus = HttpStatus.valueOf(httpStatus);
    }
}