package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;

import java.util.stream.Stream;

public interface SchedulesService {
    ScheduleResponse addSchedules(InsertScheduleRequest insertScheduleRequest);

    Stream<ScheduleResponse> getAllFilmDateByFilm(String schedules);
}
