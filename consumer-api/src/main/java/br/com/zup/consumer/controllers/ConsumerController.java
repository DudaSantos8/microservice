package br.com.zup.consumer.controllers;

import br.com.zup.consumer.controllers.dtos.ConsumerRequestDTO;
import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import br.com.zup.consumer.models.Consumer;
import br.com.zup.consumer.services.ConsumerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<?> createConsumer(@RequestBody @Valid ConsumerRequestDTO requestDTO) {
        log.info("Start consumer register flow");
        try {
            log.info("Finish happy consumer register flow");
            return ResponseEntity.status(201).body(consumerService.createConsumer(requestDTO.toEntity()));
        }catch (Exception e){
            log.info("Show error consumer register flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers() {
        return ResponseEntity.ok(consumerService.getAllConsumers());
    }

    // Read (Get by ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumerById(@PathVariable String id) {
        log.info("Start consumer get by id flow");
        try {
            log.info("Finish happy consumer get by id flow");
            return ResponseEntity.status(200).body(consumerService.getConsumerById(id));
        }catch (Exception e){
            log.info("Show error consumer get by id flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsumer(@PathVariable String id, @RequestBody @Valid ConsumerRequestDTO requestDTO) {
        log.info("Start consumer update flow");
        try {
            log.info("Finish happy consumer update flow");
            return ResponseEntity.status(201).body(consumerService.updateConsumer(id,requestDTO));
        }catch (Exception e){
            log.info("Show error consumer update flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsumer(@PathVariable String id) {
        log.info("Start consumer delete flow");
        try {
            log.info("Finish happy consumer delete flow");
            consumerService.deleteConsumer(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.info("Show error consumer delete flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}