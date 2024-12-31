package br.com.zup.gateway.infra.clients.address.dtos;

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

    public AddressRegisterDTO toAddressRegisterDto(){
        AddressRegisterDTO registerDto = new AddressRegisterDTO();

        registerDto.setStreet(this.street);
        registerDto.setCity(this.city);
        registerDto.setZipCode(this.zipCode);
        registerDto.setState(this.state);
        registerDto.setConsumerId(this.consumerId);

        return registerDto;
    }

}
