package br.com.zup.consumer.models;

import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
public class Consumer {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String age;
    private String email;

    public Consumer() {
    }

    public ConsumerResponseDTO toDtoResponse() {
        ConsumerResponseDTO dto = new ConsumerResponseDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setAge(this.age);
        dto.setEmail(this.email);
        return dto;
    }
}
