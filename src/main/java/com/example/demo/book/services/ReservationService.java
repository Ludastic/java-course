package com.example.demo.book.services;

import com.example.demo.book.entities.Reservation;
import com.example.demo.book.repositories.ReservationRepository;
import com.example.demo.room.entities.Room;
import com.example.demo.room.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public boolean checkAvailability(Long roomId, LocalDateTime startTime,
                                     LocalDateTime endTime) {
        List<Reservation> reservations = reservationRepository.findByRoomId(roomId);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
            if (reservation.getStartTime().isBefore(endTime) &&
                    reservation.getEndTime().isAfter(startTime)) {
                return false;
            }
        }
        return true;
    }

    public Reservation bookRoom(Long roomId, LocalDateTime startTime,
                                LocalDateTime endTime, String userName) {
        if (!checkAvailability(roomId, startTime, endTime)) {
            throw new RuntimeException("The room is already booked at the selected time.");
        }
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new RuntimeException("Room not found"));
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setUserName(userName);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByRoom(Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }
}
