package org.lcdd.windows2k.frame.desktop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import org.lcdd.windows2k.frame.Windows2KFrame;
import org.lcdd.windows2k.frame.apps.Windows2KApp;
import org.lcdd.windows2k.utils.PaintRunnable;
import org.lcdd.windows2k.utils.Utils;

@SuppressWarnings("serial")
public class Windows2KFrameDesktop extends JDesktopPane {
	
	private Windows2KFrame frame;
	private List<JLabel> appLabels = new ArrayList<>();
	public List<PaintRunnable> paints = new ArrayList<>();
	
	public Windows2KFrameDesktop(Windows2KFrame frame) {
		this.frame = frame;
		
		int i = 0;
		for(Windows2KApp app : frame.apps) {
			JLabel label = new JLabel(new ImageIcon(Utils.getScaledImage(app.icon.getImage(), 80, 80)));
			JToolTip toolTip = label.createToolTip();
			toolTip.setTipText(app.name);
			label.addMouseListener(new MouseListener() {
				@Override public void mouseReleased(MouseEvent e) {}
				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseClicked(MouseEvent e) {
					app.createFrame();
				}
			});
			label.setBackground(new Color(0, 0, 0, 0));
			label.setBounds(40+(i*80)+20, 0, 80, 80);
			label.setVisible(true);
			super.add(label);
			
			appLabels.add(label);
			
			i++;
		}
		
		super.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		super.setBounds(0, 0, (int) frame.getBounds().getWidth(), (int) frame.getBounds().getHeight());
		super.setBackground(new Color(57, 107, 165));
		super.setVisible(true);
	}
	
	public void updateLocation() {
		int i = 0;
		for(JLabel label : appLabels) {
			label.setBounds(40+(i*80)+20, 0, 80, 80);
			i++;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		for(PaintRunnable pr : paints) {
			pr.paint(g);
		}
		super.paint(g);
	}
	
}
