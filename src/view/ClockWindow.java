package view;

import java.io.IOException;
import java.io.Serializable;


import javax.swing.JFrame;

public class ClockWindow extends JFrame 
{
	//attribute

	ClockPanel Clock;
	
    public ClockWindow() 
    {
	   	this.setTitle("Clocks");
	   	this.setSize(800,600);
	   	this.setResizable(true);
	   	this.setLocationRelativeTo(null);
	   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	this.setLayout(null);
	   	
	   	//this.addWindowListener(new TimeTrackerOpeningListener(this));
	   	
	   	//this.addWindowListener(new TimeTrackerClosingListener());

	   	this.setVisible(true);
    }
    
    public void addClock(ClockPanel clock, int x, int y, int lx, int ly)
    {
    	clock.setBounds(x, y, lx, ly);
    	this.add(clock);
    }
}