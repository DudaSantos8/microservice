package br.com.zup.gateway.services;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerAddressService {

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private AddressClient addressClient;

    public ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO registerDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = registerConsumer(registerDTO);
            AddressResponseDTO addressResponseDTO = registerAddress(registerDTO, consumerResponseDTO.getId());

            return new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
        } catch (Exception e) {
           throw new RuntimeException("Failed to register consumer and address: " + e.getMessage(), e);
        }
    }

    public ConsumerResponseDTO registerConsumer(ConsumerRegisterDTO consumerRegisterDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = consumerRegisterDTO.toConsumerResponseDto();
            return consumerClient.registerConsumerClient(consumerResponseDTO.toConsumerRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    public AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDTO) {
        try {
            AddressResponseDTO addressResponseDTO = addressRegisterDTO.toAddressResponseDto();
            return addressClient.registerAddress(addressResponseDTO.toAddressRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    public List<ConsumerResponseDTO> getAllConsumers(){
        return consumerClient.getAllConsumers();
    }

    public ConsumerResponseDTO getConsumerById(String id){
        try {
            return consumerClient.getConsumer(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while get consumer: " + e.getMessage(), e);
        }
    }

    public List<AddressResponseDTO> getAllAddress(){
        return addressClient.getAllAddress();
    }

    public AddressResponseDTO getAddressById(String id){
        try {
            return addressClient.getAddress(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while get address: " + e.getMessage(), e);
        }
    }

    private ConsumerResponseDTO registerConsumer(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = consumerAddressRegisterDTO.toConsumerResponseDTO();
            return consumerClient.registerConsumerClient(consumerResponseDTO.toConsumerRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    private AddressResponseDTO registerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        try {
            AddressResponseDTO addressResponseDTO = consumerAddressRegisterDTO.toAddressResponseDTO();
            addressResponseDTO.setConsumerId(consumerId);
            return addressClient.registerAddress(addressResponseDTO.toAddressRegisterDto());
        }catch (Exception e) {
            throw new RuntimeException("Error while registering address: " + e.getMessage(), e);
        }
    }

}