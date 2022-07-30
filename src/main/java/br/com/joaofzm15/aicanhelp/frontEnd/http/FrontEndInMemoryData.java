package br.com.joaofzm15.aicanhelp.frontEnd.http;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Deck;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;

public class FrontEndInMemoryData {
	
	public static Player currentlyLoggedPlayer;
	
	public static int filteredSeason = 0;
	
	public static Player logIn(String username) {
		HttpResponse<String> response = HttpController.getHttpResponseFromUrl("http://localhost:8080/players/");
		List<Player> list = HttpController.parseJsonIntoPlayer(response);
		
		for (Player player : list) {
			if (player.getName().equals(username)) {
				currentlyLoggedPlayer = player;
				return player;
			}
		}
		return null;
	}
	
	public static void updateLoggedPlayerData() {
		String name = currentlyLoggedPlayer.getName();
		logOut();
		logIn(name);
	}
	
	public static void logOut() {
		currentlyLoggedPlayer=null;
	}
	
	public static List<Duel> getAllDuelsFromUser() {
		List<Duel> duels = new ArrayList<>();
		List<Deck> decks = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		for (Deck deck : decks) {
			List<Duel> deckDuels = deck.getDuels();
			for (Duel duel : deckDuels) {
				duels.add(duel);
			}
		}
		return duels;
	}
	
}
