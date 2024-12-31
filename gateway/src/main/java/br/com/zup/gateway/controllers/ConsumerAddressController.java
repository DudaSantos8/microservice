package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer-address")
public class ConsumerAddressController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ConsumerAddressRegisterDTO registerDTO) {
        try {
            return ResponseEntity.status(201).body(consumerAddressService.registerConsumerAddress(registerDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @PostMapping("/consumerRegister")
    public ResponseEntity<?> registerJustConsumer(@RequestBody ConsumerRegisterDTO registerDTO) {
        try {
            return ResponseEntity.status(201).body(consumerAddressService.registerConsumer(registerDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @PostMapping("/addressRegister")
    public ResponseEntity<?> registerJustAddress(@RequestBody AddressRegisterDTO registerDTO) {
        try {
            return ResponseEntity.status(201).body(consumerAddressService.registerAddress(registerDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<ConsumerAddressResponseDTO>> getAllConsumersAndAddress(){
        return ResponseEntity.ok(consumerAddressService.getAllConsumerAndAddress());
    }

    @GetMapping("/consumer")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(){
        return ResponseEntity.ok(consumerAddressService.getAllConsumers());
    }

    @GetMapping("/consumer/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable String id){
        try {
            return ResponseEntity.status(201).body(consumerAddressService.getConsumerById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddress(){
        return ResponseEntity.ok(consumerAddressService.getAllAddress());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable String id){
        try {
            return ResponseEntity.status(201).body(consumerAddressService.getAddressById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }
}
