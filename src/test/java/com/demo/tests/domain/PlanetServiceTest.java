package com.demo.tests.domain;

import static com.demo.tests.common.PlanetConstants.PLANET;
import static com.demo.tests.common.PlanetConstants.INVALID_PLANET;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
}