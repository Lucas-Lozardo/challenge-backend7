package com.lucaslozardo.challenge_backend7.controller;

import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.model.Depoimento;
import com.lucaslozardo.challenge_backend7.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        depoimentoService.apagarDepoimentoPorId(id);
    }

    @DeleteMapping("/nome/{name}")
    public void apagarDepoimentosPorNome(@PathVariable String name){
        depoimentoService.apagarDepoimentosPorNome(name);
    }

    @PostMapping
    public ResponseEntity<DepoimentoDTO> inserirDepoimento(@RequestBody DepoimentoDTO dto){
        Depoimento depoimentoSalvo = depoimentoService.InserirNovoDepoimento(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DepoimentoDTO(depoimentoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepoimentoDTO> atualizarDepoimento(@PathVariable Long id, @RequestBody DepoimentoDTO dto){
        DepoimentoDTO atualizado = depoimentoService.atualizarDepoimento(id, dto);

        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
