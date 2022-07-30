package br.com.joaofzm15.aicanhelp.backEnd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

	public default Player findByName(String name) {
		List<Player> list;
		list = findAll();
		for (Player player : list) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
	
}
