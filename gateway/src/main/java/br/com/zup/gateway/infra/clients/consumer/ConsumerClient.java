package br.com.zup.gateway.infra.clients.consumer;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ConsumerClient {

    @Autowired
    private WebClient webClient;
    private final String URL_BASE = "http://localhost:8081/consumer";

    public ConsumerResponseDTO registerConsumerClient(ConsumerRegisterDTO registerDTO) {
        try {
            return webClient
                    .post()
                    .uri(URL_BASE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(registerDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Client error: " + body))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Server error: " + body))
                    )
                    .bodyToMono(ConsumerResponseDTO.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public ConsumerResponseDTO getConsumer(String consumerId){
        try {
            return webClient
                    .get()
                    .uri(URL_BASE+"/"+consumerId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Client error: " + body))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Server error: " + body))
                    )
                    .bodyToMono(ConsumerResponseDTO.class)
                    .block();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<ConsumerResponseDTO> getAllConsumers(){
        return webClient
                .get()
                .uri(URL_BASE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ConsumerResponseDTO>>() {})
                .block();
    }
}
