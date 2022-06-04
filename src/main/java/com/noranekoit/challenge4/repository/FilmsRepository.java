package com.noranekoit.challenge4.repository;

import com.noranekoit.challenge4.models.entity.Films;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmsRepository extends JpaRepository<Films,String> {

    @Query(value = "SELECT f FROM films f WHERE f.statusTayang=true")
    List<Films>findAllByStatusTayang();

}