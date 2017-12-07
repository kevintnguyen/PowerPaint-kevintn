
package gui;
import actions.EllipseAction;
import actions.LineActions;
import actions.PencilAction;
import actions.RectangleAction;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


import model.BuildFileMenu;
import model.BuildHelpMenu;
import model.BuildOptionsMenu;
import model.BuildToolBar;
import model.BuildToolsMenu;

/**
 * DrawingPanelGUI is the class that sets up the GUI.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class DrawingPanelGUI extends JPanel {
    /**
     * PENCIL is a static final String "Pencil" that is used among other classes.
     */
    public static final String PENCIL = "Pencil";
    /**
     * LINE is a static final String "Line" that is used among other classes.
     */
    public static final String LINE = "Line";
    /**
     * ELLIPSE is a static final String "Ellipse" that is used among other classes.
     */
    public static final String ELLIPSE = "Ellipse";
    /**
     * RECTANGLE is a static final String "Rectangle" that is used among other classes.
     */
    public static final String RECTANGLE = "Rectangle";
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = -5166338301215545330L;
    /**
     * myFrame is the JFrame that adds all the panels together.
     */
    private final JFrame myFrame = new JFrame("PowerPaint");
    /**
     * start() sets up the JFrame and add the necessary components to it 
     * like JPanels and JMenus. Also instantiates new actions for tools.
     */
    public void start() {
        
        final DrawingPanel blankSpace = new DrawingPanel();
        final PencilAction pencilaction = new PencilAction(blankSpace);
        final LineActions lineaction = new LineActions(blankSpace);
        final RectangleAction rectangleaction = new RectangleAction(blankSpace);
        final EllipseAction ellipseaction = new EllipseAction(blankSpace);

        try {
            myFrame.setIconImage(ImageIO.read(new File("images/paintsplatter.png")));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // myFrame.add(this);
        myFrame.setLocationByPlatform(true);
        myFrame.setVisible(true);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(new BuildFileMenu(myFrame, blankSpace));
        menuBar.add(new BuildOptionsMenu(blankSpace));
        menuBar.add(new BuildToolsMenu(blankSpace, pencilaction, lineaction, rectangleaction,
                                       ellipseaction));
        menuBar.add(new BuildHelpMenu());

        myFrame.add(new BuildToolBar(blankSpace, pencilaction, lineaction, rectangleaction,
                                     ellipseaction),
                    BorderLayout.PAGE_END);
        myFrame.add(blankSpace);
        myFrame.setMinimumSize(blankSpace.getMinimumSize());
        myFrame.setJMenuBar(menuBar);
        myFrame.pack();

    }

}
