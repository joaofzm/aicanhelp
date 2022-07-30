package br.com.joaofzm15.aicanhelp.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;

public class Label implements FrameComponent {

	private JLabel label;

	public Label(double x, double y, double xSize, double ySize, String imageURL) {
		label = new JLabel();
		
		ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(imageURL)); 
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance((int)(xSize*Config.multiplier),(int) (ySize*Config.multiplier),  java.awt.Image.SCALE_SMOOTH); 
		ImageIcon icon = new ImageIcon(resizedImage);
		
		label.setIcon(icon);
		label.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier), (int) (xSize * Config.multiplier),(int) (ySize * Config.multiplier));
//		label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	}
	
	public Label(double x, double y, String imageURL) {
		label = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imageURL)); 
		label.setIcon(icon);
		label.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier),icon.getIconWidth(),icon.getIconHeight());
//		label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	}

	public Label(double x, double y, double xSize, double ySize, String text, int fontSize, int red, int green, int blue) {
		label = new JLabel();
		label.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier), (int) (xSize * Config.multiplier),
				(int) (ySize * Config.multiplier));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setText(text);
		label.setFont(new Font("Impact", Font.PLAIN, (int) (fontSize * Config.multiplier)));
		label.setForeground(new Color(red,green,blue));
//		label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
	}

	public JLabel getJComponent() {
		return label;
	}

	public void setVisible(boolean value) {
		getJComponent().setVisible(value);
	}
}
