package br.com.zup.gateway.services;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;

import java.util.List;


public interface ConsumerAddressService {
    ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO registerDTO);

    ConsumerResponseDTO registerConsumer(ConsumerRegisterDTO consumerRegisterDTO);

    AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDTO);

    List<ConsumerAddressResponseDTO> getAllConsumerAndAddress();

    List<ConsumerResponseDTO> getAllConsumers();

    ConsumerResponseDTO getConsumerById(String id);

    List<AddressResponseDTO> getAllAddress();

    AddressResponseDTO getAddressById(String id);
}
