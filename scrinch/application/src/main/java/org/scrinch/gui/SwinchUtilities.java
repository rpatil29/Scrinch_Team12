/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scrinch.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Window;

/**
 *
 * @author layatje
 */
public class SwinchUtilities {
    
    public static final Color GRADIENT_STBY_BRIGHT      = new Color(0xdadada);
    public static final Color GRADIENT_STBY_DARK        = new Color(0xababab);

    public static final Color GRADIENT_EVAL_BRIGHT      = new Color(0xf8e2c5);
    public static final Color GRADIENT_EVAL_DARK        = new Color(0xffadc8);
    
    public static final Color GRADIENT_OK_BRIGHT        = new Color(0x61ff2f);
    public static final Color GRADIENT_OK_DARK          = new Color(0x0dc842);
    
    public static final Color GRADIENT_DONE_BRIGHT      = new Color(0xbfe1fc);
    public static final Color GRADIENT_DONE_DARK        = new Color(0x0e8aec);

    public static final Color GRADIENT_NOK_BRIGHT       = new Color(0xf99146);
    public static final Color GRADIENT_NOK_DARK         = new Color(0xd75100);
    
    public static final Color GRADIENT_CANCEL_BRIGHT    = new Color(0xfff50e);
    public static final Color GRADIENT_CANCEL_DARK      = new Color(0xf1c61c);
    
    public static final Color GRADIENT_POSTPONE_BRIGHT  = new Color(0xc9abe5);
    public static final Color GRADIENT_POSTPONE_DARK    = new Color(0xa97ad2);
    
    
    
    public static final Color GRADIENT_HIGH_BRIGHT      = new Color(0x61ff2f);
    public static final Color GRADIENT_HIGH_DARK        = new Color(0x0dc842);
    public static final Color OUTLINE_HIGH              = new Color(0x339d47);

    public static final Color GRADIENT_MEDIUM_BRIGHT    = new Color(0xfff50e);
    public static final Color GRADIENT_MEDIUM_DARK      = new Color(0xf1c61c);
    public static final Color OUTLINE_MEDIUM            = new Color(0xaf851a);

    public static final Color GRADIENT_LOW_BRIGHT       = new Color(0xf99146);
    public static final Color GRADIENT_LOW_DARK         = new Color(0xd75100);
    public static final Color OUTLINE_LOW               = new Color(0x904809);
    
    public static void centerWindowOverWindow(Window parent, Window child){
        int childX = parent.getX() + (parent.getWidth() - child.getWidth())/2;
        int childY = parent.getY() + (parent.getHeight() - child.getHeight())/2;
        child.setLocation(childX, childY);
    }

    private SwinchUtilities() {}

    

}
