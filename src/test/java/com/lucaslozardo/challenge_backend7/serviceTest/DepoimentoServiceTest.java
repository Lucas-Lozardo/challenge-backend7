package com.lucaslozardo.challenge_backend7.serviceTest;

import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.model.Depoimento;
import com.lucaslozardo.challenge_backend7.repository.DepoimentoRepository;
import com.lucaslozardo.challenge_backend7.service.DepoimentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepoimentoServiceTest {

    @Mock
    DepoimentoRepository repositoryMock;        // Mock do repositório

    @InjectMocks
    DepoimentoService service;              // Classe que será testada

    @Test
    public void testarObterDepoimentoPorId_QuandoExiste(){
        //ARRANGE -- PREPARA O SENÁRIO
        Depoimento depoimento = new Depoimento("Pedro", "Depoimento teste", "urlFoto");
        depoimento.setId(1L);               // SIMULA O ID GERADO PELA JPA AO INSERIR NO BANCO DE DADOS

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(depoimento));

        //ACT -- EXECUTA O MÉTODO
        DepoimentoDTO dto = service.obterDepoimentoPorId(1L);

        //ASSERT -- VERIFICA OS RESULTADOS
        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("Pedro", dto.name());
        assertEquals("Depoimento teste", dto.depoimento());
        assertEquals("urlFoto", dto.urlFoto());
    }

    @Test
    public void testarObterDepoimentoPorId_QuandoNaoExiste(){
        //ARRANGE
        when(repositoryMock.findById(2L)).thenReturn(Optional.empty());

        //ACT
        DepoimentoDTO dto = service.obterDepoimentoPorId(2L);

        //ASSERT
        assertNull(dto);
    }
}
