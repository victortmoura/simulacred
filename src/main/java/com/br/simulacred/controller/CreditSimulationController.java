package com.br.simulacred.controller;

import com.br.simulacred.model.CreditSimulationRequest;
import com.br.simulacred.model.CreditSimulationResponse;
import com.br.simulacred.service.CreditSimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulations")
@RequiredArgsConstructor
public class CreditSimulationController {

    private final CreditSimulationService simulationService;

    @PostMapping
    @Operation(
            summary = "Simulate Loan",
            description = "Simulates a loan calculation based on the provided loan amount, birth date, and payment term.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful simulation",
                            content = @Content(schema = @Schema(implementation = CreditSimulationResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<CreditSimulationResponse> simulateLoan(@RequestBody CreditSimulationRequest request) {
        CreditSimulationResponse response = simulationService.simulate(request);
        return ResponseEntity.ok(response);
    }
}
