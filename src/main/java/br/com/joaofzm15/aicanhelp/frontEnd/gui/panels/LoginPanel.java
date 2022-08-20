package br.com.joaofzm15.aicanhelp.frontEnd.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import astral.components.visualComponents.CheckBox;
import astral.components.visualComponents.ComboBox;
import astral.components.visualComponents.Frame;
import astral.components.visualComponents.Page;
import astral.components.visualComponents.PasswordField;
import astral.components.visualComponents.TextButton;
import astral.components.visualComponents.TextField;
import br.com.joaofzm15.aicanhelp.AiCanHelpApplication;
import br.com.joaofzm15.aicanhelp.backEnd.entitites.Player;
import br.com.joaofzm15.aicanhelp.frontEnd.http.FrontEndInMemoryData;

public class LoginPanel extends Page implements ActionListener {

	private TextField usernameTextField;
	private PasswordField passwordTextField;

	private TextButton loginButton;
	private TextButton registerButton;
	private TextButton exitButton;
	
	private TextButton applyResButton;
	private CheckBox borderlessCheckBox;
	private ComboBox resComboBox;

	public LoginPanel() {
		super("Backgrounds/loginbg.png");
		
		List<String> resComboBoxOptionsList = new ArrayList<>();
		resComboBoxOptionsList.add("1280x720");
		resComboBoxOptionsList.add("1792x1008");
		resComboBoxOptionsList.add("1920x1080");
		getPanel().add(resComboBox = new ComboBox(25, 25, 300, 100, "", 255, 255, 255, 0, 111, 245, 28));
		resComboBox.getJComponent().setModel(new DefaultComboBoxModel(resComboBoxOptionsList.toArray()));
		if (Frame.res==1) {
			resComboBox.getJComponent().setSelectedIndex(2);
		} else if (Frame.res==2) {
			resComboBox.getJComponent().setSelectedIndex(1);
		} else if (Frame.res==3) {
			resComboBox.getJComponent().setSelectedIndex(0);
		}
		getPanel().add(borderlessCheckBox = new CheckBox(193, 130, 130, 65, "Borderless", 255, 255, 255, 0,111,245, 22));
		borderlessCheckBox.getJComponent().setSelected(Frame.borderless);
		borderlessCheckBox.getJComponent().addActionListener(this);
		
		getPanel().add(applyResButton = new TextButton(10, 138, 180, 56, "APPLY", 62,200,255,62, 40, 40, 220, false), this);
		

		
		getPanel().add(usernameTextField = new TextField(480, 300, 62, "  username", 63));
		
		getPanel().add(passwordTextField = new PasswordField(600, 300, 62, "  password", 63));
		
		getPanel().add(loginButton = new TextButton(750, 145, 62, "LOGIN", 61, 200,255,62, 40, 40, 220, false), this);

		getPanel().add(registerButton = new TextButton(850, 230, 62, "REGISTER", 61, 50, 50, 200, 30, 30, 255, false), this);

		getPanel().add(exitButton = new TextButton(950, 103, 62, "EXIT", 61, 200, 50, 50, 255, 50, 50, false), this);

		addBackground();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == applyResButton.getJComponent()) {
			
			String borderlessToString;
			if (borderlessCheckBox.getJComponent().isSelected()) {
				borderlessToString = "true";
			} else {
				borderlessToString = "false";
			}
			
			if (resComboBox.getJComponent().getSelectedIndex()==0) {
				Frame.setConfig(3, borderlessCheckBox.getJComponent().isSelected());
				FileWriter myWriter;
			    try {
			    	myWriter = new FileWriter("src/main/resources/Preferences/resPreferences.txt");
			    	myWriter.write("3-"+borderlessToString);
			        myWriter.close();
			      } catch (IOException iOexception) {
			        iOexception.printStackTrace();
			      }
			} else if (resComboBox.getJComponent().getSelectedIndex()==1) {
				Frame.setConfig(2, borderlessCheckBox.getJComponent().isSelected());
				FileWriter myWriter;
			    try {
			    	myWriter = new FileWriter("src/main/resources/Preferences/resPreferences.txt");
			    	myWriter.write("2-"+borderlessToString);
			        myWriter.close();
			      } catch (IOException iOexception) {
			        iOexception.printStackTrace();
			      }
			} else if (resComboBox.getJComponent().getSelectedIndex()==2) {
				Frame.setConfig(1, borderlessCheckBox.getJComponent().isSelected());
				FileWriter myWriter;
			    try {
			    	myWriter = new FileWriter("src/main/resources/Preferences/resPreferences.txt");
			    	myWriter.write("1-"+borderlessToString);
			        myWriter.close();
			      } catch (IOException iOexception) {
			        iOexception.printStackTrace();
			      }
			}
			
			//Centers frame only for 1280x720 resolutions
			boolean centered = false;
			if (Frame.res==3) {
				centered=true;
			}
			AiCanHelpApplication.appFrame.dispose();
			LoginPanel initialFrame = new LoginPanel();
			Frame frame = new Frame("AI Can Help", "Assets/windowIcon.png", initialFrame,centered);
			AiCanHelpApplication.appFrame=frame;
		}
		
		
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