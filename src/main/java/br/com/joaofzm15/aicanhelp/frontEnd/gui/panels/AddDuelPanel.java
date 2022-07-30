package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Deck;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.enums.OppDeck;
import br.com.joaofzm15.aicanhelp.frontEnd.exceptions.BlankFieldException;
import br.com.joaofzm15.aicanhelp.frontEnd.exceptions.FieldInputMismatchException;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Button;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.CheckBox;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.ComboBox;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Label;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Panel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.TextField;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;
import br.com.joaofzm15.aicanhelp.frontEnd.http.HttpController;

public class AddDuelPanel implements ActionListener {

	private Panel panel;
	public Panel getPanel() {
		return panel;
	}
	
	private JLabel bg;
	
	
	private ComboBox deckComboBox;
	
	private CheckBox duelWBox;
	private CheckBox duelLBox;
	
	private CheckBox coinWBox;
	private CheckBox coinLBox;
	
	private CheckBox firstBox;
	private CheckBox secondBox;
	
	private TextField turnsTextField;
	
	private ComboBox oppDeckComboBox;
	
	private Button addDuelButton;
	
	private Button returnButton;
	
	private Label title;

	private JFrame frame;
	
	public AddDuelPanel(JFrame frame) {
		
		this.frame=frame;

		panel = new Panel(1920,1080);
		
		title = new Label(0, 170, 1920, 130, "ADD DUEL", 130, 200, 200, 255);
		panel.add(title);
		
		deckComboBox = new ComboBox(355, 455, 300, 100, "x", 255, 255, 255, 50, 120, 50, 28);
		List<Deck> decksList = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		deckComboBox.getJComponent().setModel(new DefaultComboBoxModel(decksList.toArray()));
		panel.add(deckComboBox);
		
		duelWBox = new CheckBox(705, 400, 100, 100, "W", 255, 255, 255, 50,255,50, 50);
		duelWBox.getJComponent().addActionListener(this);
		panel.add(duelWBox);
		duelLBox = new CheckBox(705, 510, 100, 100, "L", 255, 255, 255, 255,50,50, 50);
		duelLBox.getJComponent().addActionListener(this);
		panel.add(duelLBox);
		
		
		coinWBox = new CheckBox(855, 400, 100, 100, "W", 255, 255, 255, 200,200,50, 50);
		coinWBox.getJComponent().addActionListener(this);
		panel.add(coinWBox);
		coinLBox = new CheckBox(855, 510, 100, 100, "L", 255, 255, 255, 50,50,50, 50);
		coinLBox.getJComponent().addActionListener(this);
		panel.add(coinLBox);
		
		firstBox = new CheckBox(965, 400, 100, 100, "1st", 255, 255, 255, 200,200,50, 40);
		firstBox.getJComponent().addActionListener(this);
		panel.add(firstBox);
		secondBox = new CheckBox(965, 510, 100, 100, "2nd", 255, 255, 255, 50,50,50, 40);
		secondBox.getJComponent().addActionListener(this);
		panel.add(secondBox);
		
		turnsTextField = new TextField(1115, 455, 100, 100, " turns", 40);
		panel.add(turnsTextField);
		
		oppDeckComboBox = new ComboBox(1265, 455, 300, 100, "x", 255, 255, 255, 120, 50, 50, 28);
		OppDeck[] items = OppDeck.values();
		oppDeckComboBox.getJComponent().setModel(new DefaultComboBoxModel(items));
		panel.add(oppDeckComboBox);
		
		addDuelButton = new Button(900, 720, 120, 56, "ADD", 50, 255, 50, 70);
		addDuelButton.getJComponent().addActionListener(this);
		panel.add(addDuelButton);

		returnButton = new Button(865, 950, 190, 56, "RETURN",255,20,20,62);
		returnButton.getJComponent().addActionListener(this);
		panel.add(returnButton);
		
		bg = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"));
		bg.setSize(1920,1080);
		if (Config.res==2) {
			icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"));
			bg.setSize(1280,720);
		}
		bg.setIcon(icon);
		panel.getJComponent().add(bg);

	}

	private void resetInputs() {
		duelWBox.getJComponent().setSelected(false);
		duelLBox.getJComponent().setSelected(false);
		coinWBox.getJComponent().setSelected(false);
		coinLBox.getJComponent().setSelected(false);
		firstBox.getJComponent().setSelected(false);
		secondBox.getJComponent().setSelected(false);
		turnsTextField.resetToPlaceHolder();
		oppDeckComboBox.getJComponent().setSelectedIndex(0);
	}
	
	private boolean getInputFromTwoBoxes(CheckBox trueOutcome, CheckBox falseOutcome) {
		if (trueOutcome.getJComponent().isSelected()) {
			return true;
		} else if (falseOutcome.getJComponent().isSelected()) {
			return false;
		} else {
			throw new BlankFieldException("One or more radio buttons are blank!");
		}
	}
	
