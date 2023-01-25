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
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JFrame;

public class DiskGaugeLabel extends org.scrinch.gui.GaugeLabel {

    private boolean invertedCurve;

    public DiskGaugeLabel() {
        setMinimumSize(new java.awt.Dimension(40, 40));
        setPreferredSize(new java.awt.Dimension(80, 80));
        invertedCurve = false;
    }

    public void setInvertedCurve(boolean inverted){
        this.invertedCurve = inverted;
        repaint();
    }

    protected void drawGauge(Graphics graphics){
       int scale = getWidth()/20;
        if(getWidth()>getHeight()){
            scale = getHeight()/20;
        }

        // draw pie percentage
        graphics.setColor(getCurrentColor(invertedCurve));
        int toExpectedValueAngle = 165;
        int angleOfArc = 0;
        //toExpectedValueAngle = (int)(330.0*getRatioFor(expectedValue));
        angleOfArc = toExpectedValueAngle - (int)(330.0*getRatioFor(getCurrentRealValue()));
        if(getCurrentRealValue()<0 && angleOfArc<-165) {
            angleOfArc=-165;
        } else if(getCurrentRealValue()>0 && angleOfArc>165) {
            angleOfArc=165;
        }

        graphics.fillArc(
                1,
                1,
                this.getWidth()-2,
                this.getHeight()-2,
                255-toExpectedValueAngle,
                angleOfArc);

        // write percentage string
        graphics.setFont(new Font("Monospaced",Font.BOLD,scale*4));
        DecimalFormat formatter = new DecimalFormat("000");
        String excessAsString = formatter.format(getExcess()) + this.unit;
        if(getExcess()>=0){
            excessAsString = "+" + excessAsString;
        }

        for(int i=0; i<excessAsString.length(); i++){
            graphics.setColor(Color.black);
            int topLeftCornerX = getWidth()/2+scale*(i*3-7);
            int topLeftCornerY = getHeight()/2+scale*2;
            graphics.fillRect(topLeftCornerX,topLeftCornerY,(int)(scale*2.5),scale*4);
            graphics.setColor(Color.white);
            graphics.drawString(excessAsString.charAt(i)+"",topLeftCornerX,topLeftCornerY+(int)(3.3*scale));
        }

        // draw tick
        graphics.setColor(Color.black);
        graphics.drawOval(1,1,getWidth()-2,getHeight()-2);

        graphics.fillOval(this.getWidth()/2-scale,this.getHeight()/2-scale,scale*2,scale*2);
        int[] xPoints = new int[3];
        xPoints[0] = getWidth()/2-scale/2;
        xPoints[1] = getWidth()/2+scale/2;
        xPoints[2] = getWidth()/2;
        int[] yPoints = new int[3];
        yPoints[0] = getHeight()/2;
        yPoints[1] = getHeight()/2;
        yPoints[2] = 1;

        graphics.fillPolygon(xPoints,yPoints,3);
    }

    public static void main(String[] args){
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DiskGaugeLabel gauge = new DiskGaugeLabel();
        gauge.setMaxValue(2000);
        gauge.setMinValue(-20);
        gauge.setExpectedValue(0);
        gauge.setInvertedCurve(false);
        myFrame.getContentPane().add(gauge);
        gauge.setPreferredSize(new Dimension(100,110));
        myFrame.pack();
        WindowUtilities.centerFrame(myFrame,null);
        myFrame.setVisible(true);
        gauge.setCurrentRealValue(1980);
    }
}
