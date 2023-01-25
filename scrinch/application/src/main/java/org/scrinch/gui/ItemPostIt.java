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
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.Date;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import org.scrinch.model.FiboPoint;
import org.scrinch.model.Item;
import org.scrinch.model.Item.Status;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class ItemPostIt extends PostItPanel{

    private Item item;
    private Sprint sprint;

    public static final String HANDWRITTEN_FONT1_PATH = "/org/scrinch/gui/journal.ttf";
    public static final String HANDWRITTEN_FONT2_PATH = HANDWRITTEN_FONT1_PATH;

    public static Font HANDWRITTEN_FONT1;
    public static Font HANDWRITTEN_FONT2;
    private JLabel lbId;
    private JLabel lbTitle;
    private JLabel lbTypes;
    private JLabel lbRank;
    private JLabel lbStatus;
    private JLabel lbLastStatusAuthor;
    private JTextArea taDescription;
    private JLabel lbProjectItemSet;

    private void initFonts(){
        if(HANDWRITTEN_FONT1==null){
            try{
                HANDWRITTEN_FONT1 = Font.createFont(Font.TRUETYPE_FONT,
                                              getClass().getResourceAsStream(HANDWRITTEN_FONT1_PATH));
                HANDWRITTEN_FONT1 = HANDWRITTEN_FONT1.deriveFont(25.0f);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }

        if(HANDWRITTEN_FONT2==null){
            try{
                HANDWRITTEN_FONT2 = Font.createFont(Font.TRUETYPE_FONT,
                                            getClass().getResourceAsStream(HANDWRITTEN_FONT2_PATH));
                HANDWRITTEN_FONT2 = HANDWRITTEN_FONT2.deriveFont(23.0f);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    public ItemPostIt(Item item, Color color) {
        super(color);
        initFonts();
        this.setMinimumSize(new Dimension(900, 75));
        initComponents();
        setItem(item);
    }

    public void setItem(Item item){
        this.item = item;
        this.updateItem();
    }

    public void setSprint(Sprint sprint){
        this.sprint = sprint;
        this.updateItem();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        doLayoutOnSubContainers(this);
    }
    
    private void doLayoutOnSubContainers(Container parentContainer){
        Component[] components = parentContainer.getComponents();
        for(int i=0; i<components.length; i++){
            Component comp = components[i];
            if(comp instanceof Container){
                ((Container)comp).doLayout();
                doLayoutOnSubContainers((Container) comp);
            }
        }        
    }
        
    public void drawAllForPdf(Graphics2D g2){

        AffineTransform postItOriginTransform = g2.getTransform();

        double offset = 0.5;
        Point translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbId);
        g2.translate(translation.x, translation.y);
        this.lbId.paint(g2);
        g2.translate(offset, offset); //the translate and double paint is a workaround
        this.lbId.paint(g2);          //to have bold style in the pdf
        g2.setTransform(postItOriginTransform);
        
        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbTitle);
        g2.translate(translation.x, translation.y);
        this.lbTitle.paint(g2);
        g2.translate(offset, offset);
        this.lbTitle.paint(g2);
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbTypes);
        g2.translate(translation.x, translation.y);
        this.lbTypes.paint(g2);
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbRank);
        g2.translate(translation.x, translation.y);
        this.lbRank.paint(g2);
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbProjectItemSet);
        g2.translate(translation.x, translation.y);
        this.lbProjectItemSet.paint(g2);
        g2.translate(offset, offset);
        this.lbProjectItemSet.paint(g2);        
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbStatus);
        g2.translate(translation.x, translation.y);
        this.lbStatus.paint(g2);
        g2.translate(offset, offset);
        this.lbStatus.paint(g2);
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, lbLastStatusAuthor);
        g2.translate(translation.x, translation.y);
        this.lbLastStatusAuthor.paint(g2);
        g2.translate(offset, offset);
        this.lbLastStatusAuthor.paint(g2);
        g2.setTransform(postItOriginTransform);

        translation = ScrinchGuiToolkit.translateGraphicsToAbsolutePosition(g2, taDescription);
        g2.translate(translation.x, translation.y);
        this.taDescription.paint(g2);
        g2.setTransform(postItOriginTransform);

    }

    private void initComponents(){
        this.setLayout(new BorderLayout());

        this.lbId = new JLabel();
        this.lbId.setPreferredSize(new Dimension(100,26));
        this.lbId.setHorizontalAlignment(JLabel.CENTER);
        this.lbId.setFont(HANDWRITTEN_FONT1.deriveFont(Font.BOLD));
        this.add(this.lbId, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BorderLayout());
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        this.lbTitle = new JLabel();
        this.lbTitle.setFont(HANDWRITTEN_FONT1.deriveFont(Font.BOLD));
        titlePanel.add(this.lbTitle, BorderLayout.WEST);

        this.taDescription = new JTextArea();
        this.taDescription.setEditable(false);
        this.taDescription.setFocusable(false);
        this.taDescription.setLineWrap(true);
        this.taDescription.setWrapStyleWord(true);
        this.taDescription.setOpaque(false);
        this.taDescription.setFont(HANDWRITTEN_FONT2);
        centerPanel.add(this.taDescription, BorderLayout.CENTER);

        this.lbTypes = new JLabel();
        this.lbTypes.setFont(HANDWRITTEN_FONT1);
        this.lbTypes.setPreferredSize(new Dimension(250, 22));
        this.lbTypes.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(this.lbTypes, BorderLayout.CENTER);

        this.lbRank = new JLabel();
        this.lbRank.setFont(HANDWRITTEN_FONT1);
        this.lbRank.setPreferredSize(new Dimension(220, 22));
        this.lbRank.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(this.lbRank, BorderLayout.EAST);

        JPanel projectAndStatusPanel = new JPanel();
        projectAndStatusPanel.setOpaque(false);
        projectAndStatusPanel.setLayout(new BorderLayout());
        centerPanel.add(projectAndStatusPanel, BorderLayout.EAST);

        this.lbProjectItemSet = new JLabel();
        this.lbProjectItemSet.setFont(HANDWRITTEN_FONT1.deriveFont(Font.BOLD, 18.0f));
        this.lbProjectItemSet.setPreferredSize(new Dimension(220, 18));
        this.lbProjectItemSet.setHorizontalAlignment(JLabel.CENTER);
        projectAndStatusPanel.add(this.lbProjectItemSet, BorderLayout.NORTH);

//        JPanel statusPanel = new JPanel();
//        statusPanel.setOpaque(false);
//        statusPanel.setLayout(new BorderLayout());
//        projectAndStatusPanel.add(statusPanel, BorderLayout.SOUTH);

        this.lbStatus = new JLabel();
        this.lbStatus.setFont(HANDWRITTEN_FONT1.deriveFont(Font.BOLD, 18.0f));
        this.lbStatus.setPreferredSize(new Dimension(100, 18));
        this.lbStatus.setHorizontalAlignment(JLabel.RIGHT);
        projectAndStatusPanel.add(this.lbStatus, BorderLayout.WEST);

        this.lbLastStatusAuthor = new JLabel();
        this.lbLastStatusAuthor.setFont(HANDWRITTEN_FONT1.deriveFont(18.0f));
        this.lbLastStatusAuthor.setPreferredSize(new Dimension(110, 18));
        this.lbLastStatusAuthor.setHorizontalAlignment(JLabel.LEFT);
        projectAndStatusPanel.add(this.lbLastStatusAuthor, BorderLayout.EAST);

        JPanel blankPanel = new JPanel();
        blankPanel.setOpaque(false);
        blankPanel.setPreferredSize(new Dimension(220, 2));
        projectAndStatusPanel.add(blankPanel, BorderLayout.SOUTH);
    }

    private String getItemRelatedSprintsAsString(){
        String itemRelatedSprintsAsString = "";

        Set<Sprint> itemRelatedSprints = item.getRelatedSprints();
        for(Sprint sprint : itemRelatedSprints){
            itemRelatedSprintsAsString += sprint.getTitle() + ", ";
        }
        if(itemRelatedSprints.size()>0){
            itemRelatedSprintsAsString = itemRelatedSprintsAsString.substring(0,itemRelatedSprintsAsString.length()-2);
        }
        return itemRelatedSprintsAsString;
    }    
    
    private Color brighten(Color color){
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        hsb[2] = hsb[2] + 0.5f*(1.0f-hsb[2]);
        hsb[1] = 0.3f * hsb[1];
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    public void updateItem(){
        if(this.item!=null){
            this.lbId.setText("# " + item.getKey());
            this.lbTitle.setText(item.getTitle());
            this.lbTypes.setText("  - " + item.getOriginType().toString() + " , " + item.getWorkType().toString() + " - ");
            this.lbRank.setText("( B" + item.getBusinessValue() + " / W" + item.getEvaluation().toString() + " ) ");
            this.lbStatus.setText(item.getStatus(this.sprint).name().replace('_', ' '));
            if(!item.getStatus(this.sprint).equals(Item.Status.STANDBY)){
                this.lbLastStatusAuthor.setText("("+item.getVisasHistory().peek().member.getNickname()+")");
            }
            
            String description = item.getDescription();
            if(item.getStatus(this.sprint).equals(Status.DELAYED)
               && item.getRelatedSprints().size()>1){
                description +=  " (delayed in " + getItemRelatedSprintsAsString() + ")";
            }
            this.taDescription.setText(description);

            if(item.getProjectItemSet()!=null
               && item.getProjectItemSet().getProject()!=null){
                String projectText = item.getProjectItemSet().getProject().getProjectName()
                                     + " / " + item.getProjectItemSet().getTitle();
                this.lbProjectItemSet.setText(projectText);
            }

            this.setColor(brighten(this.item.getStatus(this.sprint).getColorCode()));
        }
    }

    public void adjustSize(){
        setOptimalHeight(this.taDescription);
    }

    public static void setOptimalHeight(JTextComponent component) {
        // get the root view of the component
        View view = component.getUI().getRootView(component);

        // get the current size and set the size of the root view to this size
        // - this normally does not happen until the component is drawn on screen afaik
        Dimension size = component.getSize();
        view.setSize(size.width, size.height);

        // retrieve the preferred size of the view according to the wrapping settings and so on
        Dimension newSize = new Dimension(size.width, (int) view.getPreferredSpan(View.Y_AXIS));

        component.setMinimumSize(newSize);
        component.setPreferredSize(newSize);
        component.setSize(newSize);
    }

    public Dimension getOptimalSize(int width) {

        int margins = this.lbId.getPreferredSize().width + this.lbRank.getPreferredSize().width;
        width -= margins;
        if( width<200 ){
            width = 200;
        }
        int height = 50; // we reserve 50 pixels as the minimum height needed to display title;

        View view = taDescription.getUI().getRootView(taDescription);
        view.setSize(width-30, 60);
        int descriptionTextHeight = (int) view.getPreferredSpan(View.Y_AXIS);
        height += descriptionTextHeight;

        return new Dimension(width+margins, height);
    }

    public static void main(String[] args){
        try{
            int frameWidth = 1024;
            int frameHeight = 400;
            final Item item = new Item(new ScrinchEnvToolkit());
            item.setTitle("isMessageObsolete bug");

            item.setDescription("This method returns bad value when persistence"
                                + " is replying to the client. Inconsistencies are created"
                                + " and a client restart occurs. It would be cool not to call two"
                                + " different methods for the same computing asdasda aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaaa aaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaa");

//            item.setDescription("This method returns bad value when persistence"
//                                + " is replying to the client. Inconsistencies are created");

            item.setBusinessValue(FiboPoint.THIRTEEN);
            item.setWorkType(new ScrinchEnvToolkit().getWorkTypeFactory().getWorkTypeList().get(0));
            item.setEvaluation(FiboPoint.EIGHT);
            item.setOriginType(new ScrinchEnvToolkit().getOriginTypeFactory().getOriginTypeList().get(0));
            item.addVisa(new Item.Visa(Item.Status.EVALUATED, null, new Date()));

            final ItemPostIt itemPostIt = new ItemPostIt(item, Color.YELLOW);
            final JFrame frame = new JFrame("PostIt test");
            frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e){
                    int availableWidth = frame.getSize().width-10;
                    itemPostIt.setSize(itemPostIt.getOptimalSize(availableWidth));
                }
            });

            frame.getContentPane().setLayout(null);

            frame.getContentPane().add(itemPostIt);
            itemPostIt.setLocation(0, 0);
            frame.setBounds(100, 100, frameWidth, frameHeight);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
