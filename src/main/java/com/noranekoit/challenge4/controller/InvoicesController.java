package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.dto.ReservationData;

import com.noranekoit.challenge4.payload.request.ReservationRequest;
import com.noranekoit.challenge4.service.impl.InvoiceServiceImplFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/invoice")
public class InvoicesController {
    private final InvoiceServiceImplFacade invoiceServiceImplFacade;

    @Autowired
    public InvoicesController(InvoiceServiceImplFacade invoiceServiceImplFacade) {
        this.invoiceServiceImplFacade = invoiceServiceImplFacade;
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String reservationTicket(@RequestBody ReservationRequest reservationRequest) {
        return invoiceServiceImplFacade.reservationTicket(reservationRequest);
    }

    @Operation(description = "perlu Role ADMIN silahkan login admin untuk dapat cookies barer token")
    @GetMapping("/invoiceAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReservationData> getAllInvoice() {
        return invoiceServiceImplFacade.getAllInvoices();
    }

    @Operation(description = "perlu Role CUSTOMER silahkan login customer untuk dapat cookies barer token")
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public String generateInvoice() {
        return invoiceServiceImplFacade.createInvoice();
    }
}