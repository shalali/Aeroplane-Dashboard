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
public class Dial_HalfCircle extends Dial_FullCircle{//Child Class 1/3 of 270 deg. 

    public Dial_HalfCircle() {
        super(100, 10, 45, 0);//radius, padding, max, pointValue
        DIAL_EXTENT_DEGREES = 180;
        DIAL_START_OFFSET_DEGREES = 0;
        dialMinValue = 0;
        // double angle = Math.toRadians((180 - (DIAL_START_OFFSET_DEGREES)) - (value * (DIAL_EXTENT_DEGREES / dialMaxValue)));//****ORIGINAL****

    }
}//CLASS
