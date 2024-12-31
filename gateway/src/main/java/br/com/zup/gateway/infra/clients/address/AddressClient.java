package br.com.zup.gateway.infra.clients.address;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
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


    public AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDto){
        try {
            return webClient.post()
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
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public AddressResponseDTO getAddress(String addressId){
        try {
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
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<AddressResponseDTO> getAllAddress(){
        return webClient
                .get()
                .uri(URL_BASE)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AddressResponseDTO>>() {})
                .block();
    }

}
