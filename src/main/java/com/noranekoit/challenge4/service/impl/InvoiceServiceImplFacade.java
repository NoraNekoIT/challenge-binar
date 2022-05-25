package com.noranekoit.challenge4.service.impl;
import com.noranekoit.challenge4.dto.ReservationData;
import com.noranekoit.challenge4.repository.ReservationRepository;
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

    @Autowired
    ReservationRepository reservationRepository;

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

        }catch (Exception e){
            e.printStackTrace();
            return  "gagal generate Invoice";
        }


    }
}
