package br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.joaofzm15.yugiohstatslocal.backEnd.entitites.Deck;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Button;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Label;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Panel;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.TextField;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.http.FrontEndInMemoryData;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.http.HttpController;

public class AddDecklPanel implements ActionListener {

	private Panel panel;
	public Panel getPanel() {
		return panel;
	}
	
	private JLabel bg;
	
	private Label title;
	
	private TextField deckNameTextField;
	
	private Button addDeckButton;
	
	private Button returnButton;
	

	private JFrame frame;
	
	public AddDecklPanel(JFrame frame) {
		
		this.frame=frame;

		panel = new Panel(1920,1080);
		
		title = new Label(0, 170, 1920, 130, "ADD DECK", 130, 200, 200, 255);
		panel.add(title);

		deckNameTextField = new TextField(840, 460, 280, 80, "DECK NAME",28);
		panel.add(deckNameTextField);
		
		addDeckButton = new Button(900, 650, 120, 56, "ADD", 50, 255, 50, 70);
		addDeckButton.getJComponent().addActionListener(this);
		panel.add(addDeckButton);

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
			MenuPanel initialPanel = new MenuPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
	}

}