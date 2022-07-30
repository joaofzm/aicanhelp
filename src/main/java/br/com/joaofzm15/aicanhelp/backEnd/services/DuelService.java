package br.com.joaofzm15.aicanhelp.backEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;
import br.com.joaofzm15.aicanhelp.backEnd.repositories.DuelRepository;


@Service
public class DuelService {
	
	@Autowired
	private DuelRepository repository;
	
	public List<Duel> findAll(){
		return repository.findAll();
	}
	
	public Duel findById(Long id) {
		Optional<Duel> obj = repository.findById(id);
		return obj.get();
	}
	
	public Duel insert (Duel obj) {
		return repository.save(obj);
	}

}
