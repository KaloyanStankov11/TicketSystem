package com.example.ticketsystem.reservation;

import com.example.ticketsystem.Base._BaseEntity;
import com.example.ticketsystem.ticket.Ticket;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Reservation extends _BaseEntity {
    private Long userId;
    @OneToMany
    private List<Ticket> tickets;
    private LocalDateTime timeCreated;
    private LocalDateTime validTo;
    private String status;
}
