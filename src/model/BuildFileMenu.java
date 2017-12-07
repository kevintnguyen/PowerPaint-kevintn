
package model;
import gui.DrawingPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * BuildFileMenu is the class that sets up the File Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class BuildFileMenu extends JMenu {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 3494159208284016876L;
    /**
     * myFrame is the used to store the GUI's current overall frame.
     */
    private final JFrame myFrame;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPCs = new PropertyChangeSupport(this);
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * BuildFileMenu() sets up the File Menu by adding the necessary components to it like
     * JMenuItems.
     * @param theOldFrame uses the GUI's current frame, so it could access the exit function.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public BuildFileMenu(final JFrame theOldFrame, final DrawingPanel theBlankSpaceSentOver) {
        super("File");
        myBlankSpace = theBlankSpaceSentOver;
        myPCs.addPropertyChangeListener(myBlankSpace);
        myFrame = theOldFrame;
        setMnemonic(KeyEvent.VK_F);
        setundobutton();
       
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myFrame.dispatchEvent(new WindowEvent(myFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        exit.setMnemonic(KeyEvent.VK_X);

        add(exit);

    }
    /**
     * setundobutton() creates the undo button.
     */
    private void setundobutton() {
        final JMenuItem undoAllChanges = new JMenuItem("Undo all Changes");
        undoAllChanges.setMnemonic(KeyEvent.VK_U);

        undoAllChanges.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(final PropertyChangeEvent theEvent) {

                if (myBlankSpace.getarraysize() > 0) {
                    undoAllChanges.setEnabled(true);
                } else {
                    undoAllChanges.setEnabled(false);
                }

            }
        });

        undoAllChanges.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                myPCs.firePropertyChange("CLEAR", myBlankSpace.getarraysize(),
                                          new ArrayList<>());

            }
        });
        add(undoAllChanges);
        addSeparator();
        
    }


}
