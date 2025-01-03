package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.consumerAddress.ConsumerAddressService;
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

@WebMvcTest(ConsumerAddressController.class)
public class ConsumerAddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsumerAddressService consumerAddressService;

    private ObjectMapper mapper;
    private ConsumerAddressResponseDTO consumerAddressResponseDTO;


    @BeforeEach
    public void setUp(){
        mapper = new ObjectMapper();

        ConsumerResponseDTO consumerResponseDTO = new ConsumerResponseDTO();

        consumerResponseDTO.setId("9867b2de-10d7-4aec-9135-9abd9a934eb3");
        consumerResponseDTO.setName("Duda");
        consumerResponseDTO.setAge("19");
        consumerResponseDTO.setEmail("duds123@gmail.com");

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        addressResponseDTO.setConsumerId("9867b2de-10d7-4aec-9135-9abd9a934eb3");
        addressResponseDTO.setCity("São Paulo");
        addressResponseDTO.setState("SP");
        addressResponseDTO.setStreet("Rua das Flores");
        addressResponseDTO.setZipCode("12345-678");

        consumerAddressResponseDTO = new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
    }

    @Test
    public void testWhenRegisterConsumerHappyPath() throws Exception {
        AddressRegisterDTO addressRegisterDTO = new AddressRegisterDTO("Rua das Flores", "São Paulo", "12345-678",
                "SP", "9867b2de-10d7-4aec-9135-9abd9a934eb3");
        ConsumerAddressRegisterDTO consumerAddressRegisterDTO = new ConsumerAddressRegisterDTO("Duda", "19", "duds123@gmail.com", addressRegisterDTO);
        String json = mapper.writeValueAsString(consumerAddressRegisterDTO);

        Mockito.when(consumerAddressService.registerConsumerAddress(Mockito.any(ConsumerAddressRegisterDTO.class))).thenReturn(consumerAddressResponseDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/consumer-address")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Duda")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is("19")));
    }
}
