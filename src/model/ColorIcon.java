
package model;
import gui.DrawingPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;




/**
 * ColorIcon is a class that updates the Color Icon from the Options Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class ColorIcon implements Icon {
    /**
     * WIDTH_HEIGHT stores 15 as an int.
     */
    private static final int WIDTH_HEIGHT = 15;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPC = new PropertyChangeSupport(this);
    /**
     * myColorSelected is the current color used on PowerPaint.
     */
    private Color myColorSelected;
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * ColorIcon sets up the icon used for the Options Menu.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public ColorIcon(final DrawingPanel theBlankSpaceSentOver) {
        super();
        myBlankSpace = theBlankSpaceSentOver;
    }

    /**
     * Overrides the getIconHeight(), returns the height of the icon.
     */
    @Override
    public int getIconHeight() {
        return WIDTH_HEIGHT;
    }

    /**
     * Overrides the getIconWidth(), returns the width of the icon.
     */
    @Override
    public int getIconWidth() {
        return WIDTH_HEIGHT;
    }

    /**
     * paintIcon() paints the icon by filling a rectangle with the current
     * paint.
     */
    @Override
    public void paintIcon(final Component arg0, final Graphics theGraphicPassed,
                          final int theX, final int theY) {
        final Graphics graphicsInput;
        graphicsInput = theGraphicPassed;
        graphicsInput.setColor(myColorSelected);
        graphicsInput.fill3DRect(theX, theY, getIconWidth(), getIconHeight(), true);

    }

    /**
     * get() returns the current paint color.
     * 
     * @return a color.
     */
    public Color get() {
        return myColorSelected;
    }

    /**
     * add() adds a new color selected from the user.
     * 
     * @param theColorPassed is the new color choosen.
     */
    public void add(final Color theColorPassed) {
        myPC.addPropertyChangeListener(myBlankSpace);
        final Color oldColor = myColorSelected;
        myColorSelected = theColorPassed;
        myPC.firePropertyChange("ColorIcon", oldColor, myColorSelected);

    }

}
