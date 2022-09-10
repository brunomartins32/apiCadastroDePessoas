package com.example.cadastro.api.service;

import java.util.Optional;

import com.example.cadastro.api.dto.PessoaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cadastro.api.model.Pessoa;
import com.example.cadastro.api.repository.PessoaRepository;

@Service

public class PessoaService {


	// Usar autowired não é incorreto, porem a melhor prática está em usar a injeção de dependência por construtor
//	@Autowired
//	PessoaRepository pessoaRepository;

	//alguns detalhes:
	// PRIVATE -> pois deve ser apenas ser acessado pela classe 'PessoaService', então vamos deixa private
	// final -> vamos garantir que essa variável seja imutável, ou seja, ela vai ter um único valor que será
	// injetado pelo construtor, assim garantimos consistência da injeção de dependência
	private final PessoaRepository pessoaRepository;

	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	//Aqui estamos usando o DTO vindo externamente e jogando ele pro banco de dados usando o toModel
	public PessoaDTO save(PessoaDTO pessoaSalva) {
		Pessoa pessoa = pessoaSalva.toModel();

		pessoa = pessoaRepository.save(pessoa);

		PessoaDTO pessoaDTO = PessoaDTO.toDTO(pessoa);

		return pessoaDTO;
	}

	public Object findAll() {
		return pessoaRepository.findAll();
	}

//	public ResponseEntity<Optional<Pessoa>> findById(int codigo) {
//		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Not found"))); ;
//		return ResponseEntity.ok().body(pessoa);
//	}

	//Aqui está um exemplo do uso do nosso DTO
	public PessoaDTO findById(int id){


		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found"));

		//Aqui nós usamos o nosso método para transformar o retorno do método 'FindById' que faz parte do repository

		PessoaDTO pessoaDTO = PessoaDTO.toDTO(pessoa);

		return pessoaDTO;
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
