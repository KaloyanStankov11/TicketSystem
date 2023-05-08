package com.example.ticketsystem;

import com.example.ticketsystem.card.CardType;
import com.example.ticketsystem.route.Route;
import com.example.ticketsystem.ticket.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class TicketPriceCalculationTest {
    @InjectMocks
    private TicketService ticketService;

    @Test
    @Order(1)
    void testPeakHourFamilyCardChildren(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T17:00:00"));
        String discountCardType = CardType.FAMILY.name();
        Boolean isChildren = true;
        BigDecimal expextedPrice = BigDecimal.valueOf(25.0);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(2)
    void testPeakHourFamilyCardNotChildren(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T17:00:00"));
        String discountCardType = CardType.FAMILY.name();
        Boolean isChildren = false;
        BigDecimal expextedPrice = BigDecimal.valueOf(45.0);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(3)
    void testNotPeakHourRetiredCard(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T11:00:00"));
        String discountCardType = CardType.RETIRED.name();
        Boolean isChildren = true;
        BigDecimal expextedPrice = BigDecimal.valueOf(31.35);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren).setScale(2, RoundingMode.HALF_UP);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(4)
    void testNotPeakHourNoCard(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T11:00:00"));
        String discountCardType = null;
        Boolean isChildren = true;
        BigDecimal expextedPrice = BigDecimal.valueOf(47.50).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(5)
    void testPeakHourNoCard(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T17:00:00"));
        String discountCardType = null;
        Boolean isChildren = false;
        BigDecimal expextedPrice = BigDecimal.valueOf(50);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(6)
    void testPeakHourRetiredCard(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T18:00:00"));
        String discountCardType = CardType.RETIRED.name();
        Boolean isChildren = true;
        BigDecimal expextedPrice = BigDecimal.valueOf(33).setScale(2, RoundingMode.HALF_UP);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren).setScale(2, RoundingMode.HALF_UP);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(7)
    void testNotPeakHourFamilyCardChildren(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T14:00:00"));
        String discountCardType = CardType.FAMILY.name();
        Boolean isChildren = true;
        BigDecimal expextedPrice = BigDecimal.valueOf(23.75);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren).setScale(2, RoundingMode.HALF_UP);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(8)
    void testNotPeakHourFamilyCardNotChildren(){
        Route route = new Route();
        route.setTicketPrice(BigDecimal.valueOf(50));
        route.setDepartTime(LocalDateTime.parse("2023-05-10T14:00:00"));
        String discountCardType = CardType.FAMILY.name();
        Boolean isChildren = false;
        BigDecimal expextedPrice = BigDecimal.valueOf(42.75);
        BigDecimal actualPrice = ticketService.calculateTicketPrice(route, discountCardType, isChildren).setScale(2, RoundingMode.HALF_UP);
        Assertions.assertEquals(expextedPrice, actualPrice);
    }

    @Test
    @Order(9)
    void timeBoundariesTests(){
        BigDecimal ticketPrice = BigDecimal.valueOf(50);
        LocalTime[] peakHourTimes = {
                LocalTime.parse("07:30:01"), LocalTime.parse("09:29:59"),
                LocalTime.parse("16:00:01"), LocalTime.parse("19:29:59")
        };
        LocalTime[] nonPeakHourTimes = {
                LocalTime.parse("07:29:59"), LocalTime.parse("09:30:01"),
                LocalTime.parse("15:59:59"), LocalTime.parse("19:30:01")
        };
        for(LocalTime time : peakHourTimes){
            Assertions.assertEquals(BigDecimal.valueOf(50.00).setScale(2), ticketService.getPriceByTime(ticketPrice, time));
        }
        for(LocalTime time : nonPeakHourTimes){
            Assertions.assertEquals(BigDecimal.valueOf(47.50).setScale(2), ticketService.getPriceByTime(ticketPrice, time));
        }
    }
}
