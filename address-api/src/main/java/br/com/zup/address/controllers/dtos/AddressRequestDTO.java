package br.com.zup.address.controllers.dtos;


import br.com.zup.address.models.Address;
import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

    public AddressRequestDTO() {
    }

    public Address toEntity(){
        Address address = new Address();
        address.setStreet(this.street);
        address.setCity(this.city);
        address.setZipCode(this.zipCode);
        address.setState(this.state);
        address.setConsumerId(this.consumerId);
        return address;
    }
}
