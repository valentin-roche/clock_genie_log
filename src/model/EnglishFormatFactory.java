package model;

import controler.RefreshManager;

public class EnglishFormatFactory extends ClockFactory {

	public EnglishFormatFactory() {
		super();
	}
	
	public EnglishFormatClock createClock(int source) {
		EnglishFormatClock ef = new EnglishFormatClock();
		if (source == this.LOCAL_TIME) {
			RefreshManager.getInstance().registerObserver(ef, RefreshManager.SECONDS, source);
		}
		else {
			RefreshManager.getInstance().registerObserver(ef, RefreshManager.SECONDS, source);
		}
		return ef;
	}
}
