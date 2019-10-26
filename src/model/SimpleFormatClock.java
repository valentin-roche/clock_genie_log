package model;

public class SimpleFormatClock extends AbstractClock {

	SimpleFormatClock() {
		super();
	}
	
	public void run() {
		
	}
	
	public void refresh() {
		TicTac(getCurrentTime().getHour() + "h" + getCurrentTime().getMinute());
	}

}
