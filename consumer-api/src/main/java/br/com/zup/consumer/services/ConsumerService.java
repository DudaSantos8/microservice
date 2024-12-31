package br.com.zup.consumer.services;

import br.com.zup.consumer.controllers.dtos.ConsumerRequestDTO;
import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import br.com.zup.consumer.repositories.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.consumer.models.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private ConsumerRepository consumerRepository;

    public ConsumerResponseDTO createConsumer(Consumer consumer) {
        Consumer consumerSaved = consumerRepository.save(consumer);
        return consumerSaved.toDtoResponse();
    }

    public List<ConsumerResponseDTO> getAllConsumers() {
        List<ConsumerResponseDTO> dtoList = new ArrayList<>();
        List<Consumer> addressList = consumerRepository.findAll();

        for (Consumer consumer : addressList) {
            dtoList.add(consumer.toDtoResponse());
        }
        return dtoList;
    }

    public ConsumerResponseDTO getConsumerById(String id) {
        return getFromRepository(id).toDtoResponse();
    }

    public ConsumerResponseDTO updateConsumer(String id, ConsumerRequestDTO updatedConsumer) {
        Optional<Consumer> optional = consumerRepository.findById(id);
        if(optional.isPresent()){
            optional.get().setName(updatedConsumer.getName());
            optional.get().setAge(updatedConsumer.getAge());
            optional.get().setEmail(updatedConsumer.getEmail());
            return consumerRepository.save(optional.get()).toDtoResponse();
        }else{
            throw new RuntimeException("Consumer not found with id :" + id);
        }
    }

    public void deleteConsumer(String id) {
        Consumer consumer = getFromRepository(id);
        consumerRepository.deleteById(consumer.getId());
    }

    private Consumer getFromRepository(String id){
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        if (consumerOptional.isEmpty()){
            throw new RuntimeException("Consumer not found with id :" + id);
        }
        return consumerOptional.get();
    }
}