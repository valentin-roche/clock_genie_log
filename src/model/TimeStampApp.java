package model;

public class TimeStampApp {
	private Integer hour = 0;
	private Integer minute = 0;
	private Integer second = 0;
	
	/**
	 * Constructor with params for a TimeStamp
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public TimeStampApp(int hour, int minute, int second) {
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
	}
	
	/**
	 * Instantiates an empty TimeStamp
	 */
	public TimeStampApp() {
	}

	/**
	 * Returns the value of the hour field
	 * @return the hour of the timestamp
	 */
	public Integer getHour() {
		return hour;
	}

	/**
	 * Sets the hour field
	 * @param hour The value to set the field to
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	/**
	 * Returns the value of the minute field
	 * @return the minute of the timestamp
	 */
	public Integer getMinute() {
		return minute;
	}

	/**
	 * Sets the minutes field
	 * @param minute The value to set the field to
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	/**
	 * Returns the value of the seconds field
	 * @return the second of the timestamp
	 */
	public Integer getSecond() {
		return second;
	}

	/**
	 * Sets the second field
	 * @param second The value to set the field to
	 */
	public void setSecond(Integer second) {
		this.second = second;
	}
	
	/**
	 * Simple toString for debugging purposes
	 */
	public String toString() {
		return this.getHour() + ":" + this.getMinute() + ":" + this.getSecond();
	}
}
