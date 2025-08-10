package com.lucaslozardo.challenge_backend7.controller;

import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/nome/{name}")
    public List<DepoimentoDTO> obterPorNome(@PathVariable String name){
        return depoimentoService.obterDepoimentosPorNome(name);
    }

    @DeleteMapping("/todos")
    public void apagarTodosOsDepoimentos(){
        depoimentoService.apagarTodosOsDepoimentos();
    }

    @DeleteMapping("/{id}")
    public void apagarDepoimentoPorId(@PathVariable Long id){
        depoimentoService.apagarDepoimentoporId(id);
    }

    @DeleteMapping("/nome/{name}")
    public void apagarDepoimentosPorNome(String name){
        depoimentoService.apagarDepoimentosPorNome(name);
    }

}
