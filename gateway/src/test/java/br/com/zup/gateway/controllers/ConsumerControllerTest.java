package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.consumer.ConsumerService;
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

@WebMvcTest(ConsumerController.class)
public class ConsumerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConsumerService consumerService;

    private ObjectMapper mapper;
    private ConsumerResponseDTO consumerResponseDTO;


    @BeforeEach
    public void setUp(){
        mapper = new ObjectMapper();

        consumerResponseDTO = new ConsumerResponseDTO();

        consumerResponseDTO.setId("9867b2de-10d7-4aec-9135-9abd9a934eb3");
        consumerResponseDTO.setName("Duda");
        consumerResponseDTO.setAge("19");
        consumerResponseDTO.setEmail("duds123@gmail.com");
    }

    @Test
    public void testWhenRegisterConsumerHappyPath() throws Exception {
        ConsumerRegisterDTO consumerRegisterDTO = new ConsumerRegisterDTO("Duda", "19", "duds123@gmail.com");
        String json = mapper.writeValueAsString(consumerRegisterDTO);

        Mockito.when(consumerService.registerConsumer(Mockito.any(ConsumerRegisterDTO.class))).thenReturn(consumerResponseDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/consumer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(consumerResponseDTO.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Duda")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is("19")));
    }
}
