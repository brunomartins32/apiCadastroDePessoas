package com.example.cadastro.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.cadastro.api.dto.PessoaDTO;
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

		pessoaRepository.save(pessoa);

		PessoaDTO pessoaDTO = PessoaDTO.toDTO(pessoa);

		return pessoaDTO;
	}


	public List<PessoaDTO> buscarTodos() {
		 return pessoaRepository.findAll().stream().map(PessoaDTO::toDTO).collect(Collectors.toList());
	}

//	public ResponseEntity<Optional<Pessoa>> findById(int codigo) {
//		Optional<Pessoa> pessoa = Optional.of(pessoaRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException("Not found"))); ;
//		return ResponseEntity.ok().body(pessoa);
//	}

	//Aqui está um exemplo do uso do nosso DTO
	public PessoaDTO findById(Long id){
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("not found"));
		//Aqui nós usamos o nosso método para transformar o retorno do método 'FindById' que faz parte do repository
		PessoaDTO pessoaDTO = PessoaDTO.toDTO(pessoa);
		return pessoaDTO;
	}

	public void deleteById(Long codigo) {
		findById(codigo);
		pessoaRepository.deleteById(codigo);

	}

	public PessoaDTO atualizar(Long codigo, PessoaDTO pessoadto) {
		
		Pessoa pessoaNova = pessoadto.toModel();
		pessoaNova = pessoaRepository.getReferenceById(codigo);
		BeanUtils.copyProperties(pessoadto, pessoaNova, "codigo");
		pessoaRepository.save(pessoaNova);
		
		PessoaDTO pessoaDTO = PessoaDTO.toDTO(pessoaNova);

		return pessoaDTO;	
	}
}
