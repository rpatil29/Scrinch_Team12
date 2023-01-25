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
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;


public class StatusLabel extends JLabel{    
    
    
    public enum Status {
        UKN (Color.DARK_GRAY, Color.LIGHT_GRAY, Color.BLACK),
        HIGH (SwinchUtilities.GRADIENT_HIGH_DARK, SwinchUtilities.GRADIENT_HIGH_BRIGHT, SwinchUtilities.OUTLINE_HIGH),
        MEDIUM (SwinchUtilities.GRADIENT_MEDIUM_DARK, SwinchUtilities.GRADIENT_MEDIUM_BRIGHT, SwinchUtilities.OUTLINE_MEDIUM),
        LOW (SwinchUtilities.GRADIENT_LOW_DARK, SwinchUtilities.GRADIENT_LOW_BRIGHT, SwinchUtilities.OUTLINE_LOW);
        
        protected Color gradientDark;
        protected Color gradientBright;
        protected Color outline; 
        
        Status(Color gradientDark, Color gradientBright, Color outline){
            this.gradientDark   = gradientDark;
            this.gradientBright = gradientBright;
            this.outline        = outline;
        }
    }
    
    private Status status = Status.UKN;
    private String textToDraw;
 
    public void setStatus(Status status){
        this.status = status;
        repaint();
    }
    
    public void setStatus(Status status, String textToDraw){
        this.textToDraw = textToDraw;
        setStatus(status);
    }
        
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(0, this.getHeight(), this.status.gradientDark,
                0, 0, this.status.gradientBright);
        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 4, 4);
        g2d.setPaint(this.status.outline);
        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 4, 4);
        
        if(textToDraw!=null){
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 10.0f));
            int widthOfText = g2d.getFontMetrics().stringWidth(textToDraw);
            int descentOfText = g2d.getFontMetrics().getDescent();
            g2d.drawString(textToDraw,this.getWidth()/2-widthOfText/2, this.getHeight()-descentOfText);
        }
        
        g2d.dispose();
        super.paintComponent(g);
    }
    
}
