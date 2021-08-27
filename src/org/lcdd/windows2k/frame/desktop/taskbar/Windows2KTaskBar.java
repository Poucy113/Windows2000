package org.lcdd.windows2k.frame.desktop.taskbar;

import java.awt.Color;

import javax.swing.JComponent;

import org.lcdd.windows2k.frame.Windows2KFrame;

@SuppressWarnings("serial")
public class Windows2KTaskBar extends JComponent {

	private String hour;
	private Windows2KFrame frame;
	
	public Windows2KTaskBar(Windows2KFrame frame) {
		this.frame = frame;
		
		updateLocation();
		super.setBackground(Color.LIGHT_GRAY);
		super.setVisible(true);
	}
	
	public void updateLocation() {
		super.setBounds(0, (int) frame.getBounds().getHeight()-60, (int) frame.getBounds().getWidth(), 30);
	}
	
	public String getHour() {return hour;}
	public void setHour(String hour) {this.hour = hour;}
	
}
