package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.dto.ReservationData;
import com.noranekoit.challenge4.models.entity.Reservation;
import com.noranekoit.challenge4.models.entity.Schedules;
import com.noranekoit.challenge4.models.entity.Users;
import com.noranekoit.challenge4.payload.request.ReservationRequest;
import com.noranekoit.challenge4.repository.ReservationRepository;
import com.noranekoit.challenge4.repository.SchedulesRepository;
import com.noranekoit.challenge4.repository.UsersRepository;
import com.noranekoit.challenge4.service.InvoiceServiceFacade;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InvoiceServiceImplFacade implements InvoiceServiceFacade {

    SchedulesRepository schedulesRepository;
    UsersRepository usersRepository;
    ReservationRepository reservationRepository;

    @Autowired
    public InvoiceServiceImplFacade(SchedulesRepository schedulesRepository, UsersRepository usersRepository, ReservationRepository reservationRepository) {
        this.schedulesRepository = schedulesRepository;
        this.usersRepository = usersRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public String reservationTicket(ReservationRequest reservationRequest) {
        if ( usersRepository.findById(reservationRequest.getIdUser()).isPresent()
        && schedulesRepository.findById(reservationRequest.getIdSchedule()).isPresent())
        {
            Users user = usersRepository.
                    findById(reservationRequest.getIdUser()).orElseThrow();
            Schedules schedule =schedulesRepository.
                    findById(reservationRequest.getIdSchedule()).orElseThrow();
            Reservation reservation = new Reservation(
                    reservationRequest.getIdReservation(),
                    schedule,
                    user
            );
            reservationRepository.save(reservation);
            return "berhasil melakukan reservation";
        }
        else {
            return "gagal melakukan reservation";
        }
    }

    @Override
    public List<ReservationData> getAllInvoices() {
        return reservationRepository.getAllReservation();
    }

    @Override
    public String createInvoice() {
        try {
            List<ReservationData> reservation = reservationRepository.getAllReservation();
            File path = ResourceUtils.getFile("classpath:SimpleInvoice.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(path.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reservation);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Created by", "Bun");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src\\main\\resources\\SimpleInvoice.pdf");
            return "Berhasil generate Invoice di src/main/resources/SimpleInvoice.pdf";

        } catch (Exception e) {
            e.printStackTrace();
            return "gagal generate Invoice";
        }
    }
}
