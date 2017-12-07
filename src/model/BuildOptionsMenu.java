
package model;
import gui.DrawingPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeSupport;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * BuildOptionsMenu is the class that sets up the Options Menu.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class BuildOptionsMenu extends JMenu {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -3484625671979696814L;
    /**
     * myBlankSpace is the main drawing panel that is used.
     */
    private final DrawingPanel myBlankSpace;
    /**
     * myPCs creates a PropertyChangeSupport for this class.
     */
    private final PropertyChangeSupport myPC = new PropertyChangeSupport(this);
    /**
     * BuildOptionsMenu() sets up the Options Menu by adding the necessary
     *  components to it lik JMenuItems and JSlider.
     * @param theBlankSpaceSentOver is the main drawing panel sent over.
     */
    public BuildOptionsMenu(final DrawingPanel theBlankSpaceSentOver) {

        super("Options");
        myBlankSpace = theBlankSpaceSentOver;
        myPC.addPropertyChangeListener(myBlankSpace);
        setMnemonic(KeyEvent.VK_O);
        setupgridbutton();
        setupthickbutton();
        setupcolorbutton();

      

    }
    /**
     * setupgridbutton() sets up the grid button.
     */
    private void setupgridbutton() {
        final JCheckBox grid = new JCheckBox("     Grid");
        grid.setMnemonic(KeyEvent.VK_G);
        grid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theAction) {
                if (grid.isSelected()) {
                    myBlankSpace.grid(true);
                } else {
                    myBlankSpace.grid(false);
                }

            }
        });
        add(grid);
        addSeparator();
        
    }
    /**
     * setupcolorbutton() sets up the color icon button.
     */
    private void setupcolorbutton() {
        final JMenuItem colorIcon = new JMenuItem("Color...");
        final ColorIcon colorset = new ColorIcon(myBlankSpace);
        colorIcon.setIcon(colorset);
        colorIcon.setMnemonic(KeyEvent.VK_C);
        colorIcon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                final Color selectingcolor = JColorChooser.showDialog
                                (null, "choose a color...", Color.black);
                if (selectingcolor != null) {
                    colorset.add(selectingcolor);
                }
            }
        });
        add(colorIcon);
        
    }
    /**
     * setupthickbutton() sets up the thick button.
     */
    private void setupthickbutton() {
        final JMenu thickness = new JMenu("    Thickness");
        final JSlider thickslider = new JSlider();
        thickslider.setMinimum(0);
        final int maximumTix = 20;
        thickslider.setMaximum(maximumTix);
        final int majorTix = 5;
        thickslider.setMajorTickSpacing(majorTix);
        thickslider.setMinorTickSpacing(1);
        thickslider.setPaintLabels(true);
        thickslider.setPaintTicks(true);
        thickslider.setValue(1);
        thickness.setMnemonic(KeyEvent.VK_T);

        thickslider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theChangedSlider) {
                final JSlider current = (JSlider) theChangedSlider.getSource();
                if (!current.getValueIsAdjusting()) {
                        final int strokewidth = (int) current.getValue();
                        myPC.firePropertyChange("Slider", null, strokewidth);
                }

            }
        });

        thickness.add(thickslider);
        add(thickness);
        addSeparator();
        
    }
}
