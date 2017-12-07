
package actions;
import gui.DrawingPanel;
import gui.DrawingPanelGUI;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.beans.PropertyChangeSupport;

import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;




/**
 * LineActions sets up the Line's action and creates functional buttons for
 * it.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class LineActions extends AbstractAction {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -9109537438049181440L;
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * myintialPoint is the point first clicked on the drawing panel.
     */
    private Point myIntialPoint;
    /**
     * myPath is the path drawn from one pt to another.
     */
    private Path2D myPath;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPCs = new PropertyChangeSupport(this);
    /**
     * LineActions() sets up the Line's action, and creates a button for it.
     * 
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public LineActions(final DrawingPanel theBlankSpaceSentOver) {
        super(DrawingPanelGUI.LINE);

        myPath = new Path2D.Double();
        
        myBlankSpace = theBlankSpaceSentOver;
        final int keyCode = KeyStroke.getKeyStroke
                        (DrawingPanelGUI.LINE.charAt(0), 0).getKeyCode();
        putValue(MNEMONIC_KEY, keyCode);
        final String lowerWord = DrawingPanelGUI.LINE.toLowerCase(Locale.ENGLISH);
        putValue(SMALL_ICON, new ImageIcon("images/" + lowerWord + "_bw.gif"));
        myPCs.addPropertyChangeListener(myBlankSpace);
     
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        putValue(SELECTED_KEY, true);
        myBlankSpace.myadaptersettings(new MyLineMouseInputAdapters(), 
                                       new MyLineMouseInputAdapters());

    }
    /**
     * MyLineMouseInputAdapters is the mouse adapter used and sent to the
     * drawing panel. Let's drawing panel know what tool it's using.
     * 
     * @author Kevin Nguyen
     */
    class MyLineMouseInputAdapters extends MouseInputAdapter {

        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myIntialPoint =  theEvent.getPoint();
            myPCs.firePropertyChange("StartAt", 0, theEvent.getPoint());

        }

        @Override
        public void mouseDragged(final MouseEvent theEvent) {

            myPath.moveTo(myIntialPoint.getX(), myIntialPoint.getY());

            myPCs.firePropertyChange("LineDragged", new Point(), theEvent.getPoint());
           
         
        }

        @Override
        public void mouseReleased(final MouseEvent theEvent) {

            myPath.moveTo(myIntialPoint.getX(), myIntialPoint.getY());
            myPath.lineTo(theEvent.getX(), theEvent.getY());
            myPCs.firePropertyChange("LinetoPT", new Point(), theEvent.getPoint());
            myPath = new Path2D.Double();
  

        }

    }
}
