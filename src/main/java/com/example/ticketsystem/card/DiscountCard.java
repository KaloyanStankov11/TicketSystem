package com.example.ticketsystem.card;

import com.example.ticketsystem.Common.DefaultEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class DiscountCard extends DefaultEntity {
    private CardType cardType;
    private BigDecimal discountPercent;
}
