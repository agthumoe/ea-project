package edu.miu.cs544.moe.emr.exception;

public class BadRequestException extends HttpStatusException{
    public BadRequestException(String message) {
        super(400, message);
    }
}
