package model;

import controler.RefreshManager;

public class SimpleFormatFactory extends ClockFactory {

	public SimpleFormatFactory() {
		super();
	}
	
	public SimpleFormatClock createClock(int source) {
		SimpleFormatClock sf = new SimpleFormatClock();
		RefreshManager.getInstance().registerObserver(sf, RefreshManager.MINUTES, source);
		return sf;
	}
}
