package br.com.zup.gateway.infra.clients.consumer;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ConsumerClient.class);

    public ConsumerResponseDTO registerConsumerClient(ConsumerRegisterDTO registerDTO) {
        try {
            log.info("accessing external consumer api to register");
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
            log.info("catching external api exception");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public ConsumerResponseDTO getConsumer(String consumerId){
        try {
            log.info("accessing external consumer api to get by id");
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
            log.info("catching external api exception (get by id)");
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

    public ConsumerResponseDTO updateConsumer(String id, ConsumerRegisterDTO registerDTO) {
        try {
            log.info("accessing external consumer api to update");
            return webClient
                    .put()
                    .uri(URL_BASE+"/"+id)
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
            log.info("catching external api exception (update)");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void deleteConsumer(String id){
        try {
            log.info("accessing external consumer api to delete");
            webClient
                    .delete()
                    .uri(URL_BASE+"/"+id)
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
            log.info("catching external api exception (delete)");
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
