package br.com.zup.address.models;

import br.com.zup.address.controllers.dtos.AddressResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
public class Address {

    @Id
    @UuidGenerator
    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    @Column(nullable = false)
    private String consumerId;

    public Address() {
    }

    public AddressResponseDTO toDtoResponse(){
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setId(this.id);
        responseDTO.setStreet(this.street);
        responseDTO.setCity(this.city);
        responseDTO.setZipCode(this.zipCode);
        responseDTO.setState(this.state);
        responseDTO.setConsumerId(this.consumerId);
        return responseDTO;
    }
}
