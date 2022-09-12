package com.example.cadastro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.api.dto.PessoaDTO;
import com.example.cadastro.api.service.PessoaService;

//TODO - Mudar o status do ResponseEntity, pode ser ok() a não ser que seja um SAVE, ai seria create
//TODO - Mudar os tipos do ResponseEntity
//TODO - Mudar a injeção de dependência

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<PessoaDTO> cadastrar(@RequestBody PessoaDTO pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
	}

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> listar() {
		return ResponseEntity.ok(pessoaService.buscarTodos());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long codigo) {
		return ResponseEntity.ok(pessoaService.findById(codigo));
	}

	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable Long codigo) {
		pessoaService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long codigo, @RequestBody PessoaDTO pessoadto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.atualizar(codigo, pessoadto));
	}
}
