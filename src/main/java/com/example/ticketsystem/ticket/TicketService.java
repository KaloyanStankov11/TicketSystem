package com.example.ticketsystem.ticket;

import com.example.ticketsystem.Base._BaseService;
import com.example.ticketsystem.card.CardType;
import com.example.ticketsystem.card.DiscountCard;
import com.example.ticketsystem.route.Route;
import com.example.ticketsystem.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;

@Service
public class TicketService extends _BaseService {

    private static final Double DEFAULT_PERCENT = 0.05;
    private static final Double FAMILY_PERCENT = 0.1;
    private static final Double CHILDREN_FAMILY_PERCENT = 0.5;
    private static final Double RETIRED_PERCENT = 0.34;

    public BigDecimal calculateTicketPrice(Route route, String discountCardType, Boolean isChildren) {
        BigDecimal calculatedPrice = route.getTicketPrice();
        LocalTime departTime = route.getDepartTime().toLocalTime();

        calculatedPrice = getPriceByTime(calculatedPrice, departTime);

        calculatedPrice = getPriceByCard(discountCardType, isChildren, calculatedPrice);

        return calculatedPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPriceByCard(String discountCardType, boolean isChildren, BigDecimal calculatedPrice) {
        if (discountCardType == null) {
            return calculatedPrice;
        }
        if (discountCardType.equals(CardType.FAMILY.name())) {
            if (isChildren) {
                calculatedPrice = calculatedPrice.subtract(calculatedPrice.multiply(BigDecimal.valueOf(CHILDREN_FAMILY_PERCENT)));
            } else {
                calculatedPrice = calculatedPrice.subtract(calculatedPrice.multiply(BigDecimal.valueOf(FAMILY_PERCENT)));
            }
        } else if (discountCardType.equals(CardType.RETIRED.name())) {
            calculatedPrice = calculatedPrice.subtract(calculatedPrice.multiply(BigDecimal.valueOf(RETIRED_PERCENT)));
        }
        return calculatedPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPriceByTime(BigDecimal currentPrice, LocalTime departTime) {
        if ((departTime.isAfter(LocalTime.parse("09:30:00")) && departTime.isBefore(LocalTime.parse("16:00:00")))
                || departTime.isAfter(LocalTime.parse("19:30:00")) || departTime.isBefore(LocalTime.parse("07:30:00"))) {
            currentPrice = currentPrice.subtract(currentPrice.multiply(BigDecimal.valueOf(DEFAULT_PERCENT)));
        }
        return currentPrice.setScale(2, RoundingMode.HALF_UP);
    }


    public Ticket generateTicket(Route route, User user, Boolean isChildren) {
        Ticket ticket = new Ticket();
        ticket.setChildren(isChildren);
        ticket.setRouteId(route.getId());
        ticket.setUserId(user.getId());
        DiscountCard discountCard = em.find(DiscountCard.class, user.getDiscountCard());
        BigDecimal ticketPrice = calculateTicketPrice(route, discountCard.getCardType(), isChildren);
        ticket.setPrice(ticketPrice);
        em.persist(ticket);
        return ticket;
    }
}
