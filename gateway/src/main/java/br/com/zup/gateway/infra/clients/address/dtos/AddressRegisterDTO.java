package br.com.zup.gateway.infra.clients.address.dtos;

import lombok.Data;

@Data
public class AddressRegisterDTO {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

    public AddressRegisterDTO() {
    }

    public AddressResponseDTO toAddressResponseDto(){
        AddressResponseDTO responseDTO = new AddressResponseDTO();

        responseDTO.setStreet(this.street);
        responseDTO.setCity(this.city);
        responseDTO.setZipCode(this.zipCode);
        responseDTO.setState(this.state);
        responseDTO.setConsumerId(this.consumerId);

        return responseDTO;
    }
}
