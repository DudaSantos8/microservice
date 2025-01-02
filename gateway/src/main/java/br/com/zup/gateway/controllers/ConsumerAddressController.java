package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.services.consumerAddress.ConsumerAddressService;
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

    @GetMapping()
    public ResponseEntity<List<ConsumerAddressResponseDTO>> getAllConsumersAndAddress(){
        return ResponseEntity.ok(consumerAddressService.getAllConsumerAndAddress());
    }

    @PutMapping("/updateConsumerAddress")
    public ResponseEntity<?> updateConsumerAddress(@RequestParam String idConsumer, @RequestParam String idAddress, @RequestBody ConsumerAddressRegisterDTO registerDTO){
        try {
            return ResponseEntity.status(201).body(consumerAddressService.updateConsumerAddress(registerDTO, idConsumer, idAddress));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }
}
