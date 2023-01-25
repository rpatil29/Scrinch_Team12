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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public abstract class GaugeLabel extends javax.swing.JLabel {

    protected final static Color GREEN_COLOR = Color.green;
    protected final static Color RED_COLOR = Color.red;
    protected final static Color BLUE_COLOR = new Color(128,128,255);
    protected final static Color YELLOW_COLOR = Color.yellow;

    protected final static Color OK_COLOR = GREEN_COLOR;
    protected final static Color NOT_OK_COLOR = RED_COLOR;
    protected final static Color QUITE_OK_COLOR = GREEN_COLOR;
    protected final static Color NOT_QUITE_OK_COLOR = YELLOW_COLOR;

    protected char unit = 'J';
    protected double minValue = 0;
    protected double maxValue;
    protected double expectedValue;
    protected double currentRealValue;

    public GaugeLabel() {
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setMaximumSize(new java.awt.Dimension(255000, 255000));
        setMinimumSize(new java.awt.Dimension(80, 80));
        setPreferredSize(new java.awt.Dimension(80, 80));
        setText("");
        setOpaque(true);
    }

    protected abstract void drawGauge(Graphics graphics);

    public void paint(Graphics graphics){
        Graphics2D g2D = (Graphics2D) graphics;
        g2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(graphics);
        drawGauge(graphics);
    }

    public void setUnit(char unit){
        this.unit = unit;
        repaint();
    }

    public void setExpectedValue(double expectedValue){
        this.expectedValue = expectedValue;
        repaint();
    }

    public void setCurrentRealValue(double currentRealValue){
        this.currentRealValue = currentRealValue;
        repaint();
    }

    public double getCurrentRealValue(){
        return currentRealValue;
    }

    protected double getRatioFor(double x){
        double a = 100.0/getRange();
        double b = -minValue*a;
        return (a*x+b)/100.0;
    }

    public double getRange(){
        return maxValue-minValue;
    }

    public int getExcess(){
        int excessValue = (int)(this.currentRealValue - this.expectedValue);
        return excessValue;
    }

    public void setMaxValue(double maxValue){
        this.maxValue = maxValue;
        repaint();
    }

    public void setMinValue(double minValue){
        this.minValue = minValue;
        repaint();
    }

    protected Color getCurrentColor(boolean invertedColors){
        Color currentColor = Color.black;
        double toCompare = getRatioFor(getCurrentRealValue())/getRatioFor(expectedValue);
        if(invertedColors){
            toCompare = 2-toCompare;
        }
        if(toCompare<0.85){
            currentColor = NOT_OK_COLOR;
        } else if(toCompare<=1){
            currentColor = NOT_QUITE_OK_COLOR;
        } else if(toCompare<=1.15){
            currentColor = QUITE_OK_COLOR;
        } else {
            currentColor = OK_COLOR;
        }
        return currentColor;
    }
}
