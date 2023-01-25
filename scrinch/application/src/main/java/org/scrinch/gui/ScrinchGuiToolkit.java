/*
 Scrinch is a stand-alone Swing application that helps managing your
 projects based on Agile principles.
 Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
 Christian Lebaudy, Jean-Marc Borer

 Scrinch is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Scrinch is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scrinch.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.text.SimpleDateFormat;
import javax.swing.JComponent;

public class ScrinchGuiToolkit {

    private static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    private ScrinchGuiToolkit() {
    }

    public static synchronized SimpleDateFormat getDefaultDayFormat(){
        return DEFAULT_DATE_FORMAT;
    }

    public static String getDisplayableDouble(double d){
        return Double.toString(((double)Math.round(d*10))/(double)10);
    }

    public static void centerFrame(Window window){
        centerFrame(window, null);
    }

    public static final void centerFrame(Window window,Window reference){
        Dimension windowSize = window.getSize();
        int minX = 0;
        int minY = 0;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension availableSize = null;
        if( reference==null ){
            availableSize = screenSize;
        }else{
            availableSize = reference.getSize();
            minX = reference.getLocation().x;
            minY = reference.getLocation().y;
        }
        int xposition = minX + (int)(availableSize.getWidth()-windowSize.getWidth())/2;
        if( xposition<minX ){
            xposition = minX;
        }
        if( (xposition+windowSize.getWidth())>screenSize.getWidth() ){
            xposition = (int) (screenSize.getWidth()-windowSize.getWidth());
        }
        if( xposition<0 ){
            xposition = 0;
        }
        int yposition = minY + (int)(availableSize.getHeight()-windowSize.getHeight())/2;
        if( yposition<minY ){
            yposition = minY;
        }
        if( (yposition+windowSize.getHeight())>screenSize.getHeight() ){
            yposition = (int) (screenSize.getHeight()-windowSize.getHeight());
        }
        if( yposition<0 ){
            yposition = 0;
        }
        window.setLocation(xposition, yposition);
    }

    public static Point translateGraphicsToAbsolutePosition(
            Graphics2D g2,
            JComponent component){
        int xTranslate = component.getLocation().x;
        int yTranslate = component.getLocation().y;

        Container parent = component.getParent();
        while(parent!=null){
            xTranslate += parent.getLocation().x;
            yTranslate += parent.getLocation().y;
            parent = parent.getParent();
        }

        return new Point( xTranslate, yTranslate);
    }
    
}
