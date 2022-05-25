package com.noranekoit.challenge4.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String password;
    private String email;
}