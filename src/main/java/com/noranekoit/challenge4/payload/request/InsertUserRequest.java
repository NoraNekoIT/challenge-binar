package com.noranekoit.challenge4.payload.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class InsertUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Set<String> role;

    @NotBlank
    private String email;


}
