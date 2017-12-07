
package actions;

import gui.DrawingPanel;
import gui.DrawingPanelGUI;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeSupport;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;

/**
 * EllipseAction sets up the Ellipse's action and creates functional buttons for it.
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class EllipseAction extends AbstractAction {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 1793304479680169559L;
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPCs = new PropertyChangeSupport(this);
    /**
     * EllipseAction() sets up the Ellipse's action, and creates a button for it.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public EllipseAction(final DrawingPanel theBlankSpaceSentOver) {
        super(DrawingPanelGUI.ELLIPSE);
        myBlankSpace = theBlankSpaceSentOver;
        final int keyCode = KeyStroke.getKeyStroke
                        (DrawingPanelGUI.ELLIPSE.charAt(0), 0).getKeyCode();
        putValue(MNEMONIC_KEY, keyCode);
        final String lowerWord = DrawingPanelGUI.ELLIPSE.toLowerCase(Locale.ENGLISH);
        putValue(SMALL_ICON, new ImageIcon("images/" + lowerWord + "_bw.gif"));
        myPCs.addPropertyChangeListener(myBlankSpace);

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        putValue(SELECTED_KEY, true);

        myBlankSpace.myadaptersettings(new MyEllipseMouseInputAdapter(), 
                                       new MyEllipseMouseInputAdapter());

    }
    /**
     * MyEllipseMouseInputAdapter is the mouse adapter used and sent to the drawing panel.
     * Let's drawing panel know what tool it's using.
     * @author Kevin Nguyen
     */
    class MyEllipseMouseInputAdapter extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {

            myPCs.firePropertyChange("StartAt", 0, theEvent.getPoint());

        }

        @Override
        public void mouseDragged(final MouseEvent theEvent) {

            myPCs.firePropertyChange("EllipseDragged", new Point(), theEvent.getPoint());

        }

        @Override
        public void mouseReleased(final MouseEvent theEvent) {

            myPCs.firePropertyChange("EllipseReleased", new Point(), theEvent.getPoint());

        }

    }

}
