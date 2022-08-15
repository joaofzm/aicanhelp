package br.com.joaofzm15.aicanhelp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import astral.components.visualComponents.Frame;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginLoadingPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginPanel;


@SpringBootApplication
public class AiCanHelpApplication {
	
	public static Frame appFrame;

	public static void main(String[] args) {
		
		Frame.setConfig(3, false);
		LoginLoadingPanel loadingScreenPanel = new LoginLoadingPanel();
		Frame frame = new Frame("AI Can Help", "Assets/windowIcon.png", loadingScreenPanel,true);
		appFrame = frame;
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AiCanHelpApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		LoginPanel secondPanel = new LoginPanel();
		frame.switchPage(secondPanel);

	}

}
