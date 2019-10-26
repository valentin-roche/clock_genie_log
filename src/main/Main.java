package main;
import java.net.UnknownHostException;

import controler.RefreshManager;
import model.EnglishFormatFactory;
import model.SimpleFormatFactory;
import model.TimeSingleton;
import view.ClockWindow;

public class Main {

	public static void main(String[] args) {
		try {
			TimeSingleton.getInstance().getTimeFromNet();
		} catch (UnknownHostException e) {
			System.out.println("Hote inconnu : ");
			e.printStackTrace();
		}
		
		ClockWindow window = new ClockWindow();
		
		SimpleFormatFactory sfFactory = new SimpleFormatFactory();
		window.addClock(sfFactory.createClock(), 185, 100, 400, 30);
		
		EnglishFormatFactory efFactory = new EnglishFormatFactory();
		window.addClock(efFactory.createClock(), 185, 200, 400, 30);

		RefreshManager.getInstance().run();
		
		
	}

}
