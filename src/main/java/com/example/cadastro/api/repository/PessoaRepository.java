package com.example.cadastro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
