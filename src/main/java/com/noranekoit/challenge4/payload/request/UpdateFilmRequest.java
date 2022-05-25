package com.noranekoit.challenge4.payload.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UpdateFilmRequest {
    @NonNull
    private String filmName;
    @NonNull
    private boolean statusTayang;
}