package br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Button;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Label;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Panel;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.http.FrontEndInMemoryData;

public class MenuPanel implements ActionListener {

	private Panel panel;
	public Panel getPanel() {
		return panel;
	}
	
	private JLabel bg;
	
	private Button addDuelButton;
	private Button addDeckButton;
	private Button viewDataButton;
	
	private Label welcomeLabel;
	
	private Button logOutButton;
	private Button exitButton;

	private JFrame frame;
	
	public MenuPanel(JFrame frame) {
		
		this.frame=frame;

		panel = new Panel(1920,1080);
		
		welcomeLabel = new Label(0, 0, 500, 100, "Welcome, "+FrontEndInMemoryData.currentlyLoggedPlayer.getName()+"!", 42, 255, 255, 255);
		panel.add(welcomeLabel);
		
		logOutButton = new Button(160, 80, 200, 56, "LOG OUT",255,80,80,62);
		logOutButton.getJComponent().addActionListener(this);
		panel.add(logOutButton);
		
		
		addDuelButton = new Button(844, 450, 232, 56, "ADD DUEL",255,255,255,62);
		addDuelButton.getJComponent().addActionListener(this);
		panel.add(addDuelButton);
		
		addDeckButton = new Button(839, 550, 242, 56, "ADD DECK",255,255,255,62);
		addDeckButton.getJComponent().addActionListener(this);
		panel.add(addDeckButton);

		viewDataButton = new Button(828, 650, 264, 56, "VIEW DATA",255,255,255,62);
		viewDataButton.getJComponent().addActionListener(this);
		panel.add(viewDataButton);
		
		exitButton = new Button(908, 950, 104, 56, "EXIT",255,20,20,62);
		exitButton.getJComponent().addActionListener(this);
		panel.add(exitButton);
		
		bg = new JLabel();
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/menubg1280x720.png"));
		bg.setSize(1920,1080);
		if (Config.res==2) {
			icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/menubg1280x720.png"));
			bg.setSize(1280,720);
		}
		bg.setIcon(icon);
		panel.getJComponent().add(bg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		new Thread(new ClickSound()).start();

		if (e.getSource() == addDuelButton.getJComponent()) {
			AddDuelPanel initialPanel = new AddDuelPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
		
		if (e.getSource() == addDeckButton.getJComponent()) {
			AddDecklPanel initialPanel = new AddDecklPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
		
		if (e.getSource() == viewDataButton.getJComponent()) {
			ViewDataPanel initialPanel = new ViewDataPanel(frame
					,FrontEndInMemoryData.getAllDuelsFromUser(),
					"All decks  vs  All decks");
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
		
		if (e.getSource() == logOutButton.getJComponent()) {
			FrontEndInMemoryData.logOut();
			LoginPanel initialPanel = new LoginPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
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