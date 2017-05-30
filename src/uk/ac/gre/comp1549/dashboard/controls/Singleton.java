/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.gre.comp1549.dashboard.controls;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author st8511x
 */
//ENSURES THAT ONLY A SINGLE INSTANCE OF INDICATOR IS ACTED UPON
public class Singleton {

    //static full dial is only newed up once
    private static Dial_FullCircle fullDialSingleton = null;//static instance for singleton
    private static Dial_ThreeQuartersCircle threequaterSingleton = null;//static instance for singleton
    private static Dial_HalfCircle halfSingleton = null;//static instance for singleton
    private static final Dial_QuaterCircle quaterSingleton = null;//static instance for singleton

    private Singleton() {

    }

    //returns singleton of desired dial type by use
    public static IIndicator getDialTypeSingleton(String dialType) {
        IIndicator dial = null;

        switch (dialType) {//conditional operators f
            case "full":
                dial = (fullDialSingleton == null) ? new Dial_FullCircle() : fullDialSingleton;
                break;
            case "threequater":
                dial = (threequaterSingleton == null) ? new Dial_ThreeQuartersCircle() : threequaterSingleton;
                break;
            case "half":
                dial = (halfSingleton == null) ? new Dial_HalfCircle() : halfSingleton;

                break;
            case "quater":
                dial = (quaterSingleton == null) ? new Dial_QuaterCircle() : quaterSingleton;

                break;
            default:
                dial = null;
        }
        return dial;
    }

}//singleton class
