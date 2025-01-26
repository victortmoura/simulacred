package com.br.simulacred.util;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.HALF_UP;

public class LoanCalculator {

    private static final int SCALE = 10;

    public static BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal annualInterestRate, int paymentTermInMonths) {
        // Convert annual interest rate to monthly rate: (rate / 12)
        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12), SCALE, HALF_UP);

        // Calculate (1 + monthlyRate)^paymentTermInMonths
        BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);
        BigDecimal powerTerm = onePlusRate.pow(paymentTermInMonths, new MathContext(SCALE, HALF_UP));

        // BigDecimal does not directly support negative powers via the pow method.
        // To obtain the reciprocal should divide 1 by the positive power.
        // Calculate the denominator: 1 - (1 + monthlyRate)^(-paymentTermInMonths)
        BigDecimal denominator = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(powerTerm, SCALE, HALF_UP));

        // Calculate the monthly payment: PMT = loanAmount * monthlyRate / denominator
        return loanAmount.multiply(monthlyRate).divide(denominator, SCALE, HALF_UP);
    }

    public static BigDecimal calculateTotalAmount(BigDecimal monthlyPayment, int paymentTermInMonths) {
        return monthlyPayment.multiply(BigDecimal.valueOf(paymentTermInMonths));
    }

    public static BigDecimal calculateTotalInterest(BigDecimal totalAmount, BigDecimal loanAmount) {
        return totalAmount.subtract(loanAmount);
    }

}
