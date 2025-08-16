package com.lucaslozardo.challenge_backend7.serviceTest;

import com.lucaslozardo.challenge_backend7.controller.DepoimentoController;
import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.model.Depoimento;
import com.lucaslozardo.challenge_backend7.service.DepoimentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//PARA TESTES INTEGRADOS
//@SpringBootTest
//@AutoConfigureMockMvc                             //PERMITE INJETAR O MOCKMVC PARA SIMULAR REQUISIÇÕES HTTP.

@WebMvcTest(DepoimentoController.class)             //PARA TESTES SOMENTE COM O CONTROLLER
public class DepoimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //PARA NÃO DEPENDER DO BANCO DE DADOS
    @MockitoBean
    private DepoimentoService service;

    @Test
    public void testarGetDepoimentos_RetornarOk() throws Exception{
        mockMvc.perform(get("/depoimentos/todos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testarPostDepoimento_RetornaCreated() throws Exception{
        String json = """
                {
                "name": "Paulo",
                "depoimento": "Muito bom",
                "urlFoto": "http://fotoPaulo.jpg"
                }
        """;

        Depoimento depoimentoMock = new Depoimento("Paulo", "Muito bom", "http://fotoPaulo.jpg");
        depoimentoMock.setId(1L);

        when(service.inserirNovoDepoimento(any(DepoimentoDTO.class))).thenReturn(depoimentoMock);


        mockMvc.perform(post("/depoimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));

    }
}
