
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * StoreShape is the class that stores a shape with it's given stroke width and color.
 * 
 * @author Kevin Nguyen
 * @version 4.5.0 November 2015
 */
public class StoreShape {
    /**
     * myShape stores the shape data.
     */
    private final Shape myShape;
    /**
     * myColor stores the color data.
     */
    private final Color myColor;
    /**
     * myStroke stores the stroke data.
     */
    private final Stroke myStroke;
    /**
     * StoreShape() stores a shape with it's given stroke width and color.
     * @param theShape is the shape passed over.
     * @param theCurrentColor is the color passed over.
     * @param theBasicStroke is the stroke passed over.
     */
    public StoreShape(final Shape theShape, final Color theCurrentColor, 
                      final BasicStroke theBasicStroke) {
        myShape = theShape;
        myColor = theCurrentColor;
        myStroke = theBasicStroke;

    }
    /**
     * getShape returns the shape.
     * @return the shape.
     */
    public Shape getShape() {
        return myShape;
    }
    /**
     * getColor returns the color.
     * @return the color.
     */
    public Color getColor() {
        return myColor;
    }
    /**
     * getStroke returns the stroke.
     * @return the stroke.
     */
    public Stroke getStroke() {
        return myStroke;
    }

}
