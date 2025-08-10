package com.lucaslozardo.challenge_backend7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DestinoRepository extends JpaRepository<String, Long> {
}
