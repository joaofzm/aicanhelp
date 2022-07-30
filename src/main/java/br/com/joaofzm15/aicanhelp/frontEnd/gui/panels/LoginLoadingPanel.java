package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Label;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Panel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;

public class LoginLoadingPanel implements ActionListener {

	private Panel panel;
	public Panel getPanel() {
		return panel;
	}
	
	private JLabel bg;
	
	private Label loadingLabel;
	
	
	public LoginLoadingPanel() {
		
		panel = new Panel(1920,1080);
		
		loadingLabel= new Label(713, 400, 498,498, "Assets/loading.png");
		panel.add(loadingLabel);
		
		bg = new JLabel();
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/lockedmenubg1280x720.png"));
		bg.setSize(1920,1080);
		if (Config.res==2) {
			icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/lockedmenubg1280x720.png"));
			bg.setSize(1280,720);
		}
		
		bg.setIcon(icon);
		panel.getJComponent().add(bg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}