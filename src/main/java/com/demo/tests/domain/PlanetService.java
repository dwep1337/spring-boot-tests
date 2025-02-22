package com.demo.tests.domain;

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

    public Planet getPlanetById(Long id) {
        return planetRepository.findById(id)
                .orElse(null);
    }

    public Planet getPlanetByName(String name) {
        return planetRepository.findByName(name)
                .orElse(null);
    }
}
