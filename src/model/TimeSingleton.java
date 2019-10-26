package model;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

public final class TimeSingleton {
	
	private static final String SERVER_NAME = "pool.ntp.org";
	private static volatile TimeSingleton instance = null;
	private LocalTime time;
	
	private TimeSingleton() {
		super();
	}
	
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
	
	public TimeStampApp getTimeFromComputer() {
		time = LocalTime.now();
		return new TimeStampApp(time.getHour(), time.getMinute(), time.getSecond());
	}
	
	public TimeStamp getTimeFromNet() throws UnknownHostException {
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

	    System.out.println(TimeStamp.getNtpTime(atomicNtpTime + offset).getDate());
	    
		return null;
	}
}

