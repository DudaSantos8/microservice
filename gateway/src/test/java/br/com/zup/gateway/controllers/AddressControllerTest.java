package br.com.zup.gateway.controllers;


import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.services.address.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressService addressService;

    private ObjectMapper mapper;
    private AddressResponseDTO addressResponseDTO;


    @BeforeEach
    public void setUp(){
        mapper = new ObjectMapper();

        addressResponseDTO = new AddressResponseDTO();

        addressResponseDTO.setConsumerId("9867b2de-10d7-4aec-9135-9abd9a934eb3");
        addressResponseDTO.setCity("São Paulo");
        addressResponseDTO.setState("SP");
        addressResponseDTO.setStreet("Rua das Flores");
        addressResponseDTO.setZipCode("12345-678");
    }

    @Test
    public void testWhenRegisterAddressHappyPath() throws Exception {
        AddressRegisterDTO addressRegisterDTO = new AddressRegisterDTO("Rua das Flores", "São Paulo", "12345-678",
                "SP", "9867b2de-10d7-4aec-9135-9abd9a934eb3");
        String json = mapper.writeValueAsString(addressRegisterDTO);

        Mockito.when(addressService.registerAddress(Mockito.any(AddressRegisterDTO.class))).thenReturn(addressResponseDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/address")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(addressResponseDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", CoreMatchers.is("São Paulo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerId", CoreMatchers.is("9867b2de-10d7-4aec-9135-9abd9a934eb3")));
    }

}
