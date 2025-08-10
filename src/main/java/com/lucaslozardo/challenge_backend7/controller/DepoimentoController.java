package com.lucaslozardo.challenge_backend7.controller;

import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService depoimentoService;

    @GetMapping("/todos")
    public List<DepoimentoDTO> obterDepoimentos() {
        return depoimentoService.obterTodosOsDepoimentos();
    }

    @GetMapping("/{id}")
    public DepoimentoDTO obterPorId(@PathVariable Long id){
        return  depoimentoService.obterDepoimentoPorId(id);
    }

    @GetMapping("/nome")
    public List<DepoimentoDTO> obterPorNome(@PathVariable String name){
        return depoimentoService.obterDepoimentosPorNome(name);
    }

}
