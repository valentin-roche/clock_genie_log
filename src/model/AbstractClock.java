package model;

import view.ClockPanel;

/**
 *	Base class for the clocks of the Factory pattern.
 */
public abstract class AbstractClock extends ClockPanel{
	private TimeSingleton ts;
	private TimeStampApp currentTime;
	private Integer refreshRate;
	
	/**
	 * Implementation of the Observer pattern, uses a TimeStampApp object to set the current time.
	 * @param timeStamp time supplied through the observer pattern
	 */
	public void notify(TimeStampApp timeStamp) {
		this.setCurrentTime(timeStamp);
		this.refresh();
	}
	
	public void refresh() {}

	/**
	 * Returns the clocks current time
	 * @return The corresponding TimeStamp
	 */
	public TimeStampApp getCurrentTime() {
		return currentTime;
	}

	/**
	 * Sets the clocks current time
	 * @param currentTime
	 */
	public void setCurrentTime(TimeStampApp currentTime) {
		this.currentTime = currentTime;
	}
}
