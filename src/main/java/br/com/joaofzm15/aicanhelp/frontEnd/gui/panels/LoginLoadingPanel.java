package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astral.components.visualComponents.Label;
import astral.components.visualComponents.Page;


public class LoginLoadingPanel extends Page implements ActionListener {

	public LoginLoadingPanel() {
		
		super("Backgrounds/cleanbg.png");
		
		getPanel().add(new Label(0, 0, 1920,1080, "Assets/loading.png"));
		
		addBackground();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}