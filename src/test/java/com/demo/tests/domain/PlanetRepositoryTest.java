package com.demo.tests.domain;

import com.demo.tests.common.PlanetConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // This annotation is used to test JPA repositories using h2 database
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private TestEntityManager entityManager; // This is used to manage the entity lifecycle in the test context

    @Test
    @DisplayName("Test if create a new planet with success")
    public void createPlanetWithSuccess() {
        Planet planet = planetRepository.save(PlanetConstants.PLANET);
        Planet sut = entityManager.find(Planet.class, planet.getId());

        assertThat(sut).isNotNull();
        assertThat(planet.getName()).isEqualTo(sut.getName());
        assertThat(planet.getTerrain()).isEqualTo(sut.getTerrain());
        assertThat(planet.getClimate()).isEqualTo(sut.getClimate());
    }
}
