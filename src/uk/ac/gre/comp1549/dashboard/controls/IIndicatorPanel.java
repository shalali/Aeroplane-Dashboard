/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import javax.swing.JLabel;

/**
 *
 * @author st8511x
 */
//ALL PANELS MUST HAVE...
public interface IIndicatorPanel {

    void setLabel(String label);

    void setValue(int value);
}
