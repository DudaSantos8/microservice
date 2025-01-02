package br.com.zup.gateway.services.address;

import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private AddressClient addressClient;

    @Override
    public AddressResponseDTO registerAddress(AddressRegisterDTO addressRegisterDTO) {
        try {
            try {
                consumerClient.getConsumer(addressRegisterDTO.getConsumerId());
            }catch (Exception e){
                throw new RuntimeException("Error while registering address: " + e.getMessage(), e);
            }

            List<AddressResponseDTO> addressResponseDTOList = getAllAddress();

            for(AddressResponseDTO addressResponseDTO : addressResponseDTOList){
                if(addressResponseDTO.getConsumerId().equals(addressRegisterDTO.getConsumerId())){
                    throw new RuntimeException("This consumer already have an address");
                }
            }

            AddressResponseDTO addressResponseDTO = addressRegisterDTO.toAddressResponseDto();
            return addressClient.registerAddress(addressResponseDTO.toAddressRegisterDto());
        } catch (Exception e) {
            throw new RuntimeException("Error while registering address: " + e.getMessage(), e);
        }
    }

    @Override
    public List<AddressResponseDTO> getAllAddress(){
        return addressClient.getAllAddress();
    }

    @Override
    public AddressResponseDTO getAddressById(String id) {
        try {
            return addressClient.getAddress(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while get address: " + e.getMessage(), e);
        }
    }

    @Override
    public AddressResponseDTO updateAddress(AddressRegisterDTO registerDTO, String id) {
        try {
            return addressClient.updateAddress(id, registerDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error while update address: " + e.getMessage(), e);
        }
    }
}
