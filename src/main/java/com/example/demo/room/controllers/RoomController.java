package com.example.demo.room.controllers;

import com.example.demo.book.services.ReservationService;
import com.example.demo.room.entities.Room;
import com.example.demo.room.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoomController {
    private final RoomService roomService;
    private final ReservationService reservationService;

    public RoomController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/rooms";
    }

    @GetMapping("/room/{id}")
    public String getRoomDetails(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getRoomById(id));
        var reservations = reservationService.getReservationsByRoom(id);
        model.addAttribute("reservations", reservations);
        return "room_details";
    }


    @GetMapping("/room/new")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add_room";
    }


    @PostMapping("/room/new")
    public String addNewRoom(@ModelAttribute Room room, Model model) {
        roomService.saveRoom(room);
        model.addAttribute("message", "Room added successfully!");
        return "redirect:/rooms";
    }
}


