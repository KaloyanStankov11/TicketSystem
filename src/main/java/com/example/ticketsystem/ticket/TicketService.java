package com.example.ticketsystem.ticket;

import com.example.ticketsystem.Base._BaseService;
import com.example.ticketsystem.card.DiscountCard;
import com.example.ticketsystem.route.Route;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TicketService extends _BaseService {

    public BigDecimal calculateTicketPrice(Route route, DiscountCard discountCard){
        BigDecimal discountPercent = BigDecimal.ZERO;
        BigDecimal defaultPrice = route.getTicketPrice();
        if (discountCard != null){
            discountPercent = discountCard.getDiscountPercent();
        }
        return defaultPrice.subtract(defaultPrice.multiply(discountPercent));
    }
}
