package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.dto.ReservationData;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.ReservationRequest;

import java.util.List;

public interface InvoiceServiceFacade {
    String reservationTicket(ReservationRequest reservationRequest) throws NotFoundException;

    List<ReservationData> getAllInvoices();

    String createInvoice();
}
