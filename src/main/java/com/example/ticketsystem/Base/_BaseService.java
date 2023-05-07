package com.example.ticketsystem.Base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class _BaseService {
    @PersistenceContext
    public EntityManager em;
}
