package br.com.joaofzm15.yugiohstatslocal.frontEnd.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Deck;
import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Duel;
import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Player;

public class HttpController {
	

	public static HttpResponse<String> getHttpResponseFromUrl(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).timeout(Duration.ofSeconds(3)).build();
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(3)).build() ;
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static void post (String body, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofString(body))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(3))
                .build();
        
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .followRedirects(Redirect.NORMAL)
                .build();
        
        try {
			httpClient.sendAsync(request, BodyHandlers.ofString())
					.thenApply(HttpResponse::body)
					.thenApply(String::toUpperCase)
					.thenAccept(System.out::println)
			        .get(3, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	public static List<Player> parseJsonIntoPlayer(HttpResponse<String> response) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<Player> players = null;
        try {
			players = mapper.readValue(response.body(), new TypeReference<List<Player>>() {});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return players;
	}
	
	public static List<Deck> parseJsonIntoDeck(HttpResponse<String> response) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<Deck> decks = null;
        try {
        	decks = mapper.readValue(response.body(), new TypeReference<List<Deck>>() {});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return decks;
	}
	
	public static List<Duel> parseJsonIntoDuel(HttpResponse<String> response) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		List<Duel> duels = null;
        try {
        	duels = mapper.readValue(response.body(), new TypeReference<List<Duel>>() {});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return duels;
	}
	
}
