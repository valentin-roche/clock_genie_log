package model;

import controler.RefreshManager;

public class SimpleFormatFactory extends ClockFactory {

	public SimpleFormatFactory() {
		super();
	}
	
	public SimpleFormatClock createClock() {
		SimpleFormatClock sf = new SimpleFormatClock();
		RefreshManager.getInstance().registerObserver(sf, RefreshManager.MINUTES);
		return sf;
	}
}
