package com.example.ticketsystem.card;

import com.example.ticketsystem.Base._BaseEntity;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class DiscountCard extends _BaseEntity {
    private String cardType;
    private BigDecimal discountPercent;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }
}
