package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import astral.components.visualComponents.Label;
import astral.components.visualComponents.Page;
import astral.components.visualComponents.TextButton;
import astral.components.visualComponents.TextField;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Deck;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;
import br.com.joaofzm15.aicanhelp.frontEnd.http.HttpController;

public class AddDeckPanel extends Page implements ActionListener {

	private TextField deckNameTextField;
	
	private TextButton addDeckButton;
	
	private TextButton returnButton;
	

	private JFrame frame;
	
	public AddDeckPanel() {
		super("Backgrounds/cleanbg.png");

		
		getPanel().add(new Label(0, 100, 1920, 200, "ADD DECK", 210, 200, 255, 62, false));


		getPanel().add(deckNameTextField = new TextField(480, 280, 100, "DECK NAME",50));

		getPanel().add(addDeckButton = new TextButton(630, 118, 56, "ADD", 70,200,255,62, 40, 40, 220, false), this);


		getPanel().add(returnButton = new TextButton(950, 218, 62, "RETURN", 70, 200, 50, 50, 255, 50, 50, false), this);
		
		addBackground();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addDeckButton.getJComponent()) {
			List<Deck> decks = FrontEndInMemoryData.currentlyLoggedPlayer.getDecks();
			String inputName = deckNameTextField.getJComponent().getText();
			boolean repeat = false;
			for (Deck deck : decks) {
				if(deck.getName().equals(inputName)) {
					repeat = true;
					JOptionPane.showMessageDialog(null, "ERROR! There's already a deck with this name!");
				}
			}
			
			if (repeat==false) {
				if (deckNameTextField.getJComponent().getText().equals("DECK NAME")) {
					JOptionPane.showMessageDialog(null, "ERROR! Please type the deck name!");
				} else {
					HttpController.post("{\"name\": \"" + deckNameTextField.getJComponent().getText() + "\",\"player\":{\"id\":"+ FrontEndInMemoryData.currentlyLoggedPlayer.getId() +"}}"
							,"http://localhost:8080/decks");
					JOptionPane.showMessageDialog(null, "Deck added sucesfully!");
					FrontEndInMemoryData.updateLoggedPlayerData();
				}
			}
		}
		
		if (e.getSource() == returnButton.getJComponent()) {
		MenuPanel initialPanel = new MenuPanel();
		getFrame().switchPage(initialPanel);
		}
	}

}