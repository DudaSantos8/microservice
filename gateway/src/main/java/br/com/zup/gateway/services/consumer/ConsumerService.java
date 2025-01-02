package br.com.zup.gateway.services.consumer;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;

import java.util.List;

public interface ConsumerService {
    ConsumerResponseDTO registerConsumer(ConsumerRegisterDTO consumerRegisterDTO);

    List<ConsumerResponseDTO> getAllConsumers();

    ConsumerResponseDTO getConsumerById(String id);

    ConsumerResponseDTO updateConsumer(ConsumerRegisterDTO registerDTO, String id);
}
