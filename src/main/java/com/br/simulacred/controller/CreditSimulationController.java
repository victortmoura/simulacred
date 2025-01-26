package com.br.simulacred.controller;

import com.br.simulacred.model.CreditSimulationRequest;
import com.br.simulacred.model.CreditSimulationResponse;
import com.br.simulacred.service.CreditSimulationService;
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
    public ResponseEntity<CreditSimulationResponse> simulateLoan(@RequestBody CreditSimulationRequest request) {
        CreditSimulationResponse response = simulationService.simulate(request);
        return ResponseEntity.ok(response);
    }
}
