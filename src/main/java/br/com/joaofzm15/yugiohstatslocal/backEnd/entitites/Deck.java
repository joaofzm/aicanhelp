package br.com.joaofzm15.yugiohstatslocal.backEnd.entitites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Deck implements Serializable {
	private static final long serialVersionUID = 575045973018717512L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "deck_owner_id")
	private Player player;
	
	@OneToMany(mappedBy = "deck")
	private List<Duel> duels = new ArrayList<>();
	
	public Deck() {
		
	}

	public Deck(Long id, String name, Player player) {
		this.id = id;
		this.name = name;
		this.player=player;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "player")
	public List<Duel> getDuels() {
		return duels;
	}
	
	public void setDuels(List<Duel> duels) {
		this.duels = duels;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return name;
	}

	
	

}
