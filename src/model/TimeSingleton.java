package model;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

/**
 * A singleton designed to create timestamps from various sources.
 */
public final class TimeSingleton {
	
	private static final String SERVER_NAME = "pool.ntp.org";
	private static volatile TimeSingleton instance = null;
	private LocalTime time;
	
	private TimeSingleton() {
		super();
	}
	
	/**
	 * Returns the instance of the TimeSingleton
	 * @return the unique instance of the singleton
	 */
	public final static TimeSingleton getInstance() {
		if (instance == null) {
			synchronized(TimeSingleton.class) {
				if (TimeSingleton.instance == null) {
					TimeSingleton.instance = new TimeSingleton();
				}
			}
		}
		
		return TimeSingleton.instance;
	}
	
	/**
	 * Returns the time from the computer
	 * @return A TimeStamp based on the computer timer
	 */
	public TimeStampApp getTimeFromComputer() {
		time = LocalTime.now();
		return new TimeStampApp(time.getHour(), time.getMinute(), time.getSecond());
	}
	
	/**
	 * Returns the time from a distant server (SERVER_NAME) by default ntppool.org using the ntp protocol.
	 * @return A TimeStamp based on a distant server
	 * @throws UnknownHostException
	 */
	public TimeStampApp getAtomicTime() throws UnknownHostException {
		NTPUDPClient cli = new NTPUDPClient();
		cli.setDefaultTimeout(10_000);
		InetAddress inetAddress = InetAddress.getByName(SERVER_NAME);
		TimeInfo timeInfo = null;
		try {
			timeInfo = cli.getTime(inetAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long offset = 0;
		
		if (timeInfo.getOffset() != null) {
	        offset = timeInfo.getOffset();
	    }
		
		long currentTime = System.currentTimeMillis();
	    long atomicNtpTime = TimeStamp.getNtpTime(currentTime + offset).getTime();
	    Date ts = TimeStamp.getNtpTime(atomicNtpTime + offset).getDate();
	    //System.out.println(TimeStamp.getNtpTime(atomicNtpTime + offset).getDate());
	    
	    TimeStampApp ret = new TimeStampApp();
	    ret.setHour(ts.getHours());
	    ret.setMinute(ts.getMinutes());
	    ret.setSecond(ts.getSeconds());
	    
		return ret;
	}
}

