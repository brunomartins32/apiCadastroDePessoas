package com.example.cadastro.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cadastro.api.model.Pessoa;
import com.example.cadastro.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	@Autowired
	private PessoaRepository pessoaRepository;

	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloId(@PathVariable int codigo) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		return !pessoa.isEmpty() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable int codigo){
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public Pessoa atualizar(@PathVariable int codigo, @Valid @RequestBody Pessoa pessoa){
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).get();
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
}
