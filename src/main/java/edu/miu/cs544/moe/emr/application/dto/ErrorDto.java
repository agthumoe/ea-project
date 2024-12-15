package edu.miu.cs544.moe.emr.application.dto;

public class ErrorDto {
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
