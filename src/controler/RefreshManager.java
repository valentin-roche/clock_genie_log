package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.Timer;

import model.AbstractClock;
import model.ClockFactory;
import model.TimeSingleton;

public class RefreshManager {
	
	private static volatile RefreshManager instance = null;
	public static int MINUTES = 1;
	public static int SECONDS = 2;
	
	private HashMap<AbstractClock, Integer> observers_local = new HashMap<AbstractClock, Integer>();
	private HashMap<AbstractClock, Integer> observers_atomic = new HashMap<AbstractClock, Integer>();
	
	public final static RefreshManager getInstance() {
		if (instance == null) {
			synchronized(TimeSingleton.class) {
				if (RefreshManager.instance == null) {
					RefreshManager.instance = new RefreshManager();
				}
			}
		}
		
		return RefreshManager.instance;
	}
	
	public void registerObserver(AbstractClock observer, int refreshRate, int timeSource) {
		if (timeSource == ClockFactory.LOCAL_TIME) {
			this.observers_local.put(observer, refreshRate);
		}
		if (timeSource == ClockFactory.ATOMIC_TIME) {
			this.observers_local.put(observer, refreshRate);
		}
	}
	
	public void unregisterObserver(AbstractClock observer) {
		try {
			this.observers_local.remove(observer);
			this.observers_atomic.remove(observer);
		} catch(Exception e) {
			
		}
	}
	
	public void notifyMinute() {
		for (AbstractClock obs : observers_local.keySet()) {
			if (observers_local.get(obs) == MINUTES) {
				obs.notify(TimeSingleton.getInstance().getTimeFromComputer());
			}
		}
		for (AbstractClock obs : observers_atomic.keySet()) {
			if (observers_atomic.get(obs) == MINUTES) {
				try {
					obs.notify(TimeSingleton.getInstance().getAtomicTime());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void notifySecond() {
		if (TimeSingleton.getInstance().getTimeFromComputer().getSecond() == 0)
			notifyMinute();
		for (AbstractClock obs : observers_local.keySet()) {
			if (observers_local.get(obs) == SECONDS) {
				obs.notify(TimeSingleton.getInstance().getTimeFromComputer());
			}
		}
		for (AbstractClock obs : observers_atomic.keySet()) {
			if (observers_atomic.get(obs) == SECONDS) {
				try {
					obs.notify(TimeSingleton.getInstance().getAtomicTime());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void run() {
		
		notifyMinute();
		notifySecond();
		
		int delaySecond = 1000;
		
		ActionListener notifySeconds = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				notifySecond();
			}
		};
		
		Timer seconds = new Timer(delaySecond, notifySeconds);
		
		seconds.setRepeats(true);
		seconds.setInitialDelay(0);
		seconds.setCoalesce(true);
		seconds.start();

	}

}
