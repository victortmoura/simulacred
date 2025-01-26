package com.br.simulacred.strategy;

import java.math.BigDecimal;

public class MidAgeInterestRateStrategy implements InterestRateStrategy {

    @Override
    public BigDecimal calculateRate() {
        return new BigDecimal("0.02");
    }
}
