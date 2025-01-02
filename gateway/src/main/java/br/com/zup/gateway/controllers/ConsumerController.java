package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.consumer.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService consumerService;

    @PostMapping()
    public ResponseEntity<?> registerConsumer(@RequestBody ConsumerRegisterDTO registerDTO) {
        log.info("Start consumer register flow");
        try {
            log.info("Finish happy consumer register flow");
            return ResponseEntity.status(201).body(consumerService.registerConsumer(registerDTO));
        } catch (RuntimeException e) {
            log.info("Show error from the external api register flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error register flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers(){
        return ResponseEntity.ok(consumerService.getAllConsumers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable String id){
        log.info("Start consumer get by id flow");
        try {
            log.info("Finish happy consumer get by id flow");
            return ResponseEntity.status(200).body(consumerService.getConsumerById(id));
        } catch (RuntimeException e) {
            log.info("Show error from the external api get by id flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error get by id flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateAddress(@RequestParam String idAddress, @RequestBody ConsumerRegisterDTO registerDTO){
        log.info("Start consumer update flow");
        try {
            log.info("Finish happy consumer update flow");
            return ResponseEntity.status(201).body(consumerService.updateConsumer(registerDTO, idAddress));
        } catch (RuntimeException e) {
            log.info("Show error from the external api update flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error update flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

}
