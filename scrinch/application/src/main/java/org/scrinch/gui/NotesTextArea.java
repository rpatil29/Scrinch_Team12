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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JTextArea;

/**
 *
 * @author InCH Team
 */
public class NotesTextArea extends JTextArea{

    private String title;
    private BufferedImage titlePattern;
    private TexturePaint  texturePaint;
    private static final AffineTransform IDENTITY_MATRIX = new AffineTransform();
    private static final Font BIG_FONT = new Font("Arial", Font.BOLD, 74);
    private static final Color LIGHTEST_GRAY = new Color(230, 230, 230);

    public static final String HANDWRITTEN_FONT_PATH = ItemPostIt.HANDWRITTEN_FONT1_PATH;

    private static Font HANDWRITTEN_FONT;

    private void initFonts(){
        if(HANDWRITTEN_FONT==null){
            try{
                HANDWRITTEN_FONT = Font.createFont(Font.TRUETYPE_FONT,
                                                   NotesTextArea.class.getResourceAsStream(HANDWRITTEN_FONT_PATH));
                HANDWRITTEN_FONT = HANDWRITTEN_FONT.deriveFont(28.0f);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    public NotesTextArea(String title) {
        initFonts();
        this.title = title;
        createTitlePattern();
        this.setOpaque(false);
        this.setFont(HANDWRITTEN_FONT);
        this.setMargin(new Insets(5,5,5,5));
    }

    private void createTitlePattern(){
        this.titlePattern = new BufferedImage(600, 500,
                                              BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) this.titlePattern.getGraphics();

        g2.setFont(BIG_FONT);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 600, 500);

        g2.rotate(Math.toRadians(-35.0), 0, 500);
        g2.setColor(LIGHTEST_GRAY);
        g2.drawString(this.title, 100, 470);
        this.texturePaint = new TexturePaint(this.titlePattern,
                                             new Rectangle(this.titlePattern.getWidth(),
                                                           this.titlePattern.getHeight()));

    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        Paint backupPaint = g2.getPaint();

        g2.setPaint(this.texturePaint);

        g2.drawImage(this.titlePattern, 0, 0, null);

        g2.transform(IDENTITY_MATRIX);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());

        g2.setPaint(backupPaint);
        super.paint(g);
    }

}
