package com.noranekoit.challenge4.repository;

import com.noranekoit.challenge4.models.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules,String> {
    @Query(value = "SELECT s FROM schedules s JOIN s.films f WHERE f.filmName LIKE :film%")
    List<Schedules> findAllDateByName(@Param("film") String film);

//    @Query("SELECT m FROM Menu m INNER JOIN m.kategori k WHERE m.nama_menu LIKE %?1%")
//    List<Menu> searchMenu(String search);

}
