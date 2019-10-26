package model;

public class TimeStampApp {
	/**
	 * 
	 */
	private Integer hour = 0;
	
	/**
	 * 
	 */
	private Integer minute = 0;
	
	/**
	 * 
	 */
	private Integer second = 0;
	
	/**
	 * 
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
	 * 
	 */
	public TimeStampApp() {
	}

	/**
	 * 
	 * @return
	 */
	public Integer getHour() {
		return hour;
	}

	/**
	 * 
	 * @param hour
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getMinute() {
		return minute;
	}

	/**
	 * 
	 * @param minute
	 */
	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}
	
	public String toString() {
		return this.getHour() + ":" + this.getMinute() + ":" + this.getSecond();
	}
}
