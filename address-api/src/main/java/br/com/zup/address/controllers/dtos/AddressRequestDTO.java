package br.com.zup.address.controllers.dtos;


import br.com.zup.address.models.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequestDTO {
    @NotNull(message = "this field cant be null")
    private String street;

    @NotNull(message = "this field cant be null")
    private String city;

    @NotNull(message = "this field cant be null")
    private String zipCode;

    @NotNull(message = "this field cant be null")
    private String state;

    @NotNull(message = "this field cant be null")
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
