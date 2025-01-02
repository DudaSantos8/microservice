package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.services.consumerAddress.ConsumerAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer-address")
public class ConsumerAddressController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerAddressController.class);

    @Autowired
    private ConsumerAddressService consumerAddressService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ConsumerAddressRegisterDTO registerDTO) {
        log.info("Start consumer and address register flow");
        try {
            log.info("Finish happy consumer and address register flow");
            return ResponseEntity.status(201).body(consumerAddressService.registerConsumerAddress(registerDTO));
        } catch (RuntimeException e) {
            log.info("Show error from the external api register flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error register flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<ConsumerAddressResponseDTO>> getAllConsumersAndAddress(){
        return ResponseEntity.ok(consumerAddressService.getAllConsumerAndAddress());
    }

    @PutMapping("/updateConsumerAddress")
    public ResponseEntity<?> updateConsumerAddress(@RequestParam String idConsumer, @RequestParam String idAddress, @RequestBody ConsumerAddressRegisterDTO registerDTO){
        log.info("Start consumer and address update flow");
        try {
            log.info("Finish happy consumer and address update flow");
            return ResponseEntity.status(201).body(consumerAddressService.updateConsumerAddress(registerDTO, idConsumer, idAddress));
        } catch (RuntimeException e) {
            log.info("Show error from the external api update flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error update flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsumerAddress(@PathVariable String id){
        log.info("Start consumer and address delete flow");
        try {
            log.info("Finish happy consumer and address delete flow");
            consumerAddressService.deleteConsumerAddress(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.info("Show error from the external api delete flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error delete flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

}
