package com.lucaslozardo.challenge_backend7.dto;

import com.lucaslozardo.challenge_backend7.model.Depoimento;

public record DepoimentoDTO(Long id,
                            String name,
                            String depoimento,
                            String urlFoto) {

    public DepoimentoDTO(Depoimento depoimento) {
        this(depoimento.getId(), depoimento.getName(), depoimento.getDepoimento(), depoimento.getUrlFoto());
    }
}
