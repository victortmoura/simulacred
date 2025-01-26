package com.br.simulacred.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreditSimulationRequest(BigDecimal loanAmount, LocalDate birthDate, int paymentTerm) {

}
