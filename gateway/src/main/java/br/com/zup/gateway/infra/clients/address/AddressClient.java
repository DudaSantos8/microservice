package br.com.zup.gateway.infra.clients.address;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
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
public class AddressClient {

    @Autowired
    private WebClient webClient;
    private final String URL_BASE = "http://localhost:8082/address";
    private static final Logger log = LoggerFactory.getLogger(AddressClient.class);


    public AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDto){
        try {
            log.info("accessing external address api to register");
            return webClient
                    .post()
                    .uri(URL_BASE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(addressRegisterDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Address error: " + body))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Server error: " + body))
                    )
                    .bodyToMono(AddressResponseDTO.class)
                    .block();
        } catch (Exception e) {
            log.info("catching external api exception");
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public AddressResponseDTO getAddress(String addressId){
        try {
            log.info("accessing external address api to get by id");
            return webClient
                    .get()
                    .uri(URL_BASE+"/"+addressId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Address error: " + body))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Server error: " + body))
                    )
                    .bodyToMono(AddressResponseDTO.class)
                    .block();
        }catch (Exception e){
            log.info("catching external api exception (get by id)");
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<AddressResponseDTO> getAllAddress(){
        log.info("accessing external address api to get all");
        return webClient
                .get()
                .uri(URL_BASE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AddressResponseDTO>>() {})
                .block();
    }

    public AddressResponseDTO updateAddress(String id, AddressRegisterDTO addressRegisterDto){
        try {
            log.info("accessing external address api to update");
            return webClient
                    .put()
                    .uri(URL_BASE+"/"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(addressRegisterDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Address error: " + body))
                    )
                    .onStatus(HttpStatusCode::is5xxServerError, response ->
                            response.bodyToMono(String.class)
                                    .map(body -> new RuntimeException("Server error: " + body))
                    )
                    .bodyToMono(AddressResponseDTO.class)
                    .block();
        } catch (Exception e) {
            log.info("catching external api exception (update)");
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public void deleteAddress(String id){
        try {
            log.info("accessing external address api to delete");
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
