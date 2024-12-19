package edu.miu.cs544.moe.emr.exception;

public class NotFoundException extends HttpStatusException {
    public NotFoundException(String message) {
        super(404, message);
    }
}
