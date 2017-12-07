
package actions;
import gui.DrawingPanel;
import gui.DrawingPanelGUI;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.beans.PropertyChangeSupport;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MouseInputAdapter;



/**
 * PencilAction sets up the Pencil's action and creates functional buttons for
 * it.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class PencilAction extends AbstractAction {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 1630737615768228395L;
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * myPath is a soon to be General which is used for the Pencil tool.
     */
    private Path2D myPath;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPCs = new PropertyChangeSupport(this);

    /**
     * PencilAction() sets up the Pencil's action, and creates a button for it.
     * 
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public PencilAction(final DrawingPanel theBlankSpaceSentOver) {
        super(DrawingPanelGUI.PENCIL);
        myPath = new GeneralPath();
        myBlankSpace = theBlankSpaceSentOver;
        final int keyCode = KeyStroke.getKeyStroke
                        (DrawingPanelGUI.PENCIL.charAt(0), 0).getKeyCode();
        putValue(MNEMONIC_KEY, keyCode);
        final String lowerWord = DrawingPanelGUI.PENCIL.toLowerCase(Locale.ENGLISH);
        putValue(SMALL_ICON, new ImageIcon("images/" + lowerWord + "_bw.gif"));
        myPCs.addPropertyChangeListener(myBlankSpace);

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        putValue(SELECTED_KEY, true);
        myBlankSpace.myadaptersettings(new MyPencilMouseInputAdapters(),
                                       new MyPencilMouseInputAdapters());

    }

    /**
     * MyPencilMouseInputAdapters is the mouse adapter used and sent to the
     * drawing panel. Let's drawing panel know what tool it's using.
     * 
     * @author Kevin Nguyen
     */
    class MyPencilMouseInputAdapters extends MouseInputAdapter {
      
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myPath.moveTo(theEvent.getX(), theEvent.getY());
     

        };

        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myPCs.firePropertyChange("PencilReleased", new GeneralPath(), myPath);
            myPath = new GeneralPath();
        }

        @Override
        public void mouseDragged(final MouseEvent theEvent) {

            myPath.lineTo(theEvent.getX(), theEvent.getY());
            myPCs.firePropertyChange("PencilDragged", new GeneralPath(), myPath);

        }

    }

}
