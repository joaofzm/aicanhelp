package br.com.joaofzm15.aicanhelp.backEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;


@Repository
public interface DuelRepository extends JpaRepository<Duel, Long> {

	//No need to instantiate methods, they're inherited from JpaRepository
}
