package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import astral.components.visualComponents.Page;
import astral.components.visualComponents.PasswordField;
import astral.components.visualComponents.TextButton;
import astral.components.visualComponents.TextField;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;

public class LoginPanel extends Page implements ActionListener {

	private TextField usernameTextField;
	private PasswordField passwordTextField;

	private TextButton loginButton;
	private TextButton registerButton;
	private TextButton exitButton;

	private JFrame frame;

	public LoginPanel() {
		super("Backgrounds/titlebg.png");

		getPanel().add(usernameTextField = new TextField(480, 300, 62, "  username", 63));
		
		getPanel().add(passwordTextField = new PasswordField(600, 300, 62, "  password", 63));
		
		getPanel().add(loginButton = new TextButton(750, 145, 62, "LOGIN", 62, 200,255,62, 40, 40, 220, false), this);

		getPanel().add(registerButton = new TextButton(850, 230, 62, "REGISTER", 62, 50, 50, 200, 30, 30, 255, false), this);

		getPanel().add(exitButton = new TextButton(950, 103, 62, "EXIT", 62, 200, 50, 50, 255, 50, 50, false), this);

		addBackground();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loginButton.getJComponent()) {
			Player p = FrontEndInMemoryData.logIn(usernameTextField.getJComponent().getText());

			if (!(p==null)) {
				if (passwordTextField.getJComponent().getText().equals(p.getPassword())) {
					MenuPanel initialPanel = new MenuPanel();
					getFrame().switchPage(initialPanel);
				} else {
					JOptionPane.showMessageDialog(null, "Wrong password!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Username not found!");
			}
		}
		
		if (e.getSource() == registerButton.getJComponent()) {
			RegisterAccountPanel initialPanel = new RegisterAccountPanel();
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