package com.noranekoit.challenge4.repository;

import com.noranekoit.challenge4.dto.ReservationData;
import com.noranekoit.challenge4.models.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    @Query(nativeQuery = true)
    List<ReservationData> getAllReservation();
}
