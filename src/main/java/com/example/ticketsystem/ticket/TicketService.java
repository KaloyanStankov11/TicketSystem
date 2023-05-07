package com.example.ticketsystem.ticket;

import com.example.ticketsystem.Base._BaseService;
import com.example.ticketsystem.card.DiscountCard;
import com.example.ticketsystem.route.Route;
import com.example.ticketsystem.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TicketService extends _BaseService {

    public BigDecimal calculateTicketPrice(Route route, DiscountCard discountCard){
        BigDecimal discountPercent = BigDecimal.ZERO;
        BigDecimal defaultPrice = route.getTicketPrice();
        if (discountCard != null){
            discountPercent = discountCard.getDiscountPercent();
        }
        return defaultPrice.subtract(defaultPrice.multiply(discountPercent.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)));
    }

    public Ticket generateTicket(Route route, User user, Boolean isChildren){
        Ticket ticket = new Ticket();
        ticket.setChildren(isChildren);
        ticket.setRouteId(route.getId());
        ticket.setUserId(user.getId());
        DiscountCard discountCard = em.find(DiscountCard.class, user.getDiscountCard());
        BigDecimal ticketPrice = calculateTicketPrice(route, discountCard);
        ticket.setPrice(ticketPrice);
        em.persist(ticket);
        return ticket;
    }
}
