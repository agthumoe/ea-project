package edu.miu.cs544.moe.emr.application;

import edu.miu.cs544.moe.emr.application.dto.ErrorDto;
import edu.miu.cs544.moe.emr.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(basePackages = "edu.miu.cs544.moe.emr")
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotException(NotFoundException e) {
        return new ErrorDto(e.getMessage());
    }
}
