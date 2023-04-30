package com.example.ticketsystem.Common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public abstract class DefaultEntity {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
