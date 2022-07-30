package br.com.joaofzm15.aicanhelp.frontEnd.gui.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.com.joaofzm15.aicanhelp.frontEnd.gui.config.Config;

public class Frame {

	private JFrame frame;

	public JFrame getJFrame() {
		return frame;
	}
	
	public Frame() {
		frame.setContentPane(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"))));

		frame.setMinimumSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(Config.borderless);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		ImageIcon windowIcon = new ImageIcon(getClass().getClassLoader().getResource("Assets/windowIcon.jpg"));
		frame.setIconImage(windowIcon.getImage());
		frame.setTitle("Yu-Gi-Oh! - Advanced Stats");
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		frame.getContentPane().setBackground(Color.black);
	}

	public Frame(int x, int y) {
		frame = new JFrame();

		/*
		 * Necessary work-around in-case user is not using a border-less window.
		 * Although this background will be overlaid by the panel one, it's necessary to
		 * make sure the window borders respect the frame size and don't "eat" part of
		 * the frame.
		 */
		if (Config.res == 1) {
			frame.setContentPane(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1920x1080.png"))));
		} else if (Config.res == 2) {
			frame.setContentPane(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Backgrounds/bg1280x720.png"))));
		}

		frame.setSize(x, y);
		frame.setMinimumSize(new Dimension(x, y));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(Config.borderless);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		ImageIcon windowIcon = new ImageIcon(getClass().getClassLoader().getResource("Assets/windowIcon.jpg"));
		frame.setIconImage(windowIcon.getImage());
		frame.setTitle("Yu-Gi-Oh! - Advanced Stats");
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		frame.getContentPane().setBackground(Color.black);
	}

}
