package com.hadoyaji.movereservation.springboot.domain.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations,Long> {
    @Query("SELECT R FROM Reservations R ORDER BY R.id DESC")
    List<Reservations> findAllDesc();

    List<Reservations> findByUserId(Long userId);
}
