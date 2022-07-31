package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import astral.components.visualComponents.Label;
import astral.components.visualComponents.Page;
import astral.components.visualComponents.PasswordField;
import astral.components.visualComponents.TextButton;
import astral.components.visualComponents.TextField;

public class RegisterAccountPanel extends Page implements ActionListener {


	private TextField usernameTextField;
	private PasswordField passwordTextField;

	private TextButton registerButton;
	private TextButton returnButton;

	private Label title;

	private JFrame frame;

	public RegisterAccountPanel() {
		super("Backgrounds/cleanbg.png");

		getPanel().add(new Label(0, 100, 1920, 200, "REGISTER ACCOUNT", 210, 40, 40, 220, false));

		getPanel().add(usernameTextField = new TextField(480, 300, 62, "  username", 63));
		
		getPanel().add(passwordTextField = new PasswordField(600, 300, 62, "  password", 63));

		getPanel().add(registerButton = new TextButton(850, 230, 62, "REGISTER", 62, 50, 50, 200, 30, 30, 255, false), this);

		getPanel().add(returnButton = new TextButton(950, 218, 62, "RETURN", 70, 200, 50, 50, 255, 50, 50, false), this);

		addBackground();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		if (e.getSource() == registerButton.getJComponent()) {
//			HttpResponse<String> response = HttpController.getHttpResponseFromUrl("http://localhost:8080/players");
//			List<Player> listOfUserNames = HttpController.parseJsonIntoPlayer(response);
//			boolean repeated = false;
//			for (Player player : listOfUserNames) {
//				if (usernameTextField.getJComponent().getText().equals(player.getName())) {
//					JOptionPane.showMessageDialog(null, "Username already in use! Please choose another one!");
//					repeated=true;
//					break;
//				}
//			} 
//			
//			if (!repeated) {
//				if (usernameTextField.getJComponent().getText().equals("                 username")) {
//					JOptionPane.showMessageDialog(null, "ERROR! Please type an username!");
//				} else if(passwordTextField.getJComponent().getText().equals("                  password")) {
//					JOptionPane.showMessageDialog(null, "ERROR! Please type a password!");
//				} else {
//					HttpController.post("    {"
//							+ "     \"name\": \""+usernameTextField.getJComponent().getText()+"\","
//							+ "     \"password\": \""+passwordTextField.getJComponent().getText()+"\""
//							+ "    }",
//							"http://localhost:8080/players");
//					JOptionPane.showMessageDialog(null, "Account created!");
//					LoginPanel initialPanel = new LoginPanel(frame);
//					frame.getContentPane().removeAll();
//					frame.getContentPane().add(initialPanel.getPanel().getJComponent());
//					frame.revalidate();
//					initialPanel.getPanel().getJComponent().repaint();
//					
//				}
//			}
//		}

//		if (e.getSource() == returnButton.getJComponent()) {
//			LoginPanel initialPanel = new LoginPanel(frame);
//			frame.getContentPane().removeAll();
//			frame.getContentPane().add(initialPanel.getPanel().getJComponent());
//			frame.revalidate();
//			initialPanel.getPanel().getJComponent().repaint();
//		}
	}

}