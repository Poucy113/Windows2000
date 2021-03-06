package org.lcdd.windows2k.frame.desktop.taskbar;

import org.lcdd.windows2k.Windows2KMain;
import org.lcdd.windows2k.frame.Windows2KFrame;
import org.lcdd.windows2k.frame.apps.Windows2KApp;
import org.lcdd.windows2k.utils.Utils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Windows2KTaskBar extends JDesktopPane {

    private JLabel startMenu = new JLabel(new ImageIcon("./assets/start_main.jpg"));
    private JLabel hour = new JLabel("<html>hour</html>");
    private Windows2KFrame frame;
    private List<JLabel> appLabels = new ArrayList<>();

    public Windows2KTaskBar(Windows2KFrame frame) {
        this.frame = frame;

        startMenu.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
        startMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                startMenu.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startMenu.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.DARK_GRAY));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.desktop.startMenuPane.setVisible(!frame.desktop.startMenuPane.isVisible());
            }
        });
        startMenu.setBounds(0, 0, 80, 40);
        startMenu.setVisible(true);
        super.add(startMenu);

        int i = 0;
        for (Windows2KApp app : frame.apps) {
            if (!app.visible) continue;

            JLabel label = new JLabel(new ImageIcon(Utils.getScaledImage(app.icon.getImage(), 40, 40)));
            label.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    label.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    label.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.DARK_GRAY));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!Windows2KMain.isInstalled) return;
                    app.createFrame();
                }
            });
            label.setBounds(80 + (i * 40) + 10, 0, 40, 40);
            label.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
            label.setVisible(true);
            super.add(label);

            appLabels.add(label);
            i++;
        }

        hour.setBounds((int) super.getBounds().getWidth() - 70, 0, 70, 40);
        hour.setVisible(true);
        super.add(hour);

        super.setBackground(Color.LIGHT_GRAY);
        super.setVisible(true);
    }

    public void updateLocation() {
        super.setBounds(0, (int) frame.desktop.getBounds().getHeight() - 40, (int) frame.desktop.getBounds().getWidth(), 40);

        hour.setBounds((int) super.getBounds().getWidth() - 70, 0, 70, 40);
        startMenu.setBounds(0, 0, 80, 40);

        int i = 0;
        for (JLabel label : appLabels) {
            label.setBounds(85 + (i * (40 + 5)), 0, 40, 40);
            i++;
        }
    }

    public String getHour() {
        return hour.getText();
    }

    public void setHour(String hour) {
        this.hour.setText("<html>" + hour + "</html>");
    }

}
