package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.HttpResponse;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Button;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Label;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.Panel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.PasswordField;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.components.TextField;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;
import br.com.joaofzm15.aicanhelp.frontEnd.http.HttpController;

public class RegisterAccountPanel implements ActionListener {

	private Panel panel;

	public Panel getPanel() {
		return panel;
	}

	private JLabel bg;

	private TextField usernameTextField;
	private PasswordField passwordTextField;

	private Button registerButton;
	private Button returnButton;

	private Label title;

	private JFrame frame;

	public RegisterAccountPanel(JFrame frame) {

		this.frame = frame;

		panel = new Panel(1920, 1080);

		title = new Label(0, 170, 1920, 130, "REGISTER ACCOUNT", 130, 200, 200, 255);
		panel.add(title);
		
		usernameTextField = new TextField(828, 450, 264, 56, "                 username", 28);
		usernameTextField.getJComponent().addActionListener(this);
		panel.add(usernameTextField);

		passwordTextField = new PasswordField(828, 550, 264, 56, "                  password", 28);
		passwordTextField.getJComponent().addActionListener(this);
		panel.add(passwordTextField);

		registerButton = new Button(815, 650, 290, 56, "CREATE", 20, 160, 20, 42);
		registerButton.getJComponent().addActionListener(this);
		panel.add(registerButton);

		returnButton = new Button(865, 950, 190, 56, "RETURN", 255, 20, 20, 62);
		returnButton.getJComponent().addActionListener(this);
		panel.add(returnButton);

		bg = new JLabel();

		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"));
		bg.setSize(1920, 1080);
		if (Config.res == 2) {
			icon = new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"));
			bg.setSize(1280, 720);
		}
		bg.setIcon(icon);
		panel.getJComponent().add(bg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == registerButton.getJComponent()) {
			HttpResponse<String> response = HttpController.getHttpResponseFromUrl("http://localhost:8080/players");
			List<Player> listOfUserNames = HttpController.parseJsonIntoPlayer(response);
			boolean repeated = false;
			for (Player player : listOfUserNames) {
				if (usernameTextField.getJComponent().getText().equals(player.getName())) {
					JOptionPane.showMessageDialog(null, "Username already in use! Please choose another one!");
					repeated=true;
					break;
				}
			} 
			
			if (!repeated) {
				if (usernameTextField.getJComponent().getText().equals("                 username")) {
					JOptionPane.showMessageDialog(null, "ERROR! Please type an username!");
				} else if(passwordTextField.getJComponent().getText().equals("                  password")) {
					JOptionPane.showMessageDialog(null, "ERROR! Please type a password!");
				} else {
					HttpController.post("    {"
							+ "     \"name\": \""+usernameTextField.getJComponent().getText()+"\","
							+ "     \"password\": \""+passwordTextField.getJComponent().getText()+"\""
							+ "    }",
							"http://localhost:8080/players");
					JOptionPane.showMessageDialog(null, "Account created!");
					LoginPanel initialPanel = new LoginPanel(frame);
					frame.getContentPane().removeAll();
					frame.getContentPane().add(initialPanel.getPanel().getJComponent());
					frame.revalidate();
					initialPanel.getPanel().getJComponent().repaint();
					
				}
			}
		}

		if (e.getSource() == returnButton.getJComponent()) {
			LoginPanel initialPanel = new LoginPanel(frame);
			frame.getContentPane().removeAll();
			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
			frame.revalidate();
			initialPanel.getPanel().getJComponent().repaint();
		}
	}

}