package com.noranekoit.challenge4.payload.request;

import com.noranekoit.challenge4.models.entity.Schedules;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertFilmRequest {

    @NonNull
    private String filmCode;

    @NonNull
    private String filmName;

    @NonNull
    private boolean statusTayang;

    private List<Schedules> schedules ;

}
