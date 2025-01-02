package br.com.zup.gateway.services.address;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;

import java.util.List;

public interface AddressService {
    AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDTO);

    List<AddressResponseDTO> getAllAddress();

    AddressResponseDTO getAddressById(String id);

    AddressResponseDTO updateAddress(AddressRegisterDTO registerDTO, String id);

    void deleteAddress(String id);
}
