package com.br.simulacred.model;

import java.math.BigDecimal;
import java.math.MathContext;

public record CreditSimulationResponse(BigDecimal totalAmount, BigDecimal monthlyPayment, BigDecimal totalInterest) {

    public CreditSimulationResponse(BigDecimal totalAmount, BigDecimal monthlyPayment, BigDecimal totalInterest) {
        this.totalAmount = totalAmount.round(MathContext.DECIMAL32);
        this.monthlyPayment = monthlyPayment.round(MathContext.DECIMAL32);
        this.totalInterest = totalInterest.round(MathContext.DECIMAL32);
    }
}
