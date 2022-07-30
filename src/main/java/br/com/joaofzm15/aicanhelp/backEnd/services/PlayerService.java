package br.com.joaofzm15.aicanhelp.backEnd.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;
import br.com.joaofzm15.aicanhelp.backEnd.repositories.PlayerRepository;


@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository repository;
	
	public List<Player> findAll(){
		return repository.findAll();
	}
	
	public Player findById(Long id) {
		Optional<Player> obj = repository.findById(id);
		return obj.get();
	}

	public Player insert (Player obj) {
		return repository.save(obj);
	}

}
