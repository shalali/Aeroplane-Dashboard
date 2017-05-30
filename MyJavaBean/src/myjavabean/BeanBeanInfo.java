/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjavabean;

import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author st8511x
 */
public class BeanBeanInfo extends SimpleBeanInfo {
    
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor d = new PropertyDescriptor("destination", Bean.class, "getDestination", "setDestination");
            PropertyDescriptor h = new PropertyDescriptor("hours", Bean.class, "geHrs", "setHrs");
            PropertyDescriptor m = new PropertyDescriptor("mins", Bean.class, "getMins", "setMins");
            PropertyDescriptor a = new PropertyDescriptor("ampm", Bean.class, "getAMPM", "setAMPM");
            PropertyDescriptor s = new PropertyDescriptor("status", Bean.class, "getStatus", "setStatus");

            PropertyDescriptor[] pds = new PropertyDescriptor[]{d, h, m, a, s};
            return pds;
        } //method
        catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }//method properties 

    @Override
    public Image getIcon(int iconKind) {
        switch (iconKind) {
            case BeanInfo.ICON_COLOR_16x16:
                return loadImage("plane16.png");
            case BeanInfo.ICON_COLOR_32x32:
                return loadImage("plane32.png");
            case BeanInfo.ICON_MONO_16x16:
                return loadImage("plane16bw.png");
            case BeanInfo.ICON_MONO_32x32:
                return loadImage("plane32bw.png");
        }
        return null;
    }//method icon
}//CLASS
