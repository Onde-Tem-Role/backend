package org.generation.ondetemrole.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.ondetemrole.model.Postagem;
import org.generation.ondetemrole.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   //indica que a classe é um controller
@RequestMapping("/postagem")   //indica por qual RI essa classe será acessada, no caso /postagem
@CrossOrigin("*")   //indica que essa classe vai aceitar requisão de qq origem
public class PostagemController {
	
	@Autowired   
	private PostagemRepository repository;

	@GetMapping   
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipoTurismo/{tipoTurismo}")
	public ResponseEntity<List<Postagem>> getBytipoTurismo(@PathVariable String tipoTurismo) {
		return ResponseEntity.ok(repository.findAllByTipoTurismoContainingIgnoreCase(tipoTurismo));
	}
	
	@GetMapping("/texto/{texto}")
	public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto) {
		return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
	}
	
	@PostMapping 
	public ResponseEntity<Postagem> post(@RequestBody @Valid Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
