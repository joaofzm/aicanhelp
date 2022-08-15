package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import astral.components.visualComponents.ComboBox;
import astral.components.visualComponents.Label;
import astral.components.visualComponents.Page;
import astral.components.visualComponents.TextButton;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Deck;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Duel;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.enums.OppDeck;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;
import br.com.joaofzm15.aicanhelp.frontEnd.logic.Calculator;
import br.com.joaofzm15.aicanhelp.frontEnd.logic.DataMiner;
import br.com.joaofzm15.aicanhelp.frontEnd.logic.DuelListFilter;

public class ViewDataPanel extends Page implements ActionListener {

	private ComboBox seasonComboBox;
	private TextButton filterSeasonButton;

	private ComboBox deckComboBox;
	private TextButton viewDeckStatsButton;
	
	private ComboBox oppDeckComboBox;
	
	private TextButton returnButton;
	
	public ViewDataPanel(List<Duel> parameterList, String title) {
		super("Backgrounds/cleanbg.png");
		
		List<Duel> listFilteredOnlySelectedSeason = DuelListFilter.filterOnlyFromSelectedSeason(parameterList);
		
		
		getPanel().add(returnButton = new TextButton(1680, 980, 200, 56, "RETURN", 62,200, 50, 50, 255, 50, 50, false), this);
		
		getPanel().add(new Label(100, 20, 1920, 130, title, 72, 200, 255, 62, false));
		
	    DecimalFormat df = new DecimalFormat("0.000");
		
	    
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxCOMBO BOXESxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		getPanel().add(seasonComboBox = new ComboBox(25, 25, 300, 100, "x", 255, 255, 255, 0, 111, 245, 28));
		List<String> seasonComboBoxListOfItems = new ArrayList<>();
		seasonComboBoxListOfItems.add("All Seasons");
		for (int i=1; i<11; i++) {
			seasonComboBoxListOfItems.add("Season "+i);
		}
		seasonComboBox.getJComponent().setModel(new DefaultComboBoxModel(seasonComboBoxListOfItems.toArray()));
		seasonComboBox.getJComponent().setSelectedIndex(FrontEndInMemoryData.filteredSeason);
		
		getPanel().add(filterSeasonButton = new TextButton(10, 138, 180, 56, "FILTER", 62,200,255,62, 40, 40, 220, false), this);
		
		getPanel().add(deckComboBox = new ComboBox(25, 830, 300, 100, "x", 0, 0, 0, 200, 255, 62, 28));
		List<Deck> decksList = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		List<Deck> copyOfDecksList = new ArrayList<>();
		for (Deck deck : decksList) {
			copyOfDecksList.add(deck);
		}
		copyOfDecksList.add(0,new Deck(null, "ALL DECKS", null));
		deckComboBox.getJComponent().setModel(new DefaultComboBoxModel(copyOfDecksList.toArray()));
		
		
		getPanel().add(viewDeckStatsButton = new TextButton(360, 915, 190, 56, "FILTER", 62,200,255,62, 40, 40, 220, false), this);
		
		getPanel().add(oppDeckComboBox = new ComboBox(25, 960, 300, 100, "x", 255, 255, 255, 120, 50, 50, 28));
		OppDeck[] items = OppDeck.values();
		oppDeckComboBox.getJComponent().setModel(new DefaultComboBoxModel(items));
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

		
		
		
		
		
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxMAIN LABELSxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		//===============================================
		int labelY = 170;
		//===============================================
		int totalWins = DataMiner.getTotalWins(listFilteredOnlySelectedSeason);
		int totalLosses = DataMiner.getTotalLosses(listFilteredOnlySelectedSeason);
		int totalDuels = totalWins+totalLosses;
		double winrate = DataMiner.getTotalWinRate(listFilteredOnlySelectedSeason);
		int totalTurnsFromDuelsWithTurnCount = DataMiner.getTotalTurnsFromDuelsWithTurnCount(listFilteredOnlySelectedSeason);
		int totalDuelsWithTurnCount = DataMiner.getAmountOfDuelsWithTurnCount(listFilteredOnlySelectedSeason);
		double avgTurns = Calculator.calculateAverage(totalTurnsFromDuelsWithTurnCount, totalDuelsWithTurnCount);
		
		getPanel().add(new Label(0, labelY, 1920, 50, "Total >>> "
				+ "Wins: "+totalWins+"  "
				+ "|  Losses: "+totalLosses+"  "
				+ "|  ( "+totalDuels+" )  -  "+df.format(winrate)+"%  "
				+ "|  Avg. Turns: "+df.format(avgTurns)
				, 50, 255, 255, 200, false));
		//===============================================
		labelY+=110;
		//===============================================
		List<Duel> duelsGoingFirst = DuelListFilter.filterOnlyWentFirst(listFilteredOnlySelectedSeason);
		int totalFirstWins = DataMiner.getTotalWinsGoingFirst(duelsGoingFirst);
		int totalFirstLosses = DataMiner.getTotalLossesGoingFirst(duelsGoingFirst);
		int totalFirstDuels = totalFirstWins+totalFirstLosses;
		double firstWinrate = DataMiner.getTotalWinRateGoingFirst(duelsGoingFirst);
		int totalTurnsFromDuelsWithTurnCountFirst = DataMiner.getTotalTurnsFromDuelsWithTurnCount(duelsGoingFirst);
		int totalDuelsWithTurnCountFirst = DataMiner.getAmountOfDuelsWithTurnCount(duelsGoingFirst);
		double avgTurnsFirst = Calculator.calculateAverage(totalTurnsFromDuelsWithTurnCountFirst, totalDuelsWithTurnCountFirst);
		
		getPanel().add(new Label(0, labelY, 1920, 50, "Going First  >>>  "
				+ "Wins: "+totalFirstWins+"  "
				+ "|  Losses: "+totalFirstLosses+"  "
				+ "|  ( "+totalFirstDuels+" )  -  "+df.format(firstWinrate)+"%  "
				+ "|  Avg. Turns: "+df.format(avgTurnsFirst)
				, 50, 255, 255, 200, false));
		//===============================================
		labelY+=75;
		//===============================================
		List<Duel> duelsGoingSecond = DuelListFilter.filterOnlyWentSecond(listFilteredOnlySelectedSeason);
		int totalSecondWins = DataMiner.getTotalWinsGoingSecond(duelsGoingSecond);
		int totalSecondLosses = DataMiner.getTotalLossesGoingSecond(duelsGoingSecond);
		int totalSecondDuels = totalSecondWins+totalSecondLosses;
		double secondWinrate = DataMiner.getTotalWinRateGoingSecond(duelsGoingSecond);
		int totalTurnsFromDuelsWithTurnCountSecond = DataMiner.getTotalTurnsFromDuelsWithTurnCount(duelsGoingSecond);
		int totalDuelsWithTurnCountSecond = DataMiner.getAmountOfDuelsWithTurnCount(duelsGoingSecond);
		double avgTurnsSecond = Calculator.calculateAverage(totalTurnsFromDuelsWithTurnCountSecond, totalDuelsWithTurnCountSecond);
		getPanel().add(new Label(0, labelY, 1920, 50, "Going Second  >>>  "
				+ "Wins: "+totalSecondWins+"  "
				+ "|  Losses: "+totalSecondLosses+"  "
				+ "|  ( "+totalSecondDuels+" )  -  "+df.format(secondWinrate)+"%  "
				+ "|  Avg. Turns: "+df.format(avgTurnsSecond)
				, 50, 255, 255, 200, false));
		
		
		//===============================================
		labelY+=110;
		//===============================================
		int totalCoinWins = DataMiner.getTotalCoinWins(listFilteredOnlySelectedSeason);
		int totalCoinLosses = DataMiner.getTotalCoinLosses(listFilteredOnlySelectedSeason);
		int totalCoinsThrow = totalCoinWins+totalCoinLosses;
		double coinWinrate = DataMiner.getTotalCoinWinRate(listFilteredOnlySelectedSeason);
		
		
		getPanel().add(new Label(0, labelY, 1920, 50, "Coin toss win rate  >>>  "
				+ "Wins: "+totalCoinWins+"  "
				+ "|  Losses: "+totalCoinLosses+"  "
				+ "|  ( "+totalCoinsThrow+" )  -  "+df.format(coinWinrate)+"%"
				, 50, 255, 255, 200, false));
		//===============================================
		labelY+=75;
		//===============================================
		int totalDuelsGoingFirst = DataMiner.getTotalDuelsGoingFirst(listFilteredOnlySelectedSeason);
		int totalDuelsGoingSecond = DataMiner.getTotalDuelsGoingSecond(listFilteredOnlySelectedSeason);
		int totalDuelsAmount = totalDuelsGoingFirst+totalDuelsGoingSecond;
		double goingFirstFrequency = DataMiner.getTotalGoingFirstFrequencyPercentage(listFilteredOnlySelectedSeason);
		
		getPanel().add(new Label(0, labelY, 1920, 50, "Play first frequency  >>>  "
				+ "First: "+totalDuelsGoingFirst+"  "
				+ "|  Second: "+totalDuelsGoingSecond+"  "
				+ "|  ( "+totalDuelsAmount+" )  -  "+df.format(goingFirstFrequency)+"%"
				, 50, 255, 255, 200, false));
		//===============================================
		labelY+=75;
		//===============================================
		//Remove duels before March 10th
		List<Duel> listSortedById = new ArrayList<>(); 
		for (Duel duel : listFilteredOnlySelectedSeason) {
			if (duel.getDate().isAfter(DuelListFilter.minDateForCoinAndFirstSecond)) {
				listSortedById.add(duel);
			}
		}
		Collections.sort(listSortedById);
		
		int wCoinsInARow=0;
		int lCoinsInARow=0;
		int maxWCoinsInARow = 0;
		int maxLCoinsInARow = 0;
		boolean lastOneW = false;
		boolean lastOneL = false;
		
		for (Duel duel : listSortedById) {
			if (duel.isCoinResult()) {
				wCoinsInARow++;
			} else {
				wCoinsInARow = 0;
			}
			
			if (wCoinsInARow > maxWCoinsInARow) {
				maxWCoinsInARow = wCoinsInARow;
			}
		}
		
		for (Duel duel : listSortedById) {
			if (!duel.isCoinResult()) {
				lCoinsInARow++;
			} else {
				lCoinsInARow = 0;
			}
			
			if (lCoinsInARow > maxLCoinsInARow) {
				maxLCoinsInARow = lCoinsInARow;
			}
		}
		
		getPanel().add(new Label(0, labelY, 1920, 50,
				"Biggest coin streaks >>> Wins: " + maxWCoinsInARow + "  |  Losses: " +maxLCoinsInARow
				, 50, 255, 255, 200, false));
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		
		
		
		
		
		
		
		

		
		
		
		
//xxxxxxxxxxxxxxxxxxxBEST AND WORST MATCHUPSxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		int yAxis = 780;
		
		getPanel().add(new Label(530, 720, 550, 100,
				"-Best Matchups-",
				40, 200, 255, 62, false));
		
		LinkedHashMap<OppDeck,Double> bestMatchupsMap = DataMiner.sortLinkedHashMapByBestWinRate(
				DataMiner.getLinkedHashMapOfMatchupsAndWinrates(listFilteredOnlySelectedSeason));
		for (int i = 0; i<4; i++) {
			List<OppDeck> keys = new ArrayList<>(bestMatchupsMap.keySet());
			if(Double.isNaN(bestMatchupsMap.get(keys.get(i)))) {
				break;
			}
			
			getPanel().add(new Label(530, yAxis, 550, 100,
					keys.get(i).toString()+" "+df.format(bestMatchupsMap.get(keys.get(i)))+"%",
					40, 255, 255, 200, false));
			
			yAxis+= 60;
		}
		
//==================================================
		yAxis = 780;
		
		getPanel().add(new Label(1080, 720, 550, 100,
				"-Worse Matchups-",
				40, 200, 255, 62, false));
		
		LinkedHashMap<OppDeck,Double> worseMatchupsMap = DataMiner.sortLinkedHashMapByWorseWinRate(
				DataMiner.getLinkedHashMapOfMatchupsAndWinrates(listFilteredOnlySelectedSeason));
		for (int i = 0; i<4; i++) {
			List<OppDeck> keys = new ArrayList<>(worseMatchupsMap.keySet());
			if(Double.isNaN(worseMatchupsMap.get(keys.get(i)))) {
				break;
			}
			
			getPanel().add(new Label(1080, yAxis, 590, 100,
					keys.get(i).toString()+" "+df.format(worseMatchupsMap.get(keys.get(i)))+"%",
					40, 255, 255, 200, false));
			
			yAxis+= 60;
		}
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		
		addBackground();

	}

	private Deck getSelectedDeck() {
		String selectedDeckName = deckComboBox.getJComponent().getSelectedItem().toString();
		List<Deck> decksList = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		for (Deck deck : decksList) {
			if (deck.getName().equals(selectedDeckName)) {
				return deck;
			}
		}
		return null;
	}
	
	private OppDeck getSelectedOppDeck() {
		return (OppDeck) oppDeckComboBox.getJComponent().getSelectedItem();
	}
	
	private String formatOppDeckString(OppDeck oppDeck) {
		String oppDeckToString = oppDeck.toString();
		return oppDeckToString.replace("_", " ");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == filterSeasonButton.getJComponent()) {
			String selectedSeason = seasonComboBox.getJComponent().getSelectedItem().toString();
			if (selectedSeason.equalsIgnoreCase("All Seasons")) {
				FrontEndInMemoryData.filteredSeason = 0;
			} else {
				String[] splitted = selectedSeason.split(" ");
				FrontEndInMemoryData.filteredSeason = Integer.valueOf(splitted[1]);
			}
			
			ViewDataPanel initialPanel = new ViewDataPanel(FrontEndInMemoryData.getAllDuelsFromUser(),
					"All decks  vs  All decks");
			getFrame().switchPage(initialPanel);
		}
		
		if (e.getSource() == viewDeckStatsButton.getJComponent()) {
			List<Duel> allDuelsFromSelectedDeck ;
			ViewDataPanel initialPanel;
			
			if (deckComboBox.getJComponent().getSelectedItem().toString().equals("ALL DECKS")) {
				if (getSelectedOppDeck()!=OppDeck.ALL_DECKS) {
					allDuelsFromSelectedDeck=DuelListFilter.filterOnlyAgainst(
							FrontEndInMemoryData.getAllDuelsFromUser(),
							getSelectedOppDeck());
					initialPanel = new ViewDataPanel(allDuelsFromSelectedDeck,
							"All decks  vs  "+formatOppDeckString(getSelectedOppDeck()));
				} else {
					allDuelsFromSelectedDeck=FrontEndInMemoryData.getAllDuelsFromUser();
					initialPanel = new ViewDataPanel(allDuelsFromSelectedDeck,"All decks  vs  All decks");
				}
			} else {
				
				if (getSelectedOppDeck()!=OppDeck.ALL_DECKS) {
					allDuelsFromSelectedDeck=DuelListFilter.filterOnlyAgainst(
							getSelectedDeck().getDuels(),
							getSelectedOppDeck());
					initialPanel = new ViewDataPanel(allDuelsFromSelectedDeck,getSelectedDeck().getName()+"  vs  "+formatOppDeckString(getSelectedOppDeck()));
				} else {
					allDuelsFromSelectedDeck=getSelectedDeck().getDuels();
					initialPanel = new ViewDataPanel(allDuelsFromSelectedDeck, getSelectedDeck().getName()+"  vs  All Decks");
				}
		
			}
			getFrame().switchPage(initialPanel);
		}
			
		
		if (e.getSource() == returnButton.getJComponent()) {
			MenuPanel initialPanel = new MenuPanel();
			getFrame().switchPage(initialPanel);
		}
	}
}