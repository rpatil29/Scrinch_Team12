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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PostItPanel extends JPanel{

    private Color color;

    private static final int SHADOW_OFFSET = 4;

    public PostItPanel() {
        this.setOpaque(false);
    }

    public PostItPanel(Color color) {
        this.color = color;
        this.setOpaque(false);
    }

    public void setColor(Color color){
        this.color = color;
        this.repaint();
    }


    @Override
    public void paint(Graphics g){
        drawPaperAndShadow(g);
        
        super.paint(g);
    }
    
    private void prepareClipping(Graphics2D g) {
        Area paperArea = new Area(new Rectangle2D.Double(0,0, this.getWidth(), this.getHeight()));

        int cornerHeight = this.getHeight()/4;
        int cornerWidth  = this.getHeight()/2;

        GeneralPath path = new GeneralPath();
        path.moveTo(cornerWidth, 0);
        path.curveTo(cornerWidth/2, 0,
                     0, cornerHeight/2,
                     0, cornerHeight);
        path.lineTo(0, 0);
        path.lineTo(cornerWidth, 0);

        Area corner = new Area(path);
        
        paperArea.subtract(corner);
        
        g.setClip(paperArea);
    }
    
    public void drawPaperAndShadow(Graphics g){
        Graphics2D g2 = ((Graphics2D)g);
        Shape clip = g2.getClip();
        prepareClipping(g2);
        drawShadow(g2);

        drawPostIt(g2);
        g2.setClip(clip);
    }

    private void drawShadow(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(SHADOW_OFFSET,
                   SHADOW_OFFSET,
                   this.getWidth()-(2*SHADOW_OFFSET),
                   this.getHeight()-(2*SHADOW_OFFSET));
    }

    private void drawPostIt(Graphics2D g){
        g.setColor(this.color);
        g.fillRect(0,
                   0,
                   this.getWidth()-(2*SHADOW_OFFSET),
                   this.getHeight()-(2*SHADOW_OFFSET));
        g.setColor(Color.BLACK);
        g.drawRect(0,
                   0,
                   this.getWidth()-(2*SHADOW_OFFSET),
                   this.getHeight()-(2*SHADOW_OFFSET));

        int cornerHeight = this.getHeight()/4;
        int cornerWidth  = this.getHeight()/2;

        g.setColor(this.getBackground());
        
        drawCorner(g, cornerWidth, cornerHeight, 4, 4, Color.BLACK, false);
        drawCorner(g, cornerWidth, cornerHeight, 0, 0, color.darker(), true);
    }

    private void drawCorner(Graphics g, int cornerWidth, int cornerHeight,
                            int xOffset, int yOffset, Color color, boolean useGradient){
        GeneralPath path = new GeneralPath();
        path.reset();
        path.moveTo(0, cornerHeight);
        path.curveTo(xOffset +cornerWidth / 7,       yOffset +cornerHeight-(cornerHeight / 4),
                     xOffset +2*(cornerWidth / 7),   yOffset +cornerHeight-(cornerHeight / 4),
                     xOffset +3*(cornerWidth / 7),   yOffset +cornerHeight-(cornerHeight / 8));

        path.curveTo(xOffset +3*(cornerWidth / 7),   yOffset +cornerHeight/3,
                     5*(cornerWidth / 7),  0,
                     cornerWidth, 0);

        path.curveTo(cornerWidth/2, 0,
                     0, cornerHeight/2,
                     0, cornerHeight);
        Color backup = g.getColor();
        g.setColor(color);

        Paint paintBackup = null;
        if(useGradient){
            Paint gradientPaint = new GradientPaint(2*(cornerWidth / 7),
                                                    cornerHeight-(cornerHeight / 5),
                                                    this.color,
                                                    0, 0, Color.BLACK,
                                                    true);
            paintBackup = ((Graphics2D)g).getPaint();
            ((Graphics2D)g).setPaint(gradientPaint);
        }

        ((Graphics2D)g).fill(path);
        g.setColor(backup);
        if(useGradient){
            ((Graphics2D)g).setPaint(paintBackup);
        }
    }

    public static void main(String[] args){
        try{
            JFrame frame = new JFrame("PostIt test");

            PostItPanel postIt = new PostItPanel(Color.RED);
            postIt.add(new JLabel("TOTO"));
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(postIt, BorderLayout.CENTER);
            frame.setBounds(100, 100, 1024, 100);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }catch(Throwable t){
            t.printStackTrace();
        }
    }

}
