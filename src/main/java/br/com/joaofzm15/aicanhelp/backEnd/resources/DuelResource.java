package br.com.joaofzm15.aicanhelp.backEnd.resources;

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

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;
import br.com.joaofzm15.aicanhelp.backEnd.services.DuelService;


//RequestMapping value is the URL to access this
@RestController
@RequestMapping(value = "/duels")
public class DuelResource {
	
	@Autowired
	private DuelService service;
	
	//Returns answers of web requisitions
	@GetMapping
	public ResponseEntity<List<Duel>> findAll(){
		List<Duel> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Duel> findById(@PathVariable Long id){
		Duel obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Duel> insert(@RequestBody Duel obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

}
