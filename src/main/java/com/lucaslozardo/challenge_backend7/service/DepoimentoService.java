package com.lucaslozardo.challenge_backend7.service;

import com.lucaslozardo.challenge_backend7.dto.DepoimentoDTO;
import com.lucaslozardo.challenge_backend7.model.Depoimento;
import com.lucaslozardo.challenge_backend7.repository.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepoimentoService {

    @Autowired
    private DepoimentoRepository repository;

    //CONVERSOR DE DEPOIMENTOS EM DTO
    public List<DepoimentoDTO> converteDadosDTO(List<Depoimento> depoimentos){
        return depoimentos.stream()
                .map(d -> new DepoimentoDTO(d.getId(), d.getName(), d.getDepoimento(), d.getUrlFoto()))
                .collect(Collectors.toList());
    }

    //GET ALL
    public List<DepoimentoDTO> obterTodosOsDepoimentos(){
        return converteDadosDTO(repository.findAll());
    }

    //GET FOR ID
    public DepoimentoDTO obterDepoimentoPorId(Long id){
        Optional<Depoimento> depoimento = repository.findById(id);

        if (depoimento.isPresent()){
            Depoimento d = depoimento.get();
            return new DepoimentoDTO(d.getId(),
                    d.getName(),
                    d.getDepoimento(),
                    d.getUrlFoto());
        } else {
            return null;
        }
    }

    // GET FOR NAME
    public List<DepoimentoDTO> obterDepoimentosPorNome(String name){
        return converteDadosDTO(repository.findByNameContainingIgnoringCase(name));
    }

    // DELETE ALL
    public void apagarTodosOsDepoimentos(){
        if(repository.count() > 0){
            repository.deleteAll();
        }
    }

    // DELETE FOR ID
    public void apagarDepoimentoPorId(Long id){
        repository.findById(id).ifPresent(repository::delete);

    }

    // DELETE FOR NAME
    public void apagarDepoimentosPorNome(String name){
        List<Depoimento> depoimentos = repository.findByNameContainingIgnoringCase(name);
        if (!depoimentos.isEmpty()){
            repository.deleteAll(depoimentos);
        }
    }

    // POST DEPOIMENTO
    public Depoimento inserirNovoDepoimento (DepoimentoDTO dto){
        Depoimento novoDepoimento = new Depoimento(dto.name(), dto.depoimento(), dto.urlFoto());
        return repository.save(novoDepoimento);
    }

    // PUT DEPOIMENTO
    public DepoimentoDTO atualizarDepoimento(Long id, DepoimentoDTO dto){
        Optional<Depoimento> depoimentoExistente = repository.findById(id);

        if (depoimentoExistente.isPresent()){
            Depoimento d = depoimentoExistente.get();
            d.setName(dto.name());
            d.setDepoimento(dto.depoimento());
            d.setUrlFoto(dto.urlFoto());

            Depoimento atualizado = repository.save(d);
            return new DepoimentoDTO(atualizado);
        } else {
            return null;
        }

    }



}
