package br.com.zup.gateway.controllers.dtos;

import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import lombok.Data;

@Data
public class ConsumerAddressResponseDTO {
    private String id;
    private String name;
    private String age;
    private String email;
    private AddressDTO address;

    public ConsumerAddressResponseDTO(ConsumerResponseDTO consumerResponseDTO, AddressResponseDTO addressResponseDTO) {
        this.id = consumerResponseDTO.getId();
        this.name = consumerResponseDTO.getName();
        this.age = consumerResponseDTO.getAge();
        this.email = consumerResponseDTO.getEmail();
        this.address = new AddressDTO(addressResponseDTO);
    }
}
