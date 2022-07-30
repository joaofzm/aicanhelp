package br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;

public class PasswordField implements FrameComponent {

	private JPasswordField textField;
	public JPasswordField getJComponent() {
		return textField;
	}
	
	private String placeHolderText;

	public PasswordField(double x, double y, double xSize, double ySize, String placeHolderText, int fontSize) {
		this.placeHolderText = placeHolderText;
		
		textField = new JPasswordField();
		textField.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier),
				(int) (xSize * Config.multiplier), (int) (ySize * Config.multiplier));
		textField.setText(placeHolderText);
		textField.setForeground(new Color(140, 140, 140));
		textField.setFont(new Font("Impact", Font.PLAIN, (int) (fontSize * Config.multiplier)));
		textField.setFocusable(true);
		textField.setEchoChar((char)0);
		textField.setBorder(BorderFactory.createLineBorder(new Color(50,200,50),3));
//		textField.setBorderPainted(false);
		
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				textField.setForeground(Color.black);
				if (textField.getText().equals(placeHolderText)) {
					textField.setText("");
					textField.setEchoChar('*');
				}					
			}
		});
	}
	
	public void resetToPlaceHolder() {
		textField.setText(placeHolderText);
		textField.setForeground(new Color(140, 140, 140));
	}
	
	public void setVisible(boolean value) {
		getJComponent().setVisible(value);
	}

}