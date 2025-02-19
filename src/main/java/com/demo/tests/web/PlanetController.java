package com.demo.tests.web;

import com.demo.tests.domain.Planet;
import com.demo.tests.domain.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable Long id) {
        Planet planet = planetService.getPlanetById(id);
        return planet != null ? ResponseEntity.ok(planet) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> getPlanetByName(@PathVariable String name) {
        Planet planet = planetService.getPlanetByName(name);
        return planet != null ? ResponseEntity.ok(planet) : ResponseEntity.notFound().build();
    }
}
