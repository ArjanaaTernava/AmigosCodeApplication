package com.evonem;

import org.junit.jupiter.api.Test;

import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
public class TestContainersTest extends AbstractTestcontainers {

    @Test
    void itShouldStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }




}
