package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ConsumerRequestDTO {

    @Size(min = 3, message = "field not valid")
    private String name;

    @Min(value = 18, message = "You cant be less than 18 years old")
    @Max(value = 90, message = "You cant be more than 90 years old")
    private String age;

    @NotNull(message = "this field cant be null")
    private String email;

    public ConsumerRequestDTO() {
    }

    public Consumer toEntity() {
        Consumer consumer = new Consumer();
        consumer.setName(this.name);
        consumer.setAge(this.age);
        consumer.setEmail(this.email);
        return consumer;
    }
}
