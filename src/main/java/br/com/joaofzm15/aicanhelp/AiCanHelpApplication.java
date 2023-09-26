package br.com.joaofzm15.aicanhelp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import astral.components.visualComponents.Frame;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginLoadingPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginPanel;


@SpringBootApplication
public class AiCanHelpApplication {
	
	public static Frame appFrame;

	public static void main(String[] args) throws IOException   {
		
		//Reads preferences from txt
        Scanner scanner = new Scanner(new File("src/main/resources/Preferences/resPreferences.txt"));
        String[] splittedString = scanner.nextLine().split("-");

        int res = Integer.valueOf(splittedString[0]);
        boolean borderless = Boolean.valueOf(splittedString[1]);
        
        //Set Frame configuration and create loading panel
		Frame.setConfig(res, borderless);
		LoginLoadingPanel loadingScreenPanel = new LoginLoadingPanel();
		
		//Create frame with loadingPanel as panel, set Frame as centered if res = 3
		//Sets a static reference to Frame
		Frame frame;
		if (res == 3) {
			frame = new Frame("AI Can Help", "Assets/windowIcon.png", loadingScreenPanel,true);
		} else {
			frame = new Frame("AI Can Help", "Assets/windowIcon.png", loadingScreenPanel,false);
		}
		appFrame = frame;
		
		// Loads spring and connects to DB
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AiCanHelpApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		//Switch page to login screen
		LoginPanel secondPanel = new LoginPanel();
		frame.switchPage(secondPanel);

	}

}
