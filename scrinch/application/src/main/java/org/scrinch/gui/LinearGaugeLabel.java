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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

public class LinearGaugeLabel extends org.scrinch.gui.GaugeLabel {

    private double latestReachableValue;

    public LinearGaugeLabel() {
        setMinimumSize(new java.awt.Dimension(10, 40));
        setPreferredSize(new java.awt.Dimension(40, 160));
        setMaximumSize(new java.awt.Dimension(40, 160));
    }

    public double getCurrentRealValue(){
        if (this.currentRealValue>=0){
           return this.currentRealValue>this.maxValue?this.maxValue:this.currentRealValue;
        } else {
            return this.currentRealValue<-this.maxValue?-this.maxValue:this.currentRealValue;
        }
    }

    protected void drawGauge(Graphics graphics){
        // draw frame
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);

        // draw expected value
        graphics.setColor(Color.BLUE);
        int internalWidth = this.getWidth()-2;
        int internalHeight = this.getHeight()-2;
        int expectedY = (int)Math.round(internalHeight*(1-getRatioFor(expectedValue)));
        graphics.fillRect(1,1+expectedY,internalWidth,internalHeight-expectedY);

        // draw current real value
        graphics.setColor(getCurrentColor(false));
        int realValueWidth = internalWidth/3;
        int realY = (int)Math.round(internalHeight*(1-getRatioFor(getCurrentRealValue())));
        graphics.fillRect(internalWidth/3,realY-1,realValueWidth,internalHeight-realY+2);

        int latestReachableValueY = (int)Math.round(internalHeight*(1-getRatioFor(latestReachableValue)));
        if(latestReachableValue<maxValue){
            // draw latest reachable value
            graphics.setColor(Color.RED);
            graphics.fillRect(1,latestReachableValueY-1,internalWidth,3);
        }

        // draw strings
        Graphics2D g2D = (Graphics2D)graphics;
        g2D.rotate(-Math.PI/2);

        int characterHeight = 10;
        int characterWidth = 25;

        // 1/ draw expected value string
        int xTextPosition = -expectedY-characterWidth;
        if(xTextPosition>=-internalHeight){
            graphics.setColor(Color.WHITE);
            graphics.drawString(""+(int)Math.round(expectedValue),xTextPosition,(realValueWidth+characterHeight)/2);
        }

        // 2/ draw current real value string
        xTextPosition = -realY-characterWidth;
        if(xTextPosition>=-internalHeight){
            graphics.setColor(Color.BLACK);
            graphics.drawString(""+(int)Math.round(getCurrentRealValue()),xTextPosition,(3*realValueWidth+characterHeight)/2);
        }

        // 3/ latest reachable value string
        if(latestReachableValue<maxValue){
            xTextPosition = -latestReachableValueY-characterWidth;
            if(xTextPosition>=-internalHeight){
                graphics.setColor(Color.RED);
                graphics.drawString(""+(int)Math.round(latestReachableValue),xTextPosition,(5*realValueWidth+characterHeight)/2);
            }
        }

        // 4/ max value string
        g2D.rotate(Math.PI/2);
        graphics.setColor(Color.BLACK);
        graphics.drawString(""+(int)Math.round(maxValue),(internalWidth-characterHeight)/2,characterHeight+2);
    }

    public double getLatestReachableValue() {
        return latestReachableValue;
    }

    public void setLatestReachableValue(double latestReachableValue) {
        this.latestReachableValue = latestReachableValue;
    }

    public static void main(String[] args){
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LinearGaugeLabel gauge = new LinearGaugeLabel();
        gauge.setMaxValue(72.0);
        gauge.setMinValue(-69.125);
        gauge.setLatestReachableValue(57);
        gauge.setExpectedValue(0);
        myFrame.getContentPane().add(gauge);
        gauge.setPreferredSize(new Dimension(100,100));
        myFrame.pack();
        myFrame.setVisible(true);
        gauge.setCurrentRealValue(1);
    }
}
