package com.br.simulacred.strategy;

import org.springframework.stereotype.Component;

import java.util.NavigableMap;
import java.util.TreeMap;

@Component
public class InterestRateStrategyFactory {

    private final NavigableMap<Integer, InterestRateStrategy> strategies;

    public InterestRateStrategyFactory() {
        this.strategies = new TreeMap<>();
        this.strategies.put(25, new YoungInterestRateStrategy());
        this.strategies.put(40, new AdultInterestRateStrategy());
        this.strategies.put(60, new MidAgeInterestRateStrategy());
        this.strategies.put(Integer.MAX_VALUE, new SeniorInterestRateStrategy());
    }

    public InterestRateStrategy getStrategy(int age) {
        return strategies.ceilingEntry(age).getValue();
    }

}
