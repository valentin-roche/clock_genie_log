package model;

import org.apache.commons.net.ntp.TimeStamp;

import view.ClockPanel;

public abstract class AbstractClock extends ClockPanel{
	private TimeSingleton ts;
	private TimeStampApp currentTime;
	private Integer refreshRate;
	
	public void notify(TimeStampApp timeStamp) {
		this.setCurrentTime(timeStamp);
		this.refresh();
	}
	
	public void refresh() {}

	public TimeStampApp getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(TimeStampApp currentTime) {
		this.currentTime = currentTime;
	}
}
