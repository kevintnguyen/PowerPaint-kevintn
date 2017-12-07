
package model;
import actions.EllipseAction;
import actions.LineActions;
import actions.PencilAction;
import actions.RectangleAction;
import gui.DrawingPanel;
import gui.DrawingPanelGUI;
import java.beans.PropertyChangeSupport;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * BuildToolBar is the class that sets up the Tool Bar.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class BuildToolBar extends JToolBar {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -8157651046660483186L;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPC = new PropertyChangeSupport(this);

    /**
     * BuildToolBar() sets up the Tool Bar by adding the necessary components to it like
     * JMenuItems.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     * @param thePencilAction is the pencil's action that is sent over.
     * @param theLineAction is the line's action that is sent over.
     * @param theRectangleAction is the rectangle's action that is sent over.
     * @param theEllipseAction is the ellipse's action that is sent over.
     */
    public BuildToolBar(final DrawingPanel theBlankSpaceSentOver, 
                        final PencilAction thePencilAction,
                        final LineActions theLineAction, 
                        final RectangleAction theRectangleAction,
                        final EllipseAction theEllipseAction) {
        super();
        final DrawingPanel mainblankspace = theBlankSpaceSentOver;
        myPC.addPropertyChangeListener(mainblankspace);
        setupButtons(thePencilAction, theLineAction, theRectangleAction,
                     theEllipseAction);
     

    }
    /**
     * setupButtons sets up the button for tool bar.
       * @param thePencilAction is the pencil's action that is sent over.
     * @param theLineAction is the line's action that is sent over.
     * @param theRectangleAction is the rectangle's action that is sent over.
     * @param theEllipseAction is the ellipse's action that is sent over.
     */
    private void setupButtons(final PencilAction thePencilAction,
                              final LineActions theLineAction, 
                              final RectangleAction theRectangleAction,
                              final EllipseAction theEllipseAction) {
        final ButtonGroup group = new ButtonGroup();
        final JToggleButton pencil = new JToggleButton(DrawingPanelGUI.PENCIL);

        pencil.setAction(thePencilAction);
        pencil.doClick();
        group.add(pencil);
        add(pencil);

        final JToggleButton line = new JToggleButton(DrawingPanelGUI.LINE);

        line.setAction(theLineAction);

        group.add(line);
        add(line);

        final JToggleButton rectangle1 = new JToggleButton(DrawingPanelGUI.RECTANGLE);
        rectangle1.setAction(theRectangleAction);
        group.add(rectangle1);
        add(rectangle1);

        final JToggleButton ellipse = new JToggleButton(DrawingPanelGUI.ELLIPSE);

        ellipse.setAction(theEllipseAction);
        group.add(ellipse);
        add(ellipse);
        
    }
}
