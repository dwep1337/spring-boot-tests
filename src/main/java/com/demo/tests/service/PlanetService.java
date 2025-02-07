package com.demo.tests.service;

import com.demo.tests.domain.Planet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {


    public ResponseEntity<Planet> createPlanet(Planet planet) {
        return ResponseEntity.ok(new Planet());
    }
}
