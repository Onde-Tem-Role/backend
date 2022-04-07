

	package org.generation.ondetemrole.controller;

	import java.util.List;

import javax.validation.Valid;

import org.generation.ondetemrole.model.Postagem;
import org.generation.ondetemrole.model.Usuario;
import org.generation.ondetemrole.repository.UsuarioRepository;
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

	@RestController
	@RequestMapping("/usuario")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class UsuarioController {

		@Autowired   
		private UsuarioRepository repository;

		@GetMapping   
		public ResponseEntity<List<Usuario>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Usuario> getById(@PathVariable Long id) {
			return repository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
		}
		
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome) {
			return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
		}
		
		
		@PostMapping 
		public ResponseEntity<Usuario> post(@RequestBody @Valid Usuario Usuario) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(Usuario));
		}
		
		@PutMapping
		public ResponseEntity<Usuario> put(@RequestBody Usuario Usuario) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(Usuario));
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id) {
			repository.deleteById(id);
		}
		
	}
	

