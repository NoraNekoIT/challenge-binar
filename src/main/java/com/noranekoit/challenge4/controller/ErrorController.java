package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.response.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value ={NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    WebResponse<String> notFound(){
        return new WebResponse<>(
                404,
                "Not Found",
                "Not Found"
        );
    }
}
