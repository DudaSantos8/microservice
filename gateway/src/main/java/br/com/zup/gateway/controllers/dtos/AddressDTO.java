package br.com.zup.gateway.controllers.dtos;

import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

    public AddressDTO() {
    }

    public AddressDTO(AddressResponseDTO addressResponseDTO) {
        this.city = addressResponseDTO.getCity();
        this.consumerId = addressResponseDTO.getConsumerId();
        this.zipCode = addressResponseDTO.getZipCode();
        this.state = addressResponseDTO.getState();
        this.street = addressResponseDTO.getStreet();
    }

    public AddressResponseDTO toAddressResponseDTO(){
        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setStreet(this.street);
        responseDTO.setCity(this.city);
        responseDTO.setZipCode(this.zipCode);
        responseDTO.setState(this.state);
        responseDTO.setConsumerId(this.consumerId);

        return responseDTO;
    }
}
