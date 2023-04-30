package com.example.ticketsystem.user;

import com.example.ticketsystem.Common.DefaultEntity;
import com.example.ticketsystem.card.DiscountCard;
import jakarta.persistence.Entity;

@Entity
public class User extends DefaultEntity {
    private String username;
    private String email;
    private String password;
    private UserType userType;
    private DiscountCard discountCard;

}
