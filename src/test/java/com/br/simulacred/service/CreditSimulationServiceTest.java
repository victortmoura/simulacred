package com.br.simulacred.service;

import com.br.simulacred.model.CreditSimulationRequest;
import com.br.simulacred.model.CreditSimulationResponse;
import com.br.simulacred.strategy.InterestRateStrategyFactory;
import com.br.simulacred.strategy.YoungInterestRateStrategy;
import com.br.simulacred.util.AgeCalculator;
import com.br.simulacred.util.LoanCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(SpringExtension.class)
class CreditSimulationServiceTest {

    @Mock
    private InterestRateStrategyFactory factory;

    @InjectMocks
    private CreditSimulationService service;

    @Test
    @DisplayName("Given valid request with age under 25 when simulate loan then return response successfully")
    void givenValidRequestWhenLoanSimulateThenReturnResponseSuccessfully() {

        Mockito.when(factory.getStrategy(25)).thenReturn(new YoungInterestRateStrategy());

        try (
                MockedStatic<AgeCalculator> mockedAgeCalculator = mockStatic(AgeCalculator.class);
                MockedStatic<LoanCalculator> mockedLoanCalculator = mockStatic(LoanCalculator.class)
        ) {

            LocalDate birthDate = LocalDate.of(2000, 1, 1);
            // Mocking AgeCalculator.calculateAge
            mockedAgeCalculator.when(() -> AgeCalculator.calculateAge(birthDate)).thenReturn(25);

            // Mocking LoanCalculator.calculateMonthlyPayment
            mockedLoanCalculator.when(() -> LoanCalculator.calculateMonthlyPayment(
                            ArgumentMatchers.any(BigDecimal.class),
                            ArgumentMatchers.any(BigDecimal.class),
                            ArgumentMatchers.anyInt()))
                    .thenReturn(new BigDecimal("1010.45"));

            // Mocking LoanCalculator.calculateTotalAmount
            mockedLoanCalculator.when(() -> LoanCalculator.calculateTotalAmount(
                            ArgumentMatchers.any(BigDecimal.class),
                            ArgumentMatchers.anyInt()))
                    .thenReturn(new BigDecimal("12125.40"));

            // Mocking LoanCalculator.calculateTotalInterest
            mockedLoanCalculator.when(() -> LoanCalculator.calculateTotalInterest(
                            ArgumentMatchers.any(BigDecimal.class),
                            ArgumentMatchers.any(BigDecimal.class)))
                    .thenReturn(new BigDecimal("2125.40"));

            CreditSimulationRequest creditSimulationRequest = new CreditSimulationRequest(
                    new BigDecimal("10000"), birthDate, 10);

            // Act
            CreditSimulationResponse simulationResponse = service.simulate(creditSimulationRequest);
            // Assert
            assertThat(simulationResponse).isNotNull();
            assertThat(simulationResponse.monthlyPayment()).isNotNull().isEqualTo(new BigDecimal("1010.45"));
            assertThat(simulationResponse.totalAmount()).isNotNull().isEqualTo(new BigDecimal("12125.40"));
            assertThat(simulationResponse.totalInterest()).isNotNull().isEqualTo(new BigDecimal("2125.40"));

            mockedAgeCalculator.verify(() -> AgeCalculator.calculateAge(birthDate));
            mockedLoanCalculator.verify(() -> LoanCalculator.calculateMonthlyPayment(
                    ArgumentMatchers.any(BigDecimal.class),
                    ArgumentMatchers.any(BigDecimal.class),
                    ArgumentMatchers.anyInt()));
            mockedLoanCalculator.verify(() -> LoanCalculator.calculateTotalAmount(
                    ArgumentMatchers.any(BigDecimal.class),
                    ArgumentMatchers.anyInt()));
            mockedLoanCalculator.verify(() -> LoanCalculator.calculateTotalInterest(
                    ArgumentMatchers.any(BigDecimal.class),
                    ArgumentMatchers.any(BigDecimal.class)));
        }
    }
}