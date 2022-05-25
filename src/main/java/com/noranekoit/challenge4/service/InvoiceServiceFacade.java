package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.dto.ReservationData;

import java.util.List;

public interface InvoiceServiceFacade {

    List<ReservationData> getAllInvoices();

    String createInvoice();
}
