package com.example.cadastro.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cadastro.api.model.Pessoa;
import com.example.cadastro.api.repository.PessoaRepository;

@Service

public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa save(Pessoa pessoaSalva) {
		return pessoaRepository.save(pessoaSalva);
	}

	public Object findAll() {
		return pessoaRepository.findAll();
	}

	public ResponseEntity<Optional<Pessoa>> findById(int codigo) {
		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Not found"))); ;
		return ResponseEntity.ok().body(pessoa);
	}

	public void deleteById(int codigo) {
		findById(codigo);
		pessoaRepository.deleteById(codigo);

	}

	public Pessoa atualizar(int codigo, Pessoa pessoa) {
		Pessoa atual = pessoaRepository.getReferenceById(codigo);
		BeanUtils.copyProperties(pessoa, atual, "codigo");
		return pessoaRepository.save(atual);
	}
}
