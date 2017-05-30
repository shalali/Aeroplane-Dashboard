/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

/**
 *
 * @author st8511x
 */
//DIAL TYPE 2. 
public class Dial_QuaterCircle extends Dial_FullCircle {//Child Class 1/3 of 270 deg. 

    public Dial_QuaterCircle() {
        super(100, 10, 180, 0);//radius, padding, max, pointValue
        DIAL_EXTENT_DEGREES = 90;
        DIAL_START_OFFSET_DEGREES = 50;
        dialMinValue = 0;

        // super.paintComponent(g);
        repaint();
        //double angle = Math.toRadians(((90 - DIAL_START_OFFSET_DEGREES)) - (value * (DIAL_EXTENT_DEGREES / dialMaxValue)));//****ORIGINAL****
    }

}
