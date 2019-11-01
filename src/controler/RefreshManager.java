package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.Timer;

import model.AbstractClock;
import model.ClockFactory;
import model.TimeSingleton;

/**
 * A singleton that handles notification for the clocks and get the time for every instances
 *
 */
public class RefreshManager {
	
	private static volatile RefreshManager instance = null;
	public static int MINUTES = 1;
	public static int SECONDS = 2;
	
	private HashMap<AbstractClock, Integer> observers_local = new HashMap<AbstractClock, Integer>();
	private HashMap<AbstractClock, Integer> observers_atomic = new HashMap<AbstractClock, Integer>();
	
	/**
	 * Returns the instance of RefreshManager or creates one
	 * @return the instance of the singleton
	 */
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
	
	/**
	 * 
	 * @param observer the clocks that is to be added as an observer
	 * @param refreshRate the refresh rate of said clock
	 * @param timeSource the source for the time displayed by said clock
	 */
	public void registerObserver(AbstractClock observer, int refreshRate, int timeSource) {
		if (timeSource == ClockFactory.LOCAL_TIME) {
			this.observers_local.put(observer, refreshRate);
		}
		if (timeSource == ClockFactory.ATOMIC_TIME) {
			this.observers_local.put(observer, refreshRate);
		}
	}
	
	/**
	 * Unregisters an observer from the notification system
	 * @param observer the observer that needs to be unregistered
	 */
	public void unregisterObserver(AbstractClock observer) {
		try {
			this.observers_local.remove(observer);
			this.observers_atomic.remove(observer);
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * This methods notifies the clocks that subscribed for the minute changes
	 */
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
	
	/**
	 * This methods notifies the clocks that subscribed for the seconds changes.
	 * It also calls the update for the minutes changes.
	 */
	public void notifySecond() {
		
		try {
			if (TimeSingleton.getInstance().getTimeFromComputer().getSecond() == 0 || TimeSingleton.getInstance().getAtomicTime().getSecond() == 0)
				notifyMinute();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
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
	
	/**
	 * This method is used to instantiate the different timers and clocks 
	 */
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
