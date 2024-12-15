package edu.miu.cs544.moe.emr.application;

import edu.miu.cs544.moe.emr.application.dto.ErrorDto;
import edu.miu.cs544.moe.emr.application.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class, basePackages = "edu.miu.cs544.moe.emr")
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorDto handleNotException(NotFoundException e) {
        return new ErrorDto(e.getMessage());
    }
}
