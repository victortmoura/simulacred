package com.br.simulacred.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.math.MathContext;

@Schema(description = "Response object for loan simulation")
public record CreditSimulationResponse(

        @Schema(description = "Total payment amount", example = "12125.40")
        BigDecimal totalAmount,

        @Schema(description = "Monthly payment amount", example = "1010.45")
        BigDecimal monthlyPayment,

        @Schema(description = "Total interest paid", example = "2125.40")
        BigDecimal totalInterest
) {

    public CreditSimulationResponse(BigDecimal totalAmount, BigDecimal monthlyPayment, BigDecimal totalInterest) {
        this.totalAmount = totalAmount.round(MathContext.DECIMAL32);
        this.monthlyPayment = monthlyPayment.round(MathContext.DECIMAL32);
        this.totalInterest = totalInterest.round(MathContext.DECIMAL32);
    }
}
