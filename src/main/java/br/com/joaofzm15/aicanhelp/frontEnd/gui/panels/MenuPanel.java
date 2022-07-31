package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import astral.components.visualComponents.Label;
import astral.components.visualComponents.Page;
import astral.components.visualComponents.Panel;
import astral.components.visualComponents.TextButton;
import astral.components.visualComponents.TextField;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;

public class MenuPanel extends Page implements ActionListener {

	private TextButton addDuelButton;
	private TextButton addDeckButton;
	private TextButton viewDataButton;
	
	private Label welcomeLabel;
	
	private TextButton logOutButton;
	private TextButton exitButton;

	private JFrame frame;
	
	public MenuPanel() {
		super("Backgrounds/titlebg.png");

		getPanel().add(welcomeLabel = new Label(0, 0, 500, 100, "Welcome, "+FrontEndInMemoryData.currentlyLoggedPlayer.getName()+"!", 42, 255, 255, 255,false));
		
		getPanel().add(logOutButton = new TextButton(160, 80, 140, 56, "LOG OUT",40,180,50,50, 255, 50, 50, false),this);
		
		getPanel().add(addDuelButton = new TextButton(844, 450, 232, 56, "ADD DUEL",62,200,255,62,40, 40, 220, false),this);
		
		getPanel().add(addDeckButton = new TextButton(839, 550, 242, 56, "ADD DECK",62,200,255,62, 40, 40, 220, false),this);

		getPanel().add(viewDataButton = new TextButton(828, 650, 264, 56, "VIEW DATA",62,200,255,62, 40, 40, 220, false),this);

		getPanel().add(exitButton = new TextButton(950, 103, 62, "EXIT", 62, 200, 50, 50, 255, 50, 50, false), this);

		addBackground();


	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		new Thread(new ClickSound()).start();

		if (e.getSource() == addDuelButton.getJComponent()) {
			AddDuelPanel initialPanel = new AddDuelPanel();
			getFrame().switchPage(initialPanel);
		}
		
		if (e.getSource() == addDeckButton.getJComponent()) {
			AddDeckPanel initialPanel = new AddDeckPanel();
			getFrame().switchPage(initialPanel);
		}
		
//		if (e.getSource() == viewDataButton.getJComponent()) {
//			ViewDataPanel initialPanel = new ViewDataPanel(frame
//					,FrontEndInMemoryData.getAllDuelsFromUser(),
//					"All decks  vs  All decks");
//			frame.getContentPane().removeAll();
//			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
//			frame.revalidate();
//			initialPanel.getPanel().getJComponent().repaint();
//		}
		
		if (e.getSource() == logOutButton.getJComponent()) {
			FrontEndInMemoryData.logOut();
			LoginPanel initialPanel = new LoginPanel();
			getFrame().switchPage(initialPanel);
		}

		if (e.getSource() == exitButton.getJComponent()) {
			try {
				Thread.sleep(900);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}

}