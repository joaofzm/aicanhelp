package br.com.joaofzm15.aicanhelp.frontEnd.gui.config;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Config {

	public static boolean borderless = false;
	public static int res = 2;
	
	public static int x;
	public static int y;
	public static double multiplier;
	
	public static void setMultiplier() {
		 UIManager UI=new UIManager();
		 UI.put("OptionPane.background",new ColorUIResource(153,187,255));
		 UI.put("Panel.background",new ColorUIResource(153,187,255));
		 
		if (res==1) {
			multiplier=1;
			x=1920;
			y=1080;
		} else if (res==2) {
			multiplier=0.666666666666666666666666;
			x=1280;
			y=720;
		}
	}
}
