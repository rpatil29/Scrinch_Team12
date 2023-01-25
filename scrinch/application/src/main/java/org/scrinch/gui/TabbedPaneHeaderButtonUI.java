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
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

class TabbedPaneHeaderButtonUI extends BasicTabbedPaneUI {
    
    public TabbedPaneHeaderButtonUI() {
        super();
    }

    @Override
    protected void paintTab(
        Graphics g,
        int tabPlacement,
        Rectangle[] rects,
        int tabIndex,
        Rectangle iconRect,
        Rectangle textRect) {
        
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);

        g.setColor(Color.BLACK);        
        underlineText(g, textRect, tabIndex);
        
        Component c = tabPane.getComponentAt(tabIndex);
        if( c instanceof HeadedPane){
            Graphics2D g2d = (Graphics2D)g;
            Rectangle rect = rects[tabIndex];
            Rectangle rectangle = new Rectangle(rect.x + rect.width -19, rect.y + 4, 14, 12);
            drawIcon(g2d, (HeadedPane)c, rectangle);
        }
    }

    private void drawIcon(Graphics2D g, HeadedPane pane, Rectangle rectangle) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle);
        if( pane.isHeaderVisible() ){
            // the icon that represents the collapsed header
            g.drawLine(rectangle.x+3, rectangle.y+rectangle.height/2,
                       rectangle.x+rectangle.width-3, rectangle.y+rectangle.height/2);
        }else{
            // the icon that represents the expanded header
            g.drawLine(rectangle.x+3, rectangle.y+rectangle.height/2,
                       rectangle.x+rectangle.width-3, rectangle.y+rectangle.height/2);
            g.drawLine(rectangle.x+rectangle.width/2, rectangle.y+2,
                       rectangle.x+rectangle.width/2, rectangle.y+rectangle.height-2);
        }
    }
    
    private void underlineText(Graphics g, Rectangle textRect, int tabIndex){
        if(this.tabPane.getSelectedIndex() == tabIndex){
            g.drawLine(textRect.x, textRect.y + textRect.height, 
                       textRect.x + textRect.width, textRect.y + textRect.height);
        }        
    }

    @Override
    protected int calculateTabWidth(
        int tabPlacement,
        int tabIndex,
        FontMetrics metrics) {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 24;
    }

    @Override
    protected MouseListener createMouseListener() {
        return new MyMouseHandler();
    }

    class MyMouseHandler extends MouseHandler {
        public MyMouseHandler() {
            super();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int tabIndex = -1;
            int tabCount = tabPane.getTabCount();
            for (int i = 0; i < tabCount; i++) {
                if (rects[i].contains(x, y)) {
                    tabIndex = i;
                    break;
                }
            }
	    if (tabIndex >= 0 && ! e.isPopupTrigger()) {
                Rectangle tabRect = rects[tabIndex];
                y = y - tabRect.y;
                if ((x >= tabRect.x + tabRect.width - 18)
                    && (x <= tabRect.x + tabRect.width - 8)
                    && (y >= 5)
                    && (y <= 15)) {
                    if(tabPane.getComponentAt(tabIndex) instanceof HeadedPane){
                        ((HeadedPane)tabPane.getComponentAt(tabIndex)).toggleHeaderVisible();
                        tabPane.revalidate();
                        tabPane.repaint();
                    }
                }
            }
        }
    }

}