package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

/**
 * Dial_FullCircle. Draw a dial and indicate current value.
 *
 * @author COMP1549
 * @version 2.0
 */
public class Dial_FullCircle extends JPanel implements IIndicator {//COMPASS

    protected int radius; // radius of dial
    protected int padding; // padding outside the dial
    protected float dialMaxValue;  // dial runs from 0 to dialMaxValue
    protected int value; // current value - where the hand will point
    protected float dialMinValue = 0;//the starting value
    protected double handLength; // length of indicator hand
    protected double angle;
    /**
     * The extent of the dial. For a full circle this would be 360
     */
    protected float DIAL_EXTENT_DEGREES = 360;

    /**
     * Where the dial starts being draw from. Due north is 90.
     */
    //protected final float DIAL_START_OFFSET_DEGREES = -45;
    protected float DIAL_START_OFFSET_DEGREES = 90;

    /**
     * Default constructor - sets default values
     */
    public Dial_FullCircle() {
        this(100, 10, 360, 0);//radius, padding, max, pointValue

    }

    /**
     * @param radius - radius of the dial
     * @param padding - padding outside the dial
     * @param dialMaxValue - dial runs from 0 to dialMaxValue
     * @param value - current value - where the hand will point
     */
    public Dial_FullCircle(int radius, int padding, int dialMaxValue, int value) {
        // set size of the JPanel to be big enough to hold the dial plus padding
        setPreferredSize(new Dimension(2 * (radius + padding), 2 * (radius + padding)));
        this.radius = radius;
        this.padding = padding;
        this.dialMaxValue = dialMaxValue;
        this.value = value;
        handLength = 0.9 * radius; // hand length is fixed at 90% of radius
        //this.setBackground(java.awt.Color.red);
    }

    /**
     * This method is called every time the Dial needs drawing for instance when
     * the value has changed. It draws the dial itself and the hand in the
     * correct position to indicate the current value
     *
     * @param g - graphics object used to draw on the JPanel
     */
    @Override
    public void paintComponent(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // get a Graphics2D object to draw with
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));

        // draw centre of the dial as a small circle of fixed size
        Ellipse2D circle = new Ellipse2D.Double((radius + padding) - 5, (radius + padding) - 5, 10, 10);
        g2.fill(circle);

        // draw the dial itself
        Arc2D arc = new Arc2D.Double(padding, padding, 2 * radius, 2 * radius, DIAL_START_OFFSET_DEGREES, DIAL_EXTENT_DEGREES, Arc2D.Double.OPEN);
//        Arc2D arc = new Arc2D.Double(padding, padding, 2 * radius, 2 * radius, dialMinValue, dialMaxValue, Arc2D.Double.OPEN);

        g2.draw(arc);
        //g.fillArc(35, 45, 75, 95, 0, 90);
        g.setColor(java.awt.Color.BLUE);

        // draw the little lines at the start and end of the dial
        drawDialEnd(g2, Math.toRadians(DIAL_START_OFFSET_DEGREES));
        drawDialEnd(g2, Math.toRadians(DIAL_START_OFFSET_DEGREES + DIAL_EXTENT_DEGREES));//start mark

        float startMark = (DIAL_START_OFFSET_DEGREES + DIAL_EXTENT_DEGREES);
        float endMark = DIAL_START_OFFSET_DEGREES;

        //  double angle = ((value / dialMaxValue) * DIAL_EXTENT_DEGREES);
        if (DIAL_EXTENT_DEGREES != 90) {
            angle = Math.toRadians((180 - (DIAL_START_OFFSET_DEGREES)) - (value * (DIAL_EXTENT_DEGREES / dialMaxValue)));//****ORIGINAL****
        } else if (DIAL_EXTENT_DEGREES == 90) {
            angle = Math.toRadians((180 - (90 - DIAL_START_OFFSET_DEGREES)) - (value * (DIAL_EXTENT_DEGREES / dialMaxValue)));//****ORIGINAL****

        }
        drawHand(g2, angle, handLength);
    }

    protected void drawDialEnd(Graphics2D g2, double angle) {
        // calculate endpoint of line furthest from centre of dial
        Point2D outerEnd = new Point2D.Double((radius + padding) + radius * Math.cos(angle),
                (radius + padding) - radius * Math.sin(angle));
        // calculate endpoint of line closest to centre of dial
        Point2D innerEnd = new Point2D.Double((radius + padding) + ((radius + padding) * .8) * Math.cos(angle),
                (radius + padding) - ((radius + padding) * .8) * Math.sin(angle));
        // draw the line
        g2.draw(new Line2D.Double(outerEnd, innerEnd));
        //g2.draw(new Line2D.Double(outerEnd, innerEnd));
        // g2.setColor(java.awt.Color.red);

    }

    /**
     * Draw the hand on the dial to indicate the current value
     *
     * @param g2 - graphics object used to draw on the JPanel
     * @param angle - the angle on the dial at which the hand is to point
     * @param handLength - length of the hand
     */
    protected void drawHand(Graphics2D g2, double angle, double handLength) {
        // calculate the outer end of the hand
        Point2D end = new Point2D.Double((radius + padding) + handLength * Math.cos(angle), (radius + padding) - handLength * Math.sin(angle));

        // Point2D end = outerEnd;
// calculate the centre 
        Point2D center = new Point2D.Double(radius + padding, radius + padding);
        //     Draw the line
        //g2.draw(new Line2D.Double(center, end));
        g2.draw(new Line2D.Double(center, end));
    }

    /**
     * Set the value to be displayed on the dial
     *
     * @param value value
     */
    public void setValue(int value) {
        // don't let the value go over the maximum for the dial.  But what about the minimum?
        // this.value = (int) (value > dialMaxValue ? dialMaxValue : value);

        try {
            if (value <= dialMaxValue && value >= dialMinValue) {
                this.value = value;
            } else {
                JOptionPane.showMessageDialog(this,
                        "Range needs to be between " + dialMinValue + " & " + dialMaxValue,
                        "Invalid range",
                        JOptionPane.ERROR_MESSAGE);
            }
            repaint(); // causes paintComponent() to be called
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(this,e.getMessage());

        }

    }

}
