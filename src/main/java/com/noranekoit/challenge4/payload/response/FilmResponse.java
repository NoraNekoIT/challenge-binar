package com.noranekoit.challenge4.payload.response;

import com.noranekoit.challenge4.models.entity.Schedules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FilmResponse {
    private String filmCode;

    private String filmName;

    private boolean statusTayang;

    private List<Schedules> schedules ;
}