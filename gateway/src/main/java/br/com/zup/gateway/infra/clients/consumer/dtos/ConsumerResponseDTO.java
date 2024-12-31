package br.com.zup.gateway.infra.clients.consumer.dtos;

import lombok.Data;

@Data
public class ConsumerResponseDTO {
    private String id;
    private String name;
    private String age;
    private String email;

    public ConsumerResponseDTO() {
    }

    public ConsumerRegisterDTO toConsumerRegisterDto(){
        ConsumerRegisterDTO registerDTO = new ConsumerRegisterDTO();
        registerDTO.setName(this.name);
        registerDTO.setAge(this.age);
        registerDTO.setEmail(this.email);
        return registerDTO;
    }
}
