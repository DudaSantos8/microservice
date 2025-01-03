package br.com.zup.gateway.controllers.dtos;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import lombok.Data;

@Data
public class ConsumerAddressRegisterDTO {
    private String name;
    private String age;
    private String email;
    private AddressRegisterDTO address;

    public ConsumerAddressRegisterDTO(String name, String number, String mail, AddressRegisterDTO addressRegisterDTO) {
    }

    public ConsumerResponseDTO toConsumerResponseDTO(){
        ConsumerResponseDTO responseDTO = new ConsumerResponseDTO();
        responseDTO.setName(this.name);
        responseDTO.setAge(this.age);
        responseDTO.setEmail(this.email);
        return responseDTO;
    }

    public AddressResponseDTO toAddressResponseDTO(){
        return this.address.toAddressResponseDto();
    }
}
