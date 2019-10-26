package model;

import controler.RefreshManager;

public class EnglishFormatFactory extends ClockFactory {

	public EnglishFormatFactory() {
		super();
	}
	
	public EnglishFormatClock createClock() {
		EnglishFormatClock ef = new EnglishFormatClock();
		RefreshManager.getInstance().registerObserver(ef, RefreshManager.SECONDS);
		return ef;
	}
}
