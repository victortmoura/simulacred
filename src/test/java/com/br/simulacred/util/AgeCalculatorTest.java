package com.br.simulacred.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class AgeCalculatorTest {

    @Test
    @DisplayName("Given valid birthDate when calculate age then return successfully")
    void givenValidBirthDateWhenCalculateAgeThenReturnSuccessfully() {
        int age = AgeCalculator.calculateAge(LocalDate.of(2002, 1, 1));

        assertThat(age).isPositive();
    }

}