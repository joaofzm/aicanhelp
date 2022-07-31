package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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

	private Label titleLabel;

	private ComboBox deckComboBox;
	private TextButton viewDeckStatsButton;
	
	private ComboBox oppDeckComboBox;
	
	private TextButton returnButton;
	
	public ViewDataPanel(List<Duel> parameterList, String title) {
		super("Backgrounds/cleanbg.png");

		
		List<Duel> listFilteredOnlySelectedSeason = DuelListFilter.filterOnlyFromSelectedSeason(parameterList);

		
		getPanel().add(new Label(340, 10, 1110, 33,
				"*Duels from before March 10th 2022 do not contain info on coin tosses and who went first!",
				30, 200, 200, 255, false));

		getPanel().add(new Label(340, 50, 1110, 33,
				"*Duels from before Season 4 do not contain info on the amount of turns! ",
				30, 200, 200, 255, false));

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int yAxis = 780;
		
		getPanel().add(new Label(530, 720, 550, 100,
				"-Best Matchups-",
				40, 200, 200, 255, false));
		
		LinkedHashMap<OppDeck,Double> bestMatchupsMap = DataMiner.sortLinkedHashMapByBestWinRate(
				DataMiner.getLinkedHashMapOfMatchupsAndWinrates(listFilteredOnlySelectedSeason));
		for (int i = 0; i<4; i++) {
			List<OppDeck> keys = new ArrayList<>(bestMatchupsMap.keySet());
			if(Double.isNaN(bestMatchupsMap.get(keys.get(i)))) {
				break;
			}
			
			getPanel().add(new Label(530, yAxis, 550, 100,
					keys.get(i).toString()+" "+bestMatchupsMap.get(keys.get(i))+"%",
					40, 200, 200, 255, false));
			
			yAxis+= 60;
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		yAxis = 780;
		
		getPanel().add(new Label(1080, 720, 550, 100,
				"-Worse Matchups-",
				40, 200, 200, 255, false));
		
		LinkedHashMap<OppDeck,Double> worseMatchupsMap = DataMiner.sortLinkedHashMapByWorseWinRate(
				DataMiner.getLinkedHashMapOfMatchupsAndWinrates(listFilteredOnlySelectedSeason));
		for (int i = 0; i<4; i++) {
			List<OppDeck> keys = new ArrayList<>(worseMatchupsMap.keySet());
			if(Double.isNaN(worseMatchupsMap.get(keys.get(i)))) {
				break;
			}
			
			getPanel().add(new Label(1080, yAxis, 590, 100,
					keys.get(i).toString()+" "+worseMatchupsMap.get(keys.get(i))+"%",
					40, 200, 200, 255, false));
			
			yAxis+= 60;
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		getPanel().add(seasonComboBox = new ComboBox(25, 25, 300, 100, "x", 255, 255, 255, 50, 50, 120, 28));
		List<String> seasonComboBoxListOfItems = new ArrayList<>();
		seasonComboBoxListOfItems.add("All Seasons");
		for (int i=1; i<11; i++) {
			seasonComboBoxListOfItems.add("Season "+i);
		}
		seasonComboBox.getJComponent().setModel(new DefaultComboBoxModel(seasonComboBoxListOfItems.toArray()));
		seasonComboBox.getJComponent().setSelectedIndex(FrontEndInMemoryData.filteredSeason);
		
		
		getPanel().add(filterSeasonButton = new TextButton(75, 138, 180, 56, "FILTER", 62, 150, 150, 200, 100, 100, 255, false), this);

		getPanel().add(new Label(100, 120, 1920, 130, title, 72, 200, 200, 255, false));

		getPanel().add(deckComboBox = new ComboBox(25, 830, 300, 100, "x", 255, 255, 255, 50, 120, 50, 28));
		List<Deck> decksList = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		List<Deck> copyOfDecksList = new ArrayList<>();
		for (Deck deck : decksList) {
			copyOfDecksList.add(deck);
		}
		copyOfDecksList.add(0,new Deck(null, "ALL DECKS", null));
		deckComboBox.getJComponent().setModel(new DefaultComboBoxModel(copyOfDecksList.toArray()));
		
		
		getPanel().add(viewDeckStatsButton = new TextButton(360, 915, 190, 56, "FILTER", 62, 150, 150, 200, 100, 100, 255, false), this);

		getPanel().add(oppDeckComboBox = new ComboBox(25, 960, 300, 100, "x", 255, 255, 255, 120, 50, 50, 28));
		OppDeck[] items = OppDeck.values();
		oppDeckComboBox.getJComponent().setModel(new DefaultComboBoxModel(items));
		
		
		getPanel().add(returnButton = new TextButton(1680, 980, 200, 56, "RETURN", 62, 150, 150, 200, 100, 100, 255, false), this);

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	    DecimalFormat df = new DecimalFormat("0.000");
		//===============================================
		int labelY = 270;
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
		
		getPanel().add(new Label(0, labelY, 1920, 50, "Going Fist  >>>  "
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