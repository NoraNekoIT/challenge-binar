package com.noranekoit.challenge4.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WebResponse <T> {
    int codeResponse;

    String statusResponse;

    T dataResponse;
}