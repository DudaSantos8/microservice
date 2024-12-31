package br.com.zup.address.controllers;

import br.com.zup.address.controllers.dtos.AddressRequestDTO;
import br.com.zup.address.services.AddressService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {
    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody @Valid AddressRequestDTO requestDTO) {
        log.info("Start address register flow");
        try {
            log.info("Finish happy address register flow");
            return ResponseEntity.status(201).body(addressService.createAddress(requestDTO.toEntity()));
        }catch (Exception e){
            log.info("Show error address register flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable String id) {
        log.info("Start address get by id flow");
        try {
            log.info("Finish happy address get by id flow");
            return ResponseEntity.status(200).body(addressService.getAddressById(id));
        }catch (Exception e){
            log.info("Show error address get by id flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable String id, @RequestBody AddressRequestDTO requestDTO) {
        log.info("Start address update flow");
        try {
            log.info("Finish happy address update flow");
            return ResponseEntity.status(201).body(addressService.updateAddress(id, requestDTO));
        }catch (Exception e){
            log.info("Show error address update flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable String id) {
        log.info("Start address delete flow");
        try {
            log.info("Finish happy address delete flow");
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.info("Show error address delete flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}