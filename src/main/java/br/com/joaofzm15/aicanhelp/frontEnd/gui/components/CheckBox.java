package br.com.joaofzm15.aicanhelp.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;

import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;

public class CheckBox implements FrameComponent {

	private JCheckBox checkBox;
	public JCheckBox getJComponent() {
		return checkBox;
	}
	
	public CheckBox(double x, double y, double xSize, double ySize, String text,
			int foreGroundRed, int foreGroundGreen, int foreGroundBlue,
			int backGroundRed, int backGroundGreen, int backGroundBlue, int fontSize) {
		
		checkBox = new JCheckBox();
		checkBox.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier),
				(int) (xSize * Config.multiplier), (int) (ySize * Config.multiplier));
		checkBox.setText(text);
		checkBox.setForeground(new Color(foreGroundRed, foreGroundGreen, foreGroundBlue));
		checkBox.setBackground(new Color(backGroundRed, backGroundGreen, backGroundBlue));
		checkBox.setFont(new Font("Impact", Font.PLAIN, (int) (fontSize * Config.multiplier)));
		checkBox.setFocusable(false);
	
		checkBox.setBorder(BorderFactory.createLineBorder(Color.white));
		checkBox.setBorderPainted(true);
		checkBox.setVisible(true);
	}

	public void setVisible(boolean value) {
		getJComponent().setVisible(value);
	}

}