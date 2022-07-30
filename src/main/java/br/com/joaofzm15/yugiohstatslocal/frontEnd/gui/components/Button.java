package br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;

public class Button implements FrameComponent, MouseListener {

	private JButton button;
	public JButton getJComponent() {
		return button;
	}
	
	private int red;
	private int green;
	private int blue;

	public Button(double x, double y, double xSize, double ySize, String text, int red, int green, int blue, int fontSize) {
		this.red = red;
		this.green=green;
		this.blue = blue;
		
		button = new JButton();
		button.addMouseListener(this);
		button.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier),
				(int) (xSize * Config.multiplier), (int) (ySize * Config.multiplier));
		button.setContentAreaFilled(false);
		button.setText(text);
		button.setForeground(new Color(red, green, blue));
		button.setFont(new Font("Impact", Font.PLAIN, (int) (fontSize * Config.multiplier)));
		button.setFocusable(false);
	
		button.setBorder(BorderFactory.createLineBorder(Color.white));
		button.setBorderPainted(false);
	}

	public void setVisible(boolean value) {
		getJComponent().setVisible(value);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		button.setForeground(new Color(0,80,255));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setForeground(new Color(red,green,blue));
	}
}
