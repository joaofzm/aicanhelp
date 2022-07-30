package br.com.joaofzm15.yugiohstatslocal.frontEnd.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Duel;
import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.enums.OppDeck;

public class DataMiner {

	// ===============Final Info Methods=========================================
	public static LinkedHashMap<OppDeck, Double> getLinkedHashMapOfMatchupsAndWinrates(List<Duel> list){
		//Only saves matchup if there are more than 5 duels against that deck
		OppDeck[] allOppDeckArray = OppDeck.values();
		List<OppDeck> allOppDeckList = Arrays.asList(allOppDeckArray);
		
		LinkedHashMap<OppDeck,Double> allMatchupsAndWinrates = new LinkedHashMap<>();
		
		for (OppDeck oppDeck : allOppDeckList) {
			//If Unkown skip loop
			if(oppDeck==OppDeck.Unknown) {
				continue;
			}
			
			List<Duel> duelsAgainstThisDeck = DuelListFilter.filterOnlyAgainst(list, oppDeck);
			double winrate = DataMiner.getTotalWinRate(duelsAgainstThisDeck);
			if (duelsAgainstThisDeck.size()>=5) {
				allMatchupsAndWinrates.put(oppDeck, winrate);
			}
		}
		//Add filler matchups if less than 4 to avoid a IndexOutOfBounds exceptions at ViewDataPanel
		if(allMatchupsAndWinrates.size()<4) {
			allMatchupsAndWinrates.put(OppDeck.FAKE_DECK, Double.NaN);
		}
		return allMatchupsAndWinrates;
	}
	
	public static LinkedHashMap<OppDeck, Double> sortLinkedHashMapByWorseWinRate(LinkedHashMap<OppDeck, Double> map){
		
		LinkedHashMap<OppDeck, Double> sortedMap = 
				map.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		return sortedMap;
 	}
	
	public static LinkedHashMap<OppDeck, Double> sortLinkedHashMapByBestWinRate(LinkedHashMap<OppDeck, Double> map){
		
		LinkedHashMap<OppDeck, Double> sortedMap = 
				map.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));
		//Reverse The Map Here
		List<OppDeck> sortedMapKeys = new ArrayList<>(sortedMap.keySet());
		LinkedHashMap<OppDeck, Double> reversedSortedMap = new LinkedHashMap<>();
		for(int i=sortedMapKeys.size()-1;i>=0;i--) {
			if (!sortedMap.get(sortedMapKeys.get(i)).isNaN()) {
				reversedSortedMap.put(sortedMapKeys.get(i), sortedMap.get(sortedMapKeys.get(i)));
			}
		}
		//Add filler matchups if less than 4 to avoid a IndexOutOfBounds exceptions at ViewDataPanel
		if(reversedSortedMap.size()<4) {
			reversedSortedMap.put(OppDeck.FAKE_DECK, Double.NaN);
		}
		return reversedSortedMap;
	}
	
	
	
	public static int getTotalTurnsFromDuelsWithTurnCount(List<Duel> list) {
		int totalTurns = 0;
		for (Duel duel : list) {
			if (duel.getTurns()!=0) {
				totalTurns+=duel.getTurns();
			}
		}
		return totalTurns;
	}
	
	public static int getAmountOfDuelsWithTurnCount(List<Duel> list) {
		List<Duel> duelsWithTurnCount = new ArrayList<>();
		for (Duel duel : list) {
			if (duel.getTurns()!=0) {
				duelsWithTurnCount.add(duel);
			}
		}
		return duelsWithTurnCount.size();
	}
	
	
	
	
	public static int getTotalWins(List<Duel> list) {
		List<Duel> onlyWins = DuelListFilter.filterOnlyWins(list);
		return onlyWins.size();
	}
	public static int getTotalLosses(List<Duel> list) {
		List<Duel> onlyLosses = DuelListFilter.filterOnlyLosses(list);
		return onlyLosses.size();
	}
	public static double getTotalWinRate(List<Duel> list) {
		int wins = getTotalWins(list);
		int losses = getTotalLosses(list);
		return Calculator.calculateWinRate(wins, losses);
	}

	
	
	
	
	public static int getTotalWinsGoingFirst(List<Duel> list) {
		List<Duel> onlyFirst = DuelListFilter.filterOnlyWentFirst(list);
		List<Duel> onlyWins = DuelListFilter.filterOnlyWins(onlyFirst);
		return onlyWins.size();
	}
	public static int getTotalLossesGoingFirst(List<Duel> list) {
		List<Duel> onlyFirst = DuelListFilter.filterOnlyWentFirst(list);
		List<Duel> onlyLosses = DuelListFilter.filterOnlyLosses(onlyFirst);
		return onlyLosses.size();
	}
	public static double getTotalWinRateGoingFirst(List<Duel> list) {
		int wins = getTotalWinsGoingFirst(list);
		int losses = getTotalLossesGoingFirst(list);
		return Calculator.calculateWinRate(wins, losses);
	}
	
	
	
	
	
	public static int getTotalWinsGoingSecond(List<Duel> list) {
		List<Duel> onlySecond = DuelListFilter.filterOnlyWentSecond(list);
		List<Duel> onlyWins = DuelListFilter.filterOnlyWins(onlySecond);
		return onlyWins.size();
	}
	public static int getTotalLossesGoingSecond(List<Duel> list) {
		List<Duel> onlySecond = DuelListFilter.filterOnlyWentSecond(list);
		List<Duel> onlyLosses = DuelListFilter.filterOnlyLosses(onlySecond);
		return onlyLosses.size();
	}
	public static double getTotalWinRateGoingSecond(List<Duel> list) {
		int wins = getTotalWinsGoingSecond(list);
		int losses = getTotalLossesGoingSecond(list);
		return Calculator.calculateWinRate(wins, losses);
	}
	
	
	
	
	
	public static int getTotalCoinWins(List<Duel> list) {
		List<Duel> onlyCoinWins = DuelListFilter.filterOnlyWonCoin(list);
		return onlyCoinWins.size();
	}
	public static int getTotalCoinLosses(List<Duel> list) {
		List<Duel> onlyCoinLosses = DuelListFilter.filterOnlyLostCoin(list);
		return onlyCoinLosses.size();
	}
	public static double getTotalCoinWinRate(List<Duel> list) {
		int wins = getTotalCoinWins(list);
		int losses = getTotalCoinLosses(list);
		return Calculator.calculateWinRate(wins, losses);
	}
	
	
	
	
	
	public static int getTotalDuelsGoingFirst(List<Duel> list) {
		List<Duel> onlyFirst = DuelListFilter.filterOnlyWentFirst(list);
		return onlyFirst.size();
	}
	public static int getTotalDuelsGoingSecond(List<Duel> list) {
		List<Duel> onlySecond = DuelListFilter.filterOnlyWentSecond(list);
		return onlySecond.size();
	}
	public static double getTotalGoingFirstFrequencyPercentage(List<Duel> list) {
			int first = getTotalDuelsGoingFirst(list);
			int second = getTotalDuelsGoingSecond(list);
			return Calculator.calculateWinRate(first, second);
	}
	
	
	// ===============Final Info Methods=========================================

}
