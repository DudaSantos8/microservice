package br.com.zup.address.controllers.dtos;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

    public AddressResponseDTO() {

    }

    public AddressResponseDTO(String id, String street, String city, String zipCode, String state, String consumerId) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
        this.consumerId = consumerId;
    }

}
