package com.noranekoit.challenge4.repository;

import com.noranekoit.challenge4.models.entity.Seats;
import com.noranekoit.challenge4.models.entity.SeatsIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SeatsRepository extends JpaRepository<Seats, SeatsIdentity> {
    @Query(value = "SELECT s FROM seats s WHERE s.seatStatus =false " )
    List<Seats> findAvailableSeats();
}
