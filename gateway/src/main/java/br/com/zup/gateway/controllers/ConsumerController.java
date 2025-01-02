package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.consumer.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping()
    public ResponseEntity<?> registerConsumer(@RequestBody ConsumerRegisterDTO registerDTO) {
        try {
            return ResponseEntity.status(201).body(consumerService.registerConsumer(registerDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(){
        return ResponseEntity.ok(consumerService.getAllConsumers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable String id){
        try {
            return ResponseEntity.status(201).body(consumerService.getConsumerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

}
