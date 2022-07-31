package br.com.joaofzm15.aicanhelp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import astral.components.visualComponents.Frame;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.AddDeckPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.AddDuelPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginLoadingPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.LoginPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.MenuPanel;
import br.com.joaofzm15.aicanhelp.frontEnd.gui.panels.RegisterAccountPanel;


@SpringBootApplication
public class AiCanHelpApplication {

	public static void main(String[] args) {
		
		Frame.setConfig(2, false);
		LoginLoadingPanel loadingScreenPanel = new LoginLoadingPanel();
		Frame frame = new Frame("AI can help!", "Assets/windowIcon.png", loadingScreenPanel);
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AiCanHelpApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		LoginPanel secondPanel = new LoginPanel();
		
		//-------------------
//		AddDeckPanel secondPanel = new AddDeckPanel();
//		AddDuelPanel secondPanel = new AddDuelPanel();
//		MenuPanel secondPanel = new MenuPanel();
//		RegisterAccountPanel secondPanel = new RegisterAccountPanel();
//		ViewDataPanel secondPanel = new ViewDataPanel();
		
		//-------------------
		frame.switchPage(secondPanel);

	}

}
