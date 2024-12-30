package br.com.zup.address.services;

import br.com.zup.address.controllers.dtos.AddressRequestDTO;
import br.com.zup.address.controllers.dtos.AddressResponseDTO;
import br.com.zup.address.models.Address;
import br.com.zup.address.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressResponseDTO createAddress(Address address) {
        Address addressSaved = addressRepository.save(address);
        return addressSaved.toDtoResponse();
    }

    public List<AddressResponseDTO> getAllAddresses() {
        List<AddressResponseDTO> dtoList = new ArrayList<>();
        List<Address> addressList = addressRepository.findAll();

        for(Address address: addressList){
            dtoList.add(address.toDtoResponse());
        }
        return dtoList;
    }

    public AddressResponseDTO getAddressById(String id) {
        return getFromRepository(id).toDtoResponse();
    }

    public AddressResponseDTO updateAddress(String id, AddressRequestDTO updatedAddress) {
        getFromRepository(id);
        Address addressUpdated = addressRepository.saveAndFlush(updatedAddress.toEntity());
        return addressUpdated.toDtoResponse();
    }

    public void deleteAddress(String id) {
        Address address = getFromRepository(id);
        addressRepository.deleteById(address.getId());
    }

    private Address getFromRepository(String id){
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()){
            throw new RuntimeException("Address not found with id :" + id);
        }
        return addressOptional.get();
    }
}