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
    private UserType userType;
    private String discountCard;

}
