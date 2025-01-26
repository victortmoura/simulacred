package com.br.simulacred.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Request object for loan simulation")
public record CreditSimulationRequest(

        @Positive
        @Schema(description = "Loan amount to be simulated", example = "10000")
        BigDecimal loanAmount,

        @Past(message = "The date must be in the past.")
        @Schema(description = "Customer's birth date in ISO-8601 format", example = "1994-08-12")
        LocalDate birthDate,

        @Positive
        @Schema(description = "Payment term in months", example = "10")
        int paymentTerm
) {}
