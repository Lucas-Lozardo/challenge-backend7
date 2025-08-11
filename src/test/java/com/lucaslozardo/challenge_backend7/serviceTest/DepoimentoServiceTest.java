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
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void testarObterTodosOsDepoimentos_QuandoExiste(){
        //ARRANGE
        Depoimento dep1 = new Depoimento("Pedro", "Depoimento teste", "urlFoto");
        dep1.setId(1L);
        Depoimento dep2 = new Depoimento("João", "Depoimento teste2", "urlFoto");
        dep2.setId(2L);
        Depoimento dep3 = new Depoimento("Marcos", "Depoimento teste3", "urlFoto");
        dep3.setId(3L);

        when(repositoryMock.findAll()).thenReturn(List.of(dep1, dep2, dep3));

        //ACT
        List<DepoimentoDTO> dto = service.obterTodosOsDepoimentos();

        //ASSERT
        assertNotNull(dto);
    }

    @Test
    public void testarObterTodosOsDepoimentos_QuandoNaoExiste(){
        //ARRANGE
        when(repositoryMock.findAll()).thenReturn(List.of());

        //ACT
        List<DepoimentoDTO> dto = service.obterTodosOsDepoimentos();

        //ASSERT
        assertNotNull(dto);             //A LISTA NÃO ESTÁ NULL E SIM [] VAZIA
        assertTrue(dto.isEmpty());      // LISTA [] VAZIA
    }

    @Test
    public void testarObterDepoimentosPorNome_QuandoExistirApenasUmDepoimento(){
        //ARRANGE
        Depoimento dep = new Depoimento("Pedro", "Depoimento teste", "urlFoto");
        dep.setId(1L);

        when(repositoryMock.findByNameContainingIgnoringCase("Pedro")).thenReturn(List.of(dep));

        //ACT
        List<DepoimentoDTO> dtos = service.obterDepoimentosPorNome("Pedro");

        //ASSERT
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(1, dtos.size());
        assertEquals("Pedro", dtos.get(0).name());
    }

    @Test
    public void testarObterDepoimentosPorNome_QuandoExistiremMaisDeUmDepoimento(){
        //ARRANGE
        Depoimento dep1 = new Depoimento("Pedro", "Depoimento teste", "urlFoto");
        dep1.setId(1L);
        Depoimento dep2 = new Depoimento("Pedro", "Depoimento teste2", "urlFoto");
        dep2.setId(2L);

        when(repositoryMock.findByNameContainingIgnoringCase("Pedro")).thenReturn(List.of(dep1, dep2));

        //ACT
        List<DepoimentoDTO> dtos = service.obterDepoimentosPorNome("Pedro");

        //ASSERT
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
        assertEquals(2, dtos.size());
        assertEquals("Pedro", dtos.get(0).name());
    }

    @Test
    public void testarObterDepoimentosPorNome_QuandoNaoExistiremDepoimentos(){
        //ARRANGE
        when(repositoryMock.findByNameContainingIgnoringCase("")).thenReturn(List.of());

        //ACT
        List<DepoimentoDTO> dto = service.obterDepoimentosPorNome("");

        //ASSERT
        assertNotNull(dto);
        assertTrue(dto.isEmpty());
    }

    @Test
    public void testarApagarTodosOsDepoimentosPorNome_QuandoExistiremDepoimentos(){
        //ARRANGE
        Depoimento dep1 = new Depoimento("João", "Depoimento teste", "urlFoto");
        dep1.setId(1L);
        Depoimento dep2 = new Depoimento("João", "Depoimento teste2", "urlFoto");
        dep2.setId(2L);

        when(repositoryMock.findByNameContainingIgnoringCase("João")).thenReturn(List.of(dep1, dep2));
        //ACT
        service.apagarDepoimentosPorNome("João");

        //ASSERT
        //verify(mockObjeto, times(quantidade)).metodoEsperado(argumentosEsperados); VERIFY POR SE TRATAR DE VOID.
        verify(repositoryMock, times(1)).deleteAll(List.of(dep1, dep2));
    }

    @Test
    public void testarApagarTodosOsDepoimentosPorNome_QuandoNaoExistiremDepoimentos(){
        //ARRANGE
        when(repositoryMock.findByNameContainingIgnoringCase("")).thenReturn(List.of());

        //ACT
        service.apagarDepoimentosPorNome("");

        //ASSERT
        verify(repositoryMock, times(0)).deleteAll(any());
    }

    @Test
    public void testarApagarDepoimentoPorId_QuandoExistir(){
        //ARRANGE
        Depoimento dep1 = new Depoimento("João", "Depoimento teste", "urlFoto");
        dep1.setId(1L);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(dep1));

        //ACT
        service.apagarDepoimentoPorId(1L);

        //ASSERT
        verify(repositoryMock, times(1)).delete(dep1);
    }

    @Test
    public void testarApagarDepoimentoPorId_QuandoNaoExistir(){
        //ARRANGE
        when(repositoryMock.findById(1L)).thenReturn(Optional.empty());

        //ACT
        service.apagarDepoimentoPorId(1L);

        //ASSERT
        verify(repositoryMock, times(0)).delete(any());
    }

    @Test
    public void testarApagarTodosOsDepoimentos_QuandoExistirem(){
        //ARRANGE
        when(repositoryMock.count()).thenReturn(3L);

        //ACT
        service.apagarTodosOsDepoimentos();

        //ASSERT
        verify(repositoryMock, times(1)).deleteAll();
    }

    @Test
    public void testarApagarTodosOsDepoimentos_QuandoNaoExistirem(){
        //ARRANGE
        when(repositoryMock.count()).thenReturn(0L);

        //ACT
        service.apagarTodosOsDepoimentos();

        //ASSERT
        verify(repositoryMock, never()).deleteAll();
    }
}
