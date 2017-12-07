
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.StoreShape;

/**
 * DrawingPanel is a class that sets up the drawing panel.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class DrawingPanel extends JPanel implements PropertyChangeListener {
    /**
     * serialVersionUID is generated to keep Checkstyle off.
     */
    private static final long serialVersionUID = 3316811765624525730L;
    /**
     * WIDTH is a int that stores the drawing panel width.
     */
    private static final int WIDTH = 400;
    /**
     * HEIGHT is a int that stores the drawing panel height.
     */
    private static final int HEIGHT = 200;
    /**
     * MY_GRID_SIZE is the size of the grid by every 10 pixels.
     */
    private static final int MY_GRID_SIZE = 10;
    /**
     * myCurrentIconColor is the color choosen from the color icon from the
     * options menu.
     */
    private Color myCurrentIconColor = Color.black;
    /**
     * MyMinSize is a Dimension that is used to return the drawing panel's
     * preferred and min size.
     */
    private final Dimension myMinSize = new Dimension(WIDTH, HEIGHT);
    /**
     * myPencilPath is the path for pencil, drawn on the graphics.
     */
    private Path2D myPencilPath;
    /**
     * myLinePath is the path for line, drawn on the graphics.
     */
    
    private Path2D myLinePath;
    /**
     * myLineStart is the path for line when it is hovering, drawn on the
     * graphics.
     */
    private Line2D myLineMeasure;
    /**
     * myStartPoint stores a point for a mouse click on panel.
     */
    private Point myStartPoint;
    /**
     * myEllipsesandRects can store ellipses and rectangles.
     */
    private RectangularShape myEllipsesandRects;
    /**
     * myCurrentMouseListener is the current Mouse Listener.
     */
    private MouseListener myCurrentMouseListener;
    /**
     * myCurrentMouseMotionListener is the current Mouse Motion Listener.
     */
    private MouseMotionListener myCurrentMouseMotionListener;
    /**
     * myGridBoolean determines the boolean whether the grid button is enabled
     * or not.
     */
    private boolean myGridBoolean;
    /**
     * myCurrentStrokeWidth is the currently selected width.
     */
    private int myCurrentStrokeWidth;
    /**
     * myShapeStores stores the shapes into an ArrayList. Not initialized yet.
     */
    private List<StoreShape> myShapeStores;

    /**
     * DrawingPanel() starts up the drawing panel.
     */
    private Path2D myLineHover;
    public DrawingPanel() {
        super();
        setupDrawingPanel();

    }

    /**
     * setupDrawingPanel sets up the necessary components for drawing panel.
     */
    private void setupDrawingPanel() {

        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        final Cursor crosshair = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(crosshair);
        myPencilPath = new GeneralPath();
        myLinePath = new Path2D.Double();
        myEllipsesandRects = new Ellipse2D.Double();
        addMouseListener(myCurrentMouseListener);
        addMouseMotionListener(myCurrentMouseMotionListener);
        myLineMeasure = new Line2D.Double();
        myCurrentStrokeWidth = 1;
        myShapeStores = new ArrayList<>();
        myStartPoint = new Point();
        myLineHover = new Path2D.Double();

    }

    @Override
    public Dimension getMinimumSize() {
        return myMinSize;
    }

    @Override
    public Dimension getPreferredSize() {
        return myMinSize;
    }

    /**
     * paintComponent is override and is used to paint on the drawing panel.
     * 
     * @param theGraphics is the graphics sent in from the panel.
     */
    protected void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        drawShapes(g2d);
        drawRunTimeShapes(g2d);
        drawGrid(g2d);
        checkforUndoButton();

    }

    /**
     * checkforUndoButton() checks if there is a shape drawn, then enables the
     * undo button.
     */
    private void checkforUndoButton() {
        if (!myShapeStores.isEmpty()) {
            firePropertyChange("UNDO", 0, myShapeStores.size());
        }

    }

    /**
     * drawRunTimeShapes() draws the Run time shapes as the user clicks and
     * drags the selected tool.
     * 
     * @param theGraphicsPanel is the Graphics2D sent over.
     */
    private void drawRunTimeShapes(final Graphics2D theGraphicsPanel) {
        theGraphicsPanel.setStroke(new BasicStroke(myCurrentStrokeWidth));
        theGraphicsPanel.setPaint(myCurrentIconColor);
        if (myCurrentStrokeWidth > 0) {
            
            theGraphicsPanel.draw(myPencilPath);
            
            theGraphicsPanel.draw(myEllipsesandRects);
            
            theGraphicsPanel.draw(myLineHover);
        }

       
        
    }
    /**
     * getarraysize() returns the ArrayList size from myShapeStores.
     * @return a integer from the ArrayList size.
     */
    public int getarraysize() {
        return myShapeStores.size();
    }
    /**
     * drawShapes() draws the shapes stored from the ArrayList myShapeStores.
     * @param theGraphicsPanel is the Graphics2D sent over.
     */
    private void drawShapes(final Graphics2D theGraphicsPanel) {
        for (final StoreShape o : myShapeStores) {
            theGraphicsPanel.setPaint(o.getColor());
            theGraphicsPanel.setStroke(o.getStroke());
            theGraphicsPanel.draw(o.getShape());

        }

    }
    /**
     * drawGrid() draws the grid to the panel if the grid button is true.
     * @param theGraphicsPanel is the Graphics2D sent over.
     */
    private void drawGrid(final Graphics2D theGraphicsPanel) {
        theGraphicsPanel.setPaint(Color.GRAY);
        theGraphicsPanel.setStroke(new BasicStroke(1));
        if (myGridBoolean) {
            for (int i = 0; i < getWidth(); i = i + MY_GRID_SIZE) {
                for (int r = 0; r < getHeight(); r = r + MY_GRID_SIZE) {
                    theGraphicsPanel.draw(new Rectangle(i, r, MY_GRID_SIZE, MY_GRID_SIZE));
                }
            }
            repaint();
        }
    }
    /**
     * grid sets the grid's boolean from the options menu.
     * @param theAnswer is the boolean sent over from the options menu.
     */
    public void grid(final boolean theAnswer) {
        myGridBoolean = theAnswer;
    }
    /**
     * myadaptersettings() sets up the MouseMotionListener 
     * and MouseListener for the drawing panel.
     * @param theNewMouseMotionListener is the new MouseMotionListener sent over.
     * @param theNewMouseListener is the new MouseListener sent over.
     */
    public void myadaptersettings(final MouseMotionListener theNewMouseMotionListener,
                                  final MouseListener theNewMouseListener) {
        removeMouseListener(myCurrentMouseListener);
        removeMouseMotionListener(myCurrentMouseMotionListener);
        myCurrentMouseListener = theNewMouseListener;
        myCurrentMouseMotionListener = theNewMouseMotionListener;
        addMouseMotionListener(theNewMouseMotionListener);
        addMouseListener(theNewMouseListener);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

        if ("ColorIcon".equals(theEvent.getPropertyName())) {
            myCurrentIconColor = (Color) theEvent.getNewValue();
            repaint();
        } else if ("Slider".equals(theEvent.getPropertyName())) {
            myCurrentStrokeWidth = (int) theEvent.getNewValue();

        } else if ("PencilDragged".equals(theEvent.getPropertyName())) {
            myPencilPath = (GeneralPath) theEvent.getNewValue();
            repaint();
        }  else if ("PencilReleased".equals(theEvent.getPropertyName())) {
            myPencilPath = (GeneralPath) theEvent.getNewValue();
            if (myCurrentStrokeWidth > 0) {
                myShapeStores.add(new StoreShape(myPencilPath, myCurrentIconColor,
                                                 new BasicStroke(myCurrentStrokeWidth)));
            }
            myPencilPath = new GeneralPath();
            repaint();

        } else if ("CLEAR".equals(theEvent.getPropertyName())) {
            myShapeStores = (List<StoreShape>) theEvent.getNewValue();

            myPencilPath = new GeneralPath();
            myLinePath = new Path2D.Double();
            myLineMeasure = new Line2D.Double();
            myEllipsesandRects = new Ellipse2D.Double();
            removeAll();
            revalidate();

            repaint();

        } else if ("StartAt".equals(theEvent.getPropertyName())) {
            myStartPoint = (Point) theEvent.getNewValue();
        } else if ("LineDragged".equals(theEvent.getPropertyName())) {
            myLinePath.moveTo(myStartPoint.getX(), myStartPoint.getY());
            myLineMeasure = new Line2D.Double(myLinePath.getCurrentPoint(),
                                            (Point2D) theEvent.getNewValue());
            myLineHover = new Path2D.Double(myLineMeasure);
            repaint();
        } else {
            checklineandrectangularshapes(theEvent);
        }

    }
    /**
     * checklineandrectangularshapes checks for more if else statements for theEvent.
     * @param theEvent is the event sent from propertyChange.
     */
    private void checklineandrectangularshapes(final PropertyChangeEvent theEvent) {
        if ("LinetoPT".equals(theEvent.getPropertyName())) {
            myLinePath.moveTo(myStartPoint.getX(), myStartPoint.getY());
            myLinePath.lineTo(((Point) theEvent.getNewValue()).getX(),
                          ((Point) theEvent.getNewValue()).getY());
            if (myCurrentStrokeWidth > 0) {
                myShapeStores.add(new StoreShape(myLinePath, myCurrentIconColor,
                                             new BasicStroke(myCurrentStrokeWidth)));
            }
            myLinePath = new Path2D.Double();
            myLineMeasure = new Line2D.Double();
            myLineHover = new Path2D.Double();

        } else if ("EllipseDragged".equals(theEvent.getPropertyName())) {
            checkcoordinEllipse(theEvent);

        } else if ("EllipseReleased".equals(theEvent.getPropertyName())) {
            if (myCurrentStrokeWidth > 0) {
                myShapeStores.add(new StoreShape(myEllipsesandRects, myCurrentIconColor,
                                             new BasicStroke(myCurrentStrokeWidth)));
            }
            myEllipsesandRects = new Ellipse2D.Double();

        } else if ("RectangleDragged".equals(theEvent.getPropertyName())) {
            checkcoordinRectangles(theEvent);

        } else if ("RectangleReleased".equals(theEvent.getPropertyName())) {
            if (myCurrentStrokeWidth > 0) {
                myShapeStores.add(new StoreShape(myEllipsesandRects, myCurrentIconColor,
                                              new BasicStroke(myCurrentStrokeWidth)));
            }
            myEllipsesandRects = new Ellipse2D.Double();
        }
        
    }

    /**
     * checkcoordinEllipse() checks the current coordinates for the mouse drag
     * and creates the ellipse from the initial point to current mouse position.
     * @param theEvent is the mouse point sent over.
     */
    private void checkcoordinEllipse(final PropertyChangeEvent theEvent) {
        if (((Point2D) theEvent.getNewValue()).getX() > myStartPoint.getX()
            && ((Point2D) theEvent.getNewValue()).getY() < myStartPoint.getY()) {
            myEllipsesandRects = new Ellipse2D.Double(myStartPoint.getX(), 
            ((Point2D) theEvent.getNewValue()).getY(), 
            ((Point2D) theEvent.getNewValue()).getX() - myStartPoint.getX(),
            myStartPoint.getY() - ((Point2D) theEvent.getNewValue()).getY());
            repaint();
        } else if (((Point2D) theEvent.getNewValue()).getX() < myStartPoint.getX()
                 && ((Point2D) theEvent.getNewValue()).getY() < myStartPoint.getY()) {
            myEllipsesandRects = new Ellipse2D.Double
            (((Point2D) theEvent.getNewValue()).getX(), 
                             ((Point2D) theEvent.getNewValue()).getY(), 
                             myStartPoint.getX() - ((Point2D) theEvent.getNewValue()).getX(),
                             myStartPoint.getY() - ((Point2D) theEvent.getNewValue()).getY());
            repaint();
        } else if (((Point2D) theEvent.getNewValue()).getX() < myStartPoint.getX()
                 && ((Point2D) theEvent.getNewValue()).getY() > myStartPoint.getY()) {
            myEllipsesandRects = new Ellipse2D.Double
            (((Point2D) theEvent.getNewValue()).getX(),
            myStartPoint.getY(),
            myStartPoint.getX() - ((Point2D) theEvent.getNewValue()).getX(),
            ((Point2D) theEvent.getNewValue()).getY() - myStartPoint.getY());
            repaint();
        } else {
            myEllipsesandRects = new Ellipse2D.Double
            (myStartPoint.getX(), myStartPoint.getY(), 
             ((Point2D) theEvent.getNewValue()).getX() - myStartPoint.getX(), 
             ((Point2D) theEvent.getNewValue()).getY() - myStartPoint.getY());
            repaint();
        }

    }
    /**
    * checkcoordinRectangles() checks the current coordinates for the mouse drag
    * and creates the ellipse from the initial point to current mouse position.
    * @param theEvent is the mouse point sent over.
    */
    private void checkcoordinRectangles(final PropertyChangeEvent theEvent) {
        if (((Point2D) theEvent.getNewValue()).getX() > myStartPoint.getX()
            && ((Point2D) theEvent.getNewValue()).getY() < myStartPoint.getY()) {
            myEllipsesandRects = new Rectangle2D.Double
            (myStartPoint.getX(), 
            ((Point2D) theEvent.getNewValue()).getY(), 
            ((Point2D) theEvent.getNewValue()).getX() - myStartPoint.getX(),
            myStartPoint.getY() - ((Point2D) theEvent.getNewValue()).getY());
            repaint();
        } else if (((Point2D) theEvent.getNewValue()).getX() < myStartPoint.getX()
                 && ((Point2D) theEvent.getNewValue()).getY() < myStartPoint.getY()) {
            myEllipsesandRects = new Rectangle2D.Double
            (((Point2D) theEvent.getNewValue()).getX(), 
             ((Point2D) theEvent.getNewValue()).getY(), 
            myStartPoint.getX() - ((Point2D) theEvent.getNewValue()).getX(),
            myStartPoint.getY() - ((Point2D) theEvent.getNewValue()).getY());
            repaint();
        } else if (((Point2D) theEvent.getNewValue()).getX() < myStartPoint.getX()
                 && ((Point2D) theEvent.getNewValue()).getY() > myStartPoint.getY()) {
            myEllipsesandRects = new Rectangle2D.Double
            (((Point2D) theEvent.getNewValue()).getX(),
            myStartPoint.getY(),
            myStartPoint.getX() - ((Point2D) theEvent.getNewValue()).getX(),
           ((Point2D) theEvent.getNewValue()).getY() - myStartPoint.getY());
            repaint();
        } else {
            myEllipsesandRects = new Rectangle2D.Double
            (myStartPoint.getX(), 
            myStartPoint.getY(),
           ((Point2D) theEvent.getNewValue()).getX() - myStartPoint.getX(),
            ((Point2D) theEvent.getNewValue()).getY() - myStartPoint.getY());
            repaint();
        }

    }

}
