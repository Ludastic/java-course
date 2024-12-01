package com.example.demo.book.repositories;

import com.example.demo.book.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomIdAndStartTimeBetween(Long roomId,
                                                      LocalDateTime start,
                                                      LocalDateTime end);
    List<Reservation> findByRoomId(Long roomId);
}
