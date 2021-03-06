package org.lcdd.windows2k.frame;

import org.lcdd.windows2k.back.AudioPlayerManager;
import org.lcdd.windows2k.back.ClockManager;
import org.lcdd.windows2k.frame.apps.*;
import org.lcdd.windows2k.frame.desktop.Windows2KFrameDesktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Windows2KFrame extends JFrame implements ComponentListener, WindowListener {

    public Windows2KFrameDesktop desktop;
    public ClockManager clockManager;
    public List<Windows2KApp> apps = new ArrayList<>();
    private AudioPlayerManager audio = new AudioPlayerManager();
    private Windows2KInstaller installer = new Windows2KInstaller();

    public Windows2KFrame() throws IOException {
        super("Windows 2000 Simulation");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setBounds(0, 0, 1250, 720);
        super.setIconImage(new ImageIcon("./assets/windows.png").getImage());

        super.addComponentListener(this);
        super.addWindowListener(this);

        apps.add(new Windows2KFileExplorerApp());
        apps.add(new Windows2KCmdApp());
        apps.add(new Windows2KCrashErrorApp());
        apps.add(new Windows2KAudioPlayerApp());
        apps.add(new Windows2KInternetExplorer());
        apps.add(installer);

        desktop = new Windows2KFrameDesktop(this);
        super.setContentPane(desktop);

        super.setVisible(true);

        clockManager = new ClockManager();

        desktop.updateLocation();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        desktop.updateLocation();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        audio.playAudioFile(new File("./assets/in.wav"));
        for (Windows2KApp a : apps) {
            if (a.name.equals("Windows installer")) {
                a.createFrame();
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        audio.playAudioFile(new File("./assets/out.wav"));
        audio.end = new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    public void putBluescreen(Component blue) {
        desktop.removeAll();
        desktop.add(blue);
    }
}
