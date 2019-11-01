package main;
import java.net.UnknownHostException;

import controler.RefreshManager;
import model.ClockFactory;
import model.EnglishFormatFactory;
import model.SimpleFormatFactory;
import model.TimeSingleton;
import view.ClockWindow;

public class Main {

	public static void main(String[] args) {
		
		ClockWindow window = new ClockWindow();
		
		SimpleFormatFactory sfFactory = new SimpleFormatFactory();
		window.addClock(sfFactory.createClock(ClockFactory.ATOMIC_TIME), 185, 100, 400, 30);
		
		EnglishFormatFactory efFactory = new EnglishFormatFactory();
		window.addClock(efFactory.createClock(ClockFactory.LOCAL_TIME), 185, 200, 400, 30);

		RefreshManager.getInstance().run();
		
		
	}

}
