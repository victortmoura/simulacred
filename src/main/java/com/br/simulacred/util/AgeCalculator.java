package com.br.simulacred.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class AgeCalculator {

    public static int calculateAge(LocalDate birthDate) {
        try {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.", e);
        }
    }
}
