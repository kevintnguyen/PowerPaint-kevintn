
package model;
import actions.EllipseAction;
import actions.LineActions;
import actions.PencilAction;
import actions.RectangleAction;
import gui.DrawingPanel;
import gui.DrawingPanelGUI;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeSupport;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;


/**
 * BuildToolsMenu is the class that sets up the Tools Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class BuildToolsMenu extends JMenu {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -6827821635730233819L;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPCs = new PropertyChangeSupport(this);
    
    /**
     * BuildToolsMenu() sets up the Tool Menu by adding the necessary components to it like
     * JRadioButtonMenuItems.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     * @param thePencilAction is the pencil's action that is sent over.
     * @param theLineAction is the line's action that is sent over.
     * @param theRectangleAction is the rectangle's action that is sent over.
     * @param theEllipseAction is the ellipse's action that is sent over.
     */
    public BuildToolsMenu(final DrawingPanel theBlankSpaceSentOver, 
                          final PencilAction thePencilAction,
                          final LineActions theLineAction, 
                          final RectangleAction theRectangleAction,
                          final EllipseAction theEllipseAction) {
        super("Tools");
        final DrawingPanel mainBlankSpace = theBlankSpaceSentOver;
        myPCs.addPropertyChangeListener(mainBlankSpace);
        setMnemonic(KeyEvent.VK_T);
        setupButtons(thePencilAction, theLineAction, theRectangleAction, theEllipseAction);
        
    }
    /**
     * setupButtons() sets up the buttons for Tools Menu.
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
        
        final JRadioButtonMenuItem pencil = new JRadioButtonMenuItem(DrawingPanelGUI.PENCIL);
        pencil.setAction(thePencilAction);
        group.add(pencil);

        pencil.setIcon(null);

        final JRadioButtonMenuItem line = new JRadioButtonMenuItem(DrawingPanelGUI.LINE);
        line.setAction(theLineAction);
        group.add(line);

        line.setIcon(null);

        final JRadioButtonMenuItem rectangle = 
                        new JRadioButtonMenuItem(DrawingPanelGUI.RECTANGLE);
        rectangle.setAction(theRectangleAction);
        group.add(rectangle);
        rectangle.setIcon(null);
        final JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem(DrawingPanelGUI.ELLIPSE);
        ellipse.setAction(theEllipseAction);
        group.add(ellipse);
        ellipse.setIcon(null);

        add(pencil);

        add(line);

        add(rectangle);

        add(ellipse);

        
    }

}
