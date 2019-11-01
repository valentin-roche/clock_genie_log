package model;

import controler.RefreshManager;

/**
 * Used to create a clock using the english format.
 */
public class EnglishFormatFactory extends ClockFactory {

	public EnglishFormatFactory() {
		super();
	}
	
	/**
	 * Instanciates a clock and registers to the right observable depending on the time source.
	 * @param source Defines the time source usign the const from AbstractClock.
	 */
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
