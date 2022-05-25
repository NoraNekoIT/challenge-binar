package com.noranekoit.challenge4.payload.response;

import com.noranekoit.challenge4.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String username;

    private String email;

    private List<String> roles;

}
