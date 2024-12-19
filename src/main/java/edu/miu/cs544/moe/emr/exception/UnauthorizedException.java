package edu.miu.cs544.moe.emr.exception;

public class UnauthorizedException extends HttpStatusException {
    public UnauthorizedException(String message) {
        super(401, message);
    }
}
