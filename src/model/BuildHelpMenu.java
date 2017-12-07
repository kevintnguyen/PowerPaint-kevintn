
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * BuildHelpMenu is the class that sets up the Help Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class BuildHelpMenu extends JMenu {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -1911290848585409454L;

    /**
     * BuildHelpMenu() sets up the Help Menu by adding the necessary components to it like
     * JMenuItems.
     */
    public BuildHelpMenu() {
        super("Help");
        setMnemonic(KeyEvent.VK_H);

        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        add(about);
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                JOptionPane.showMessageDialog(null, "TCSS 305 PowerPaint, Autumn 2015");

            }
        });
    }
}
