package com.demo.tests.web;

import com.demo.tests.domain.Planet;
import com.demo.tests.domain.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet) {
        Planet createdPlanet = planetService.createPlanet(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanet);
    }
}
