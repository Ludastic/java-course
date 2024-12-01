package com.example.demo.book.controllers;

import com.example.demo.book.services.ReservationService;
import com.example.demo.room.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final RoomService roomService;

    public ReservationController(ReservationService reservationService,
                                 RoomService roomService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping("/reserve/{roomId}")
    public String showReservationForm(@PathVariable Long roomId, Model model) {
        model.addAttribute("room", roomService.getRoomById(roomId));
        return "reservation_form";
    }

    @PostMapping("/reserve/{roomId}")
    public String reserveRoom(@PathVariable Long roomId,
                              @RequestParam LocalDateTime startTime,
                              @RequestParam LocalDateTime endTime,
                              @RequestParam String userName,
                              Model model) {
        try {
            reservationService.bookRoom(roomId, startTime, endTime, userName);
            model.addAttribute("message", "Reservation successful!");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
            return "failure";
        }
        model.addAttribute("roomId", roomId);
        model.addAttribute("userName", userName);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        return "success";
    }

    @GetMapping("/room/{roomId}/reservations")
    public String viewRoomReservations(@PathVariable Long roomId, Model model) {
        model.addAttribute("reservations",
                reservationService.getReservationsByRoom(roomId));
        return "room_reservations";
    }
}
