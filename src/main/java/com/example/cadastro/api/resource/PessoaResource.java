package com.example.cadastro.api.resource;

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

import com.example.cadastro.api.model.Pessoa;
import com.example.cadastro.api.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	// @Autowired
	// private PessoaRepository pessoaRepository;

	@Autowired
	PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
	}

	@GetMapping
	public ResponseEntity<Object> listar() {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.findAll());
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloId(@PathVariable int codigo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.findById(codigo));
	}

	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable int codigo) {
		pessoaService.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable int codigo, @RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.atualizar(codigo, pessoa));
	}
}
