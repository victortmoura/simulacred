package com.br.simulacred.controller;

import com.br.simulacred.model.CreditSimulationRequest;
import com.br.simulacred.model.CreditSimulationResponse;
import com.br.simulacred.service.CreditSimulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class CreditSimulationControllerTest {

    @Mock
    private CreditSimulationService service;

    @InjectMocks
    private CreditSimulationController controller;

    @BeforeEach
    void setUp() {
        CreditSimulationResponse response = new CreditSimulationResponse(
                new BigDecimal("1010.45"), new BigDecimal("12125.40"), new BigDecimal("2125.40")
        );

        BDDMockito.given(service.simulate(ArgumentMatchers.any(CreditSimulationRequest.class)))
                .willReturn(response);
    }

    @Test
    @DisplayName("Given valid loan amount, the customer's age and the payment term in months then return response successfully")
    void givenValidRequestWhenLoanSimulateThenReturnResponseSuccessfully() {
        CreditSimulationRequest creditSimulationRequest = new CreditSimulationRequest(
                new BigDecimal("10000"), LocalDate.parse("1994-08-12"), 10);

        CreditSimulationResponse simulationResponse = controller.simulateLoan(creditSimulationRequest).getBody();

        assertThat(simulationResponse).isNotNull();
        assertThat(simulationResponse.monthlyPayment()).isNotNull().isEqualTo(new BigDecimal("12125.40"));
        assertThat(simulationResponse.totalAmount()).isNotNull().isEqualTo(new BigDecimal("1010.45"));
        assertThat(simulationResponse.totalInterest()).isNotNull().isEqualTo(new BigDecimal("2125.40"));
    }
}