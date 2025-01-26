package com.br.simulacred.service;

import com.br.simulacred.model.CreditSimulationRequest;
import com.br.simulacred.model.CreditSimulationResponse;
import com.br.simulacred.strategy.InterestRateStrategy;
import com.br.simulacred.strategy.InterestRateStrategyFactory;
import com.br.simulacred.util.AgeCalculator;
import com.br.simulacred.util.LoanCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreditSimulationService {

    private final InterestRateStrategyFactory strategyFactory;

    public CreditSimulationResponse simulate(CreditSimulationRequest request) {
        int age = AgeCalculator.calculateAge(request.birthDate());
        InterestRateStrategy strategy = strategyFactory.getStrategy(age);

        BigDecimal interestRate = strategy.calculateRate();
        BigDecimal loanAmount = request.loanAmount();
        int paymentTermInMonths = request.paymentTerm();

        // Calculation
        BigDecimal monthlyPayment = LoanCalculator.calculateMonthlyPayment(loanAmount, interestRate, paymentTermInMonths);
        BigDecimal totalAmount = LoanCalculator.calculateTotalAmount(monthlyPayment, paymentTermInMonths);
        BigDecimal totalInterest = LoanCalculator.calculateTotalInterest(totalAmount, loanAmount);

        return new CreditSimulationResponse(totalAmount, monthlyPayment, totalInterest);
    }

}
