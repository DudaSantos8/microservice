package br.com.zup.gateway.services.consumerAddress;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConsumerAddressServiceImpl implements ConsumerAddressService {

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private AddressClient addressClient;

    @Override
    public ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO registerDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = registerConsumerInternal(registerDTO);
            AddressResponseDTO addressResponseDTO = registerAddressInternal(registerDTO, consumerResponseDTO.getId());

            return new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
        } catch (Exception e) {
           throw new RuntimeException("Failed to register consumer and address: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ConsumerAddressResponseDTO> getAllConsumerAndAddress() {
        List<ConsumerResponseDTO> consumerResponseDTOList = consumerClient.getAllConsumers();
        List<AddressResponseDTO> addressResponseDTOList = addressClient.getAllAddress();

        Map<String, AddressResponseDTO> addressMap = addressResponseDTOList.stream()
                .collect(Collectors.toMap(AddressResponseDTO::getConsumerId, address -> address));

        return consumerResponseDTOList.stream()
                .map(consumer -> {
                    AddressResponseDTO address = addressMap.get(consumer.getId());
                    return new ConsumerAddressResponseDTO(consumer, address);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ConsumerAddressResponseDTO updateConsumerAddress(ConsumerAddressRegisterDTO registerDTO, String consumerId, String addressId) {
        try {
            ConsumerResponseDTO consumerResponseDTO = updateConsumerInternal(consumerId,registerDTO);
            AddressResponseDTO addressResponseDTO = updateAddressInternal(addressId, registerDTO, consumerResponseDTO.getId());

            return new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register consumer and address: " + e.getMessage(), e);
        }
    }

    private ConsumerResponseDTO registerConsumerInternal(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = consumerAddressRegisterDTO.toConsumerResponseDTO();
            return consumerClient.registerConsumerClient(consumerResponseDTO.toConsumerRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    private AddressResponseDTO registerAddressInternal(ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        try {
            AddressResponseDTO addressResponseDTO = consumerAddressRegisterDTO.toAddressResponseDTO();
            addressResponseDTO.setConsumerId(consumerId);
            return addressClient.registerAddress(addressResponseDTO.toAddressRegisterDto());
        }catch (Exception e) {
            throw new RuntimeException("Error while registering address: " + e.getMessage(), e);
        }
    }

    private ConsumerResponseDTO updateConsumerInternal(String id, ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = consumerAddressRegisterDTO.toConsumerResponseDTO();
            return consumerClient.updateConsumerClient(id,consumerResponseDTO.toConsumerRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    private AddressResponseDTO updateAddressInternal(String id, ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        try {
            AddressResponseDTO addressResponseDTO = consumerAddressRegisterDTO.toAddressResponseDTO();
            addressResponseDTO.setConsumerId(consumerId);
            return addressClient.updateAddress(id, addressResponseDTO.toAddressRegisterDto());
        }catch (Exception e) {
            throw new RuntimeException("Error while registering address: " + e.getMessage(), e);
        }
    }

}