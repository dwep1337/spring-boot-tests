package com.demo.tests.service;

import com.demo.tests.domain.Planet;
import com.demo.tests.repository.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }


    public Planet createPlanet(Planet planet) {
        return planetRepository.save(planet);
    }
}
