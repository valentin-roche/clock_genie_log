package model;
/**
 * This class is an implementation of the factory pattern displaying the english format.
 */
public class EnglishFormatClock extends AbstractClock {

	public EnglishFormatClock() {
		super();
	}
	
	public void run() {
	}
	
	/**
	 * Updates the clock's display using the current timestamp.
	 */
	public void refresh() {
		TimeStampApp ts = this.getCurrentTime();
		String period = "am";
		if (ts.getHour() > 12 || ts.getHour() == 0) {
			period = "pm";
			ts.setHour(ts.getHour()%13 + 1);
		}
		
		TicTac(ts.getHour() + period + ":" + ts.getMinute() + " " + ts.getSecond() + "s");
		
	}

}
