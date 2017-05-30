/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;

/**
 *
 * @author st8511x
 */
//IS IN CHARGE OF INSTANTIATING DESIRED OBJECTS BY USER
public class IndicatorFactory {

    //factory method
    public IIndicator createIndicator(String Indicatortype) {
        if (Indicatortype == null) {
            return null;

        } else if (Indicatortype.equalsIgnoreCase("full")) {

            return Singleton.getDialTypeSingleton(Indicatortype);//Use of singleton of base class

        } else if (Indicatortype.equalsIgnoreCase("threequater")) {
            return Singleton.getDialTypeSingleton(Indicatortype);

        } else if (Indicatortype.equalsIgnoreCase("half")) {
            return Singleton.getDialTypeSingleton(Indicatortype);

        } else if (Indicatortype.equalsIgnoreCase("quater")) {
            return Singleton.getDialTypeSingleton(Indicatortype);

        }
        else{
        return null;
        }
    }//factory method

//INCLUDE BARRRRRRR
}//factory
