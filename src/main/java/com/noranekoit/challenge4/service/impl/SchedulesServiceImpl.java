package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.models.entity.Schedules;
import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;
import com.noranekoit.challenge4.repository.SchedulesRepository;
import com.noranekoit.challenge4.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SchedulesServiceImpl implements SchedulesService {
    private final SchedulesRepository schedulesRepository;

    @Autowired
    public SchedulesServiceImpl(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    @Override
    public ScheduleResponse addSchedules(InsertScheduleRequest insertScheduleRequest) {
        Schedules schedule = new Schedules(
                insertScheduleRequest.getScheduleId(),
                insertScheduleRequest.getJamMulai(),
                insertScheduleRequest.getJamSelesai(),
                insertScheduleRequest.getTanggalTayang(),
                insertScheduleRequest.getHargaTiket(),
                insertScheduleRequest.getFilms()
        );
        schedulesRepository.save(schedule);
        return convertScheduleToScheduleResponse(schedule);
    }

    @Override
    public Stream<ScheduleResponse> getAllFilmDateByFilm(String schedules) {
        List<Schedules> schedule = schedulesRepository.findAllDateByName(schedules);
        return schedule.stream().map(this::convertScheduleToScheduleResponse);
    }


    private ScheduleResponse convertScheduleToScheduleResponse(Schedules schedules){
        return new ScheduleResponse(
                schedules.getScheduleId(),
                schedules.getJamMulai(),
                schedules.getJamSelesai(),
                schedules.getTanggalTayang(),
                schedules.getHargaTiket(),
                schedules.getFilms()
        );
    }
}
