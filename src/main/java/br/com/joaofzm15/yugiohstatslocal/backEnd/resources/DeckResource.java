package br.com.joaofzm15.yugiohstatslocal.backEnd.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Deck;
import br.com.joaofzm15.yugiohstatslocal.backEnd.services.DeckService;

//RequestMapping value is the URL to access this
@RestController
@RequestMapping(value = "/decks")
public class DeckResource {
	
	@Autowired
	private DeckService service;
	
	//Returns answers of web requisitions
	@GetMapping
	public ResponseEntity<List<Deck>> findAll(){
		List<Deck> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Deck> findById(@PathVariable Long id){
		Deck obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Deck> insert(@RequestBody Deck obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

}
