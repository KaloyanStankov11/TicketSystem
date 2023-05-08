package com.example.ticketsystem.reservation;

import com.example.ticketsystem.Base._BaseService;
import com.example.ticketsystem.ticket.Ticket;
import jakarta.persistence.EntityTransaction;
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

    public List<Reservation> getReservationsForUser(Long userId){
        return em.createQuery("SELECT reservation FROM Reservation reservation where reservation.userId = ?1", Reservation.class)
                .setParameter(1, userId)
                .getResultList();
    }

    public void editReservation(Long reservationId, List<Ticket> newTicketList){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Reservation reservation = em.find(Reservation.class, reservationId);
        reservation.setTickets(newTicketList);
        tx.commit();
    }

    public void deleteReservation(Long reservationId){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Reservation reservation = em.find(Reservation.class, reservationId);
        reservation.setStatus(ReservationStatus.DELETED.name());
        tx.commit();
    }
}
