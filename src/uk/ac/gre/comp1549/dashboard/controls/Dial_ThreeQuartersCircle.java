/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author st8511x
 */
//DIAL TYPE 2. 
public class Dial_ThreeQuartersCircle extends Dial_FullCircle{//Child Class 1/3 of 270 deg. 

    public Dial_ThreeQuartersCircle() {
        super(100, 10, 500, 0);//radius, padding, max, pointValue
        DIAL_EXTENT_DEGREES = 270;
        DIAL_START_OFFSET_DEGREES = -45;
        dialMinValue = 0;
       // this.setBackground(Color.CYAN);
    }

}
