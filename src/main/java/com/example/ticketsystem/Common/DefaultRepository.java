package com.example.ticketsystem.Common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class DefaultRepository {
    @PersistenceContext
    EntityManager em;
}
