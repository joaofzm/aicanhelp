package br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;

public class ImageButton  implements FrameComponent, MouseListener {
	
	private JButton button;
	public JButton getJComponent() {
		return button;
	}
	
	public ImageButton(double x, double y, double xSize, double ySize, String imageURL) {
		button = new JButton();
		button.addMouseListener(this);
		button.setBounds((int) (x * Config.multiplier), (int) (y * Config.multiplier),
				(int) (xSize * Config.multiplier), (int) (ySize * Config.multiplier));
		button.setContentAreaFilled(false);

		ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource(imageURL));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance((int) (xSize * Config.multiplier),
				(int) (ySize * Config.multiplier), java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(resizedImage);
		button.setIcon(icon);

		button.setBorderPainted(false);
		button.setBorder(BorderFactory.createLineBorder(Color.red,6));
		button.setFocusable(false);
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		button.setBorderPainted(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		button.setBorderPainted(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
