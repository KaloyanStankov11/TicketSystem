package com.example.ticketsystem.reservation;

import com.example.ticketsystem.Base._BaseService;
import com.example.ticketsystem.ticket.Ticket;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService extends _BaseService {

    public void createReservation(Long userId, List<Ticket> tickets){
        LocalDateTime currentTime = LocalDateTime.now();
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.ACTIVE.name());
        reservation.setTickets(tickets);
        reservation.setTimeCreated(currentTime);
        reservation.setUserId(userId);
        em.persist(reservation);
    }
}
