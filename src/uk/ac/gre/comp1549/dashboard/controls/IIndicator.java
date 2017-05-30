/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;

/**
 *
 * @author st8511x
 */
public interface IIndicator {

    void paintComponent(Graphics g);

    void setValue(int value);
   //the remaining methods in the base class full circle dial made more sense to be protected
}
