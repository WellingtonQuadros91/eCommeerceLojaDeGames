package com.generation.LojaDeGames.controller;

import java.util.List;

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

import com.generation.LojaDeGames.model.Categorias;
import com.generation.LojaDeGames.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriasController {

	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Categorias>> GetAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorias> GetById(@PathVariable Long id){
		return categoriasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categorias>> GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(categoriasRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Categorias> POSTCategorias (@RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categorias> PUTCategorias (@RequestBody Categorias categorias){
		
		return categoriasRepository.findById(categorias.getId())
				.map(respostas -> ResponseEntity.ok().body(categoriasRepository.save(categorias)))
				.orElse(ResponseEntity.notFound().build());
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategorias(@PathVariable Long id) {
		
		return categoriasRepository.findById(id)
			.map(respostas -> {
			categoriasRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		})
		.orElse(ResponseEntity.notFound().build());
	}
}
