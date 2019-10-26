package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.Timer;

import model.AbstractClock;
import model.TimeSingleton;

public class RefreshManager {
	
	private static volatile RefreshManager instance = null;
	public static int MINUTES = 1;
	public static int SECONDS = 2;
	
	private HashMap<AbstractClock, Integer> observers = new HashMap<AbstractClock, Integer>();
	
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
	
	public void registerObserver(AbstractClock observer, int refreshRate) {
		this.observers.put(observer, refreshRate);
	}
	
	public void unregisterObserver(AbstractClock observer) {
		this.observers.remove(observer);
	}
	
	public void notifyMinute() {
		for (AbstractClock obs : observers.keySet()) {
			if (observers.get(obs) == MINUTES) {
				obs.notify(TimeSingleton.getInstance().getTimeFromComputer());
			}
		}
	}
	
	public void notifySecond() {
		if (TimeSingleton.getInstance().getTimeFromComputer().getSecond() == 0)
			notifyMinute();
		for (AbstractClock obs : observers.keySet()) {
			if (observers.get(obs) == SECONDS) {
				obs.notify(TimeSingleton.getInstance().getTimeFromComputer());
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
