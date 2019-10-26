package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClockPanel extends JPanel 
{
	//attribute 
	private JLabel clock;
	
	//builders
    public ClockPanel()
    {
        setLayout(new BorderLayout());
        clock = new JLabel();
        clock.setHorizontalAlignment(JLabel.CENTER);
        clock.setFont(clock.getFont().deriveFont(30f));
        clock.setVisible(true);
        add(clock);
    }
    
    //others
    public void TicTac(String time) 
    {
        clock.setText(time);
    }
}