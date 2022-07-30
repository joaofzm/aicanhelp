package br.com.joaofzm15.yugiohstatslocal.backEnd.entitites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player implements Serializable {
	private static final long serialVersionUID = 9054637370713078681L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private String password;
	
	@OneToMany(mappedBy = "player")
	private List<Deck> decks = new ArrayList<>();
	
	// Default constructor must be 
	// created when using this framework.
	public Player() {

	}
	
	public Player(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password=password;
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

	public List<Deck> getDecks() {
		return decks;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		Player other = (Player) obj;
		return Objects.equals(id, other.id);
	}

}
