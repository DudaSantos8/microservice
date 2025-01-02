package br.com.zup.gateway.services.consumerAddress;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import java.util.List;


public interface ConsumerAddressService {
    ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO registerDTO);

    List<ConsumerAddressResponseDTO> getAllConsumerAndAddress();

    ConsumerAddressResponseDTO updateConsumerAddress(ConsumerAddressRegisterDTO registerDTO, String consumerId, String addressId);
}
