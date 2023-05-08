package com.example.ticketsystem.route;

import com.example.ticketsystem.Base._BaseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RouteService extends _BaseService {

    public Route findRoute(String destinationCity, LocalDateTime departureTime){
        return em.createQuery("SELECT route FROM Route route where route.endCity = ?1 AND route.departTime = ?2", Route.class)
                .setParameter(1, destinationCity)
                .setParameter(2, departureTime)
                .getSingleResult();
    }
}