	private String getTurnsAndValidadeIt() {
		Integer turnsParsedToInt = 0;
		try {
			turnsParsedToInt = Integer.valueOf(turnsTextField.getJComponent().getText());
		} catch (NumberFormatException e) {
			throw new FieldInputMismatchException("Turns input isn't an integer number!");
		}
		
		return String.valueOf(turnsParsedToInt);
		
		//Uncomment this if turns can't be 0
//		if (turnsParsedToInt!=0) {
//			return String.valueOf(turnsParsedToInt);
//		} else {
//			throw new BlankFieldException("Turns can't be 0!");
//		}
	}
	
	private Integer getDeckIdFromComboBox() {
		String selectedDeckName = deckComboBox.getJComponent().getSelectedItem().toString();
		List<Deck> decksList = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
		for (Deck deck : decksList) {
			if (deck.getName().equals(selectedDeckName)) {
				return Math.toIntExact(deck.getId());
			}
		}
		//Not possible to return null, but won't compile without this
		return null;
	}
	
	private Integer getOppDeckIdFromComboBox() {
		String selectedOppDeckToString = oppDeckComboBox.getJComponent().getSelectedItem().toString();
		OppDeck[] allOppDecksArray = OppDeck.values();
		ArrayList<OppDeck> allOppDecksList = new ArrayList<>(Arrays.asList(allOppDecksArray));
		for (OppDeck oppDeck : allOppDecksList) {
			if(oppDeck.toString().equals(selectedOppDeckToString)) {
				return oppDeck.getCode();
			}
		}
		return null;
	}
	
	private String getCurrentInstantInStringFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
        Instant instantNow = Instant.now();
		return formatter.format(instantNow);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == addDuelButton.getJComponent()) {
			
			try {
				try {
					String deck = deckComboBox.getJComponent().getSelectedItem().toString();
				} catch (NullPointerException e4) {
					JOptionPane.showMessageDialog(null, "You must pick a deck! Register one first if you don't have any!");
				}
				
				String selectedOppDeck = oppDeckComboBox.getJComponent().getSelectedItem().toString();
				if(selectedOppDeck.equals("ALL_DECKS")) {
					JOptionPane.showMessageDialog(null, "You must pick your opponent's deck!");
					return;
				}
				
				HttpController.post("{"
						+ "        \"coinResult\": "+ getInputFromTwoBoxes(coinWBox, coinLBox) +","
						+ "        \"first\":" + getInputFromTwoBoxes(firstBox, secondBox) +","
						+ "        \"result\": " + getInputFromTwoBoxes(duelWBox, duelLBox) +","
						+ "        \"turns\": " + getTurnsAndValidadeIt() + ","
						+ "        \"deck\":{"
						+ "            \"id\": " + getDeckIdFromComboBox() +" "
						+ "        },"
						+ "        \"oppDeck\": " + String.valueOf(getOppDeckIdFromComboBox()) +" , "
						+ "        \"date\": \""+getCurrentInstantInStringFormat()+"\""
						+ "    }"
						,"http://localhost:8080/duels");
				JOptionPane.showMessageDialog(null, "Duel added sucesfully!!");
				resetInputs();
				FrontEndInMemoryData.updateLoggedPlayerData();
			} catch (BlankFieldException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (FieldInputMismatchException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			} catch (NullPointerException e3) {
				//Treated above
			}
		}
		
		if (e.getSource() == returnButton.getJComponent()) {
			MenuPanel initialPanel = new MenuPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
		
		if (e.getSource()== duelWBox.getJComponent()) {
			if (duelLBox.getJComponent().isSelected()) {
				duelLBox.getJComponent().setSelected(false);
			} 
		}
		if (e.getSource()== duelLBox.getJComponent()) {
			if (duelWBox.getJComponent().isSelected()) {
				duelWBox.getJComponent().setSelected(false);
			} 
		}
		if (e.getSource()== coinWBox.getJComponent()) {
			if (coinLBox.getJComponent().isSelected()) {
				coinLBox.getJComponent().setSelected(false);
			} 
		}
		if (e.getSource()== coinLBox.getJComponent()) {
			if (coinWBox.getJComponent().isSelected()) {
				coinWBox.getJComponent().setSelected(false);
			} 
		}
		if (e.getSource()== firstBox.getJComponent()) {
			if (secondBox.getJComponent().isSelected()) {
				secondBox.getJComponent().setSelected(false);
			} 
		}
		if (e.getSource()== secondBox.getJComponent()) {
			if (firstBox.getJComponent().isSelected()) {
				firstBox.getJComponent().setSelected(false);
			} 
		}
	}
}