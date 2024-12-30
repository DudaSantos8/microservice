package br.com.zup.consumer.controllers.dtos;

import lombok.Data;

@Data
public class ConsumerResponseDTO {
    private String id;
    private String name;
    private String age;
    private String email;
}