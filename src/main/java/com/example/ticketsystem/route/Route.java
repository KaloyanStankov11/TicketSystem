package com.example.ticketsystem.route;

import com.example.ticketsystem.Base._BaseEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Route extends _BaseEntity {
    private String startCity;
    private String endCity;
    private LocalDateTime departTime;
    private BigDecimal ticketPrice;
}
