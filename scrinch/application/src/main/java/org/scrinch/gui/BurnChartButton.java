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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.scrinch.model.Item;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class BurnChartButton extends JButton implements Sprint.SprintListener{
    
    private static final int THUMBNAIL_MARGIN_IN_PERCENT = 20;
    
    private Sprint sprint;
    private ScrinchEnvToolkit toolkit;
    
    public BurnChartButton() {
        initComponents();
        this.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                setIcon(getChartIcon());
            }
        });
    }
    
    protected void releaseAllResources(){
        if(this.sprint!=null){
            this.sprint.removeSprintListener(this);
        }
    }

    public void setToolkit(ScrinchEnvToolkit toolkit){
        this.toolkit = toolkit;
    }

    public void setSprint(Sprint sprint){
        this.sprint = sprint;
        sprint.addSprintListener(this);
        this.repaint();
    }
    
    private Icon getChartIcon(){
        ImageIcon icon = null;
        if(this.sprint!=null){
            int thumbnailWidth = getWidth()-THUMBNAIL_MARGIN_IN_PERCENT*getWidth()/100;
            int thumbnailHeight = getHeight()-THUMBNAIL_MARGIN_IN_PERCENT*getHeight()/100;
            BufferedImage iconImage = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics g = iconImage.getGraphics();
            
            int xMargin = 0;
            
            String os = System.getProperty("os.name").toLowerCase();
            if (os.indexOf("mac") >= 0) {
                xMargin = 2;
            }

            double graphMaxXValue = sprint.getBusinessDaysCount();

            double workToBeDone = ItemToolkit.getActiveItemEvaluationCount(sprint.getItemList());
            double canBeDone = sprint.getPossibleVelocity()*sprint.getBusinessDaysCount();
            double graphMaxYValue = Math.max(workToBeDone,canBeDone);
            boolean isWorkToBeDoneMaxValue = (graphMaxYValue==workToBeDone);

            g.setColor(isWorkToBeDoneMaxValue?Color.BLUE:Color.GREEN);
            g.drawLine(0, thumbnailHeight, thumbnailWidth, 0);

            g.setColor(isWorkToBeDoneMaxValue?Color.GREEN:Color.BLUE);

            g.drawLine(0, thumbnailHeight, thumbnailWidth, (int)((graphMaxYValue-Math.min(workToBeDone,canBeDone))/graphMaxYValue*thumbnailHeight));
            List<Date> dateList = sprint.getWorkingDates();
            Collection<Item> itemList = sprint.getItemList();
            int dateWork = 0;
            List<Integer> dateWorkList = new ArrayList<Integer>();
            for (Date date : dateList) {
                if(date.compareTo(toolkit.getCurrentDate())<=0){
                    for (Item item : itemList) {
                        Item.Visa lastVisa = item.getLastDoneVisaIfTaskDoneInSprint(sprint);
                        if(lastVisa != null &&
                                lastVisa.date.equals(date) &&
                                lastVisa.status.isWorkDone()){
                            dateWork += item.getEvaluation().intValue();
                        }
                    }
                    dateWorkList.add(new Integer(dateWork));
                }
            }

            g.setColor(Color.RED);
            int lastXValue = xMargin;
            int lastYValue = thumbnailHeight;
            for(int i=0;i<dateWorkList.size();i++){
                int nextXValue = (int)(((double)(i+1))/graphMaxXValue*thumbnailWidth);
                int nextYValue = (int)((graphMaxYValue-dateWorkList.get(i))/graphMaxYValue*thumbnailHeight);
                g.drawLine(lastXValue, lastYValue, nextXValue, nextYValue);
                lastXValue = nextXValue;
                lastYValue = nextYValue;
            }
            
            BufferedImage finalIconImage = new BufferedImage(iconImage.getWidth(), iconImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D gf = (Graphics2D)finalIconImage.getGraphics();

            AffineTransform affine = new AffineTransform();
            if (!toolkit.isBurnUpChart()){
                affine.setTransform(1, 0, 0, -1, 0, 0);
                affine.translate(0, -finalIconImage.getHeight());
            } else {
                affine.setTransform(1, 0, 0, 1, 0, 0);
            }

            gf.drawImage(iconImage,affine, null);
            
            icon = new ImageIcon(finalIconImage);
        }
        
        return icon;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents


    public void realVelocityChanged(Sprint sprint) {
        resetIcon();
    }

    public void datesChanged(Sprint sprint) {
        resetIcon();
    }

    public void workChanged(Sprint sprint) {
        resetIcon();
    }

    public void itemsChanged(Sprint sprint) {
        resetIcon();
    }
    
    private void resetIcon(){
        this.setIcon(getChartIcon());
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
