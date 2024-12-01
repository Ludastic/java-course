package com.example.demo.room.services;

import com.example.demo.room.entities.Room;
import com.example.demo.room.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
}

