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

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import org.scrinch.model.Item;

public class StatusButton extends JButton{
   
    private Item.Status status = Item.Status.STANDBY;
    
    public StatusButton(){
        this.setOpaque(false);
    }
    
    public void setStatus(Item.Status status){
        this.status = status;
        this.setText(status.toString());
        repaint();
    }

    public Item.Status getStatus() {
        return status;
    }
    
    private GradientPaint getGradient(Item.Status status){
        if(status.equals(Item.Status.EVALUATED)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_EVAL_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_EVAL_BRIGHT);                
        }else if(status.equals(Item.Status.DONE)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_DONE_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_DONE_BRIGHT);                
        }else if(status.equals(Item.Status.OK)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_OK_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_OK_BRIGHT);                
        }else if(status.equals(Item.Status.NOT_OK)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_NOK_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_NOK_BRIGHT);                
        }else if(status.equals(Item.Status.CANCELLED)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_CANCEL_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_CANCEL_BRIGHT);                
        }else if(status.equals(Item.Status.POSTPONED)){
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_POSTPONE_DARK,
                                                       0, 0, SwinchUtilities.GRADIENT_POSTPONE_BRIGHT);                
        }else {
            return new GradientPaint(0, this.getHeight(), SwinchUtilities.GRADIENT_STBY_DARK,
                                                           0, 0, SwinchUtilities.GRADIENT_STBY_BRIGHT);
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setPaint(getGradient(this.status));
        g2d.fillRect(0,0,this.getWidth(), this.getHeight());
        g2d.dispose();
        super.paintComponent(g);
    }
}
