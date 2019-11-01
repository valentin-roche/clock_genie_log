package model;

import controler.RefreshManager;

/**
 * Used to create a clock using the simple format.
 */
public class SimpleFormatFactory extends ClockFactory {

	public SimpleFormatFactory() {
		super();
	}
	
	/**
	 * Instanciates a clock and registers to the right observable depending on the time source.
	 * @param source Defines the time source usign the const from AbstractClock.
	 */
	public SimpleFormatClock createClock(int source) {
		SimpleFormatClock sf = new SimpleFormatClock();
		RefreshManager.getInstance().registerObserver(sf, RefreshManager.MINUTES, source);
		return sf;
	}
}
