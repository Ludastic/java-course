package com.example.demo.book.entities;

import com.example.demo.room.entities.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;  // Комната, которую бронируют

    @Column(name = "stat_at")
    private LocalDateTime startTime;
    @Column(name = "end_at")
    private LocalDateTime endTime;
    @Column(name = "user_id")
    private String userName;
}
