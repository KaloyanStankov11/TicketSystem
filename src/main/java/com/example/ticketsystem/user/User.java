package com.example.ticketsystem.user;

import com.example.ticketsystem.Base._BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User extends _BaseEntity {
    private String username;
    private String email;
    private String password;
    private String userType;
    private String discountCard;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(String discountCard) {
        this.discountCard = discountCard;
    }
}
