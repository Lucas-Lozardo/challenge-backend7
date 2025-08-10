package com.lucaslozardo.challenge_backend7.repository;

import com.lucaslozardo.challenge_backend7.model.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

    List<Depoimento> findByNameContainingIgnoringCase(String name);
}
