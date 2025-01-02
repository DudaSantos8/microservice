package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.services.address.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping()
    public ResponseEntity<?> registerAddress(@RequestBody AddressRegisterDTO registerDTO) {
        log.info("Start address register flow");
        try {
            log.info("Finish happy address register flow");
            return ResponseEntity.status(201).body(addressService.registerAddress(registerDTO));
        } catch (RuntimeException e) {
            log.info("Show error from the external api register flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error register flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<List<AddressResponseDTO>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable String id){
        log.info("Start address get by id flow");
        try {
            log.info("Finish happy address get by id flow");
            return ResponseEntity.status(200).body(addressService.getAddressById(id));
        } catch (RuntimeException e) {
            log.info("Show error from the external api get by id flow");
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error get by id flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateAddress(@RequestParam String idAddress, @RequestBody AddressRegisterDTO registerDTO){
        log.info("Start address update flow");
        try {
            log.info("Finish happy address update flow");
            return ResponseEntity.status(201).body(addressService.updateAddress(registerDTO, idAddress));
        } catch (RuntimeException e) {
            log.info("Show error from the external api update flow");
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            log.info("Show internal server error update flow");
            return ResponseEntity.status(500).body(Map.of("message", "Internal server error", "details", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable String id){
        log.info("Start address delete flow");
        try {
            log.info("Finish happy address delete flow");
            addressService.deleteAddress(id);
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
