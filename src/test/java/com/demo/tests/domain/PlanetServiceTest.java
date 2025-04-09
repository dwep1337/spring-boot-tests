package com.demo.tests.domain;

import static com.demo.tests.common.PlanetConstants.PLANET;
import static com.demo.tests.common.PlanetConstants.INVALID_PLANET;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;

    @Test
    @DisplayName("It is expected to create a successful planet.")
    public void createPlanetWithSuccess() {
        //mocks the behavior of the planetRepository.save method
        when(planetRepository.save(PLANET)).thenReturn(PLANET);

        //sut = System Under Test
        Planet sut = planetService.createPlanet(PLANET);

        //assertions
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    @DisplayName("It is expected to throw an exception when creating an invalid planet.")
    public void createPlanetWithInvalidPlanet() {
        when(planetRepository.save(INVALID_PLANET))
                .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.createPlanet(INVALID_PLANET))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("It is expected to get a planet by id.")
    public void getPlanetByIdWithSuccess() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));
        Planet sut = planetService.getPlanetById(1L);
        assertThat(sut).isEqualTo(PLANET);
        assertThat(sut).isNotNull();
    }

    @Test
    @DisplayName("It is expected to return null when the planet does not exist.")
    public void getPlanetByIdWithFail() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());
        Planet sut = planetService.getPlanetById(1L);
        assertThat(sut).isNull();
    }

    @Test
    @DisplayName("It is expected to get a planet by name.")
    public void getPlanetByNameWithSuccess() {
        when(planetRepository.findByName("name")).thenReturn(Optional.of(PLANET));

        Planet sut = planetService.getPlanetByName("name");

        assertThat(sut).isEqualTo(PLANET);
        assertThat(sut).isNotNull();
    }

    @Test
    @DisplayName("It is expected to return null when the planet does not exist.")
    public void getPlanetByNameWithFail() {
        when(planetRepository.findByName("name")).thenReturn(Optional.empty());

        Planet sut = planetService.getPlanetByName("name");

        assertThat(sut).isNull();
    }

    @Test
    @DisplayName("It is expected to get a planet by terrain and climate.")
    public void getPlanetByTerrainAndClimateWithSuccess() {
        List<Planet> planets = List.of(PLANET);

        Example<Planet> query = QueryBuilder.makeQuery(new Planet(planets.getFirst().getTerrain(),
                planets.getFirst().getClimate()));

        when(planetRepository.findAll(query)).thenReturn(planets);

        List<Planet> sut = planetService.list(planets.getFirst().getTerrain(),
                planets.getFirst().getClimate());

        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.getFirst()).isEqualTo(PLANET);
    }

    @Test
    @DisplayName("It is expected to return an empty list when no planets match the criteria.")
    public void getPlanetByTerrainAndClimateWithFail() {
        when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());

        List<Planet> sut = planetService.list("invalidTerrain", "invalidClimate");

        assertThat(sut).isEmpty();
        assertThat(sut).hasSize(0);
    }
}