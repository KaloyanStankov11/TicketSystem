package com.example.ticketsystem.route;

import com.example.ticketsystem.Base._BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class CityRoute extends _BaseEntity {
    private String city;
    private Long routeId;
}
