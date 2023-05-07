package com.example.ticketsystem.Base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class _BaseRepository {
    @PersistenceContext
    EntityManager em;
}
