package model;
/**
 * This class is an implementation of the factory pattern displaying the english format.
 */
public class SimpleFormatClock extends AbstractClock {

	SimpleFormatClock() {
		super();
	}
	
	public void run() {
		
	}
	
	/**
	 * Updates the clock's display using the current timestamp.
	 */
	public void refresh() {
		TicTac(getCurrentTime().getHour() + "h" + getCurrentTime().getMinute());
	}

}
