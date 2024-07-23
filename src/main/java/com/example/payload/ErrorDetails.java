package com.example.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class ErrorDetails {

    private String message;
    private Date date;

    public ErrorDetails(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
