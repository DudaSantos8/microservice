package br.com.zup.gateway.services.consumer;

import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerClient consumerClient;

    @Override
    public ConsumerResponseDTO registerConsumer(ConsumerRegisterDTO consumerRegisterDTO) {
        try {
            ConsumerResponseDTO consumerResponseDTO = consumerRegisterDTO.toConsumerResponseDto();
            return consumerClient.registerConsumerClient(consumerResponseDTO.toConsumerRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering consumer: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ConsumerResponseDTO> getAllConsumers(){
        return consumerClient.getAllConsumers();
    }

    @Override
    public ConsumerResponseDTO getConsumerById(String id) {
        try {
            return consumerClient.getConsumer(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while get consumer: " + e.getMessage(), e);
        }
    }
}
