package com.example.ticketsystem.ticket;

import com.example.ticketsystem.Base._BaseEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Ticket extends _BaseEntity {
    private Long userId;
    private Long routeId;
    private BigDecimal price;
    private Boolean isChildren;
}
