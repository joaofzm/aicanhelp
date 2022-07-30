package br.com.joaofzm15.yugiohstatslocal;

import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.components.Frame;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.config.Config;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.panels.LoginLoadingPanel;
import br.com.joaofzm15.yugiohstatslocal.frontEnd.gui.panels.LoginPanel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class YugiohStatsLocalApplication {

	public static void main(String[] args) {
		
		Config.setMultiplier();
		Frame frame = new Frame(Config.x, Config.y);
		
		LoginLoadingPanel initialPanel = new LoginLoadingPanel();
		frame.getJFrame().getContentPane().removeAll();
		frame.getJFrame().getContentPane().add(initialPanel.getPanel().getJComponent());
		frame.getJFrame().revalidate();
		initialPanel.getPanel().getJComponent().repaint();

		SpringApplicationBuilder builder = new SpringApplicationBuilder(YugiohStatsLocalApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		
		LoginPanel secondPanel = new LoginPanel(frame.getJFrame());
		frame.getJFrame().getContentPane().removeAll();
		frame.getJFrame().getContentPane().add(secondPanel.getPanel().getJComponent());
		frame.getJFrame().revalidate();
		secondPanel.getPanel().getJComponent().repaint();
	}

}
