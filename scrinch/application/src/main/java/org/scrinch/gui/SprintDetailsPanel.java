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
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.scrinch.gui.reporting.SprintGraphDialog;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class SprintDetailsPanel extends javax.swing.JPanel implements Sprint.SprintListener {

    private Sprint sprint;
    private SprintPanel sprintPanel;
    private SprintsMainPanel sprintsMainPanel;
    private ScrinchEnvToolkit toolkit;

    public SprintDetailsPanel(SprintPanel sprintPanel, SprintsMainPanel sprintsMainPanel, ScrinchEnvToolkit toolkit) {
        initComponents();
        burnUpChart.putClientProperty("JButton.buttonType", "square");
        burnUpChart.setToolkit(toolkit);
        setAquaBorder(sprintPropertiesThirdPanel);
        setAquaBorder(jPanel2);
        setAquaBorder(workPanel);
        this.toolkit = toolkit;
        this.sprintPanel = sprintPanel;
        this.sprintsMainPanel = sprintsMainPanel;
    }

    public Sprint getSprint(){
        return this.sprint;
    }

    public SprintPanel getSprintPanel(){
        return this.sprintPanel;
    }

    private void setAquaBorder(JPanel titledPanel){
        TitledBorder actualTitledBorder = (TitledBorder)titledPanel.getBorder();
        Border border =  UIManager.getBorder("TitledBorder.aquaVariant");
        if(border!=null){
            titledPanel.setBorder(BorderFactory.createTitledBorder(border,
                                                                   actualTitledBorder.getTitle()));
        }        
    }

    protected void releaseAllResources(){
        if(sprint!=null){
            sprint.removeSprintListener(this);
        }
        burnUpChart.releaseAllResources();
    }

    public void realVelocityChanged(Sprint sprint){
        updateSprintValues();
    }

    public void datesChanged(Sprint sprint){
        updateSprintDates();
    }

    public void workChanged(Sprint sprint){
        updateSprintValues();
    }

    public void itemsChanged(Sprint sprint){
    }

    public void setSprint(Sprint sprint){
        if(this.sprint!=null){
            this.sprint.removeSprintListener(this);
        }
        this.sprint = sprint;
        this.sprint.addSprintListener(this);
        updateSprintDates();
        updateSprintValues();
        updateNotesButton();
        this.burnUpChart.setSprint(sprint);
    }

    private void updateSprintDates(){
        if(sprint.getEndDate()!=null && sprint.getStartDate()!=null){
            StringBuilder periodInfo = new StringBuilder();
            periodInfo.append(ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getStartDate()));
            periodInfo.append(" \u2192 ");
            periodInfo.append(ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getEndDate()));
            periodInfo.append(" (");
            periodInfo.append(Double.toString(sprint.getBusinessDaysCount()));
            periodInfo.append(" working days");
            periodInfo.append(")");
            periodLabel.setText(periodInfo.toString());
            
        }
    }

    private void updateNotesButton(){
        if((sprint.getPlanningMeetingReport()!=null&&sprint.getPlanningMeetingReport().length()>0)
           ||(sprint.getReviewMeetingReport()!=null&&sprint.getReviewMeetingReport().length()>0)
           ||(sprint.getDailyMeetingsNotes()!=null&&sprint.getDailyMeetingsNotes().length()>0)){
            notesButton.setBackground(Color.ORANGE);
        } else {
            notesButton.setBackground(UIManager.getColor("Button.background"));
        }
    }

    private void updateSprintValues(){
        titleTextField.setText(sprint.getTitle());
        
        totalWorkLabel.setText(Integer.toString(ItemToolkit.getActiveItemEvaluationCount(sprint.getItemList())));
        
        double possibleVelocity = sprint.getPossibleVelocity();
        velocityLabel.setText(ScrinchGuiToolkit.getDisplayableDouble(possibleVelocity));

        boolean showRequiredAndResultingVelocity = (!sprint.isTopical())
                                        || sprint.equals(toolkit.getSprintFactory().findTodaySprint());

        resultingVelocityLabel.setVisible(showRequiredAndResultingVelocity);
        resultingVelocityTitle.setVisible(showRequiredAndResultingVelocity);
        requiredVelocityLabel.setVisible(showRequiredAndResultingVelocity);
        requiredVelocityTitle.setVisible(showRequiredAndResultingVelocity);
        
        StatusLabel.Status status = StatusLabel.Status.HIGH;
        int excessInPercent = (int)(sprint.getOverloadedResourcesCount()*100/sprint.getAvailableResourcesInFiboPoints());
        String statusTextToDraw = "";
        if(excessInPercent>10){
            status = StatusLabel.Status.LOW;
            statusTextToDraw = "Remove some work !";
        } else if (excessInPercent<-10){
            status = StatusLabel.Status.MEDIUM;
            statusTextToDraw = "Add some work !";
        }
        
        excessWorkLabel.setStatus(status, statusTextToDraw);
        excessWorkLabel.setText(ScrinchGuiToolkit.getDisplayableDouble(-sprint.getOverloadedResourcesCount()));

        if(showRequiredAndResultingVelocity){
            try{
                double requiredVelocity =
                        ((double)ItemToolkit.getActiveItemEvaluationCount(sprint.getItemList()))
                        /sprint.getBusinessDaysCount();
                double resultingVelocity = sprint.getResultingVelocity();
                requiredVelocityLabel.setText(ScrinchGuiToolkit.getDisplayableDouble(requiredVelocity));
                resultingVelocityLabel.setText(ScrinchGuiToolkit.getDisplayableDouble(resultingVelocity ));

                if (((resultingVelocity-requiredVelocity)/requiredVelocity)<-0.2) {
                    resultingVelocityLabel.setStatus(StatusLabel.Status.LOW);
                } else if(((resultingVelocity-requiredVelocity)/requiredVelocity)>=-0.1) {
                    resultingVelocityLabel.setStatus(StatusLabel.Status.HIGH);
                } else {
                    resultingVelocityLabel.setStatus(StatusLabel.Status.MEDIUM);
                }
            }catch(Exception e){
            }
        }
    }
    
    private void seeGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Frame appFrame = (Frame)SwingUtilities.windowForComponent(this);
        SprintGraphDialog sprintGraphDialog = new SprintGraphDialog(appFrame, false, sprint, toolkit);
        sprintGraphDialog.pack();
        WindowUtilities.centerFrame(sprintGraphDialog, appFrame);
        sprintGraphDialog.setModal(true);
        sprintGraphDialog.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        sprintPropertiesMainPanel = new javax.swing.JPanel();
        sprintPropertiesTopPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        btRemove = new javax.swing.JButton();
        sprintPropertiesButton = new javax.swing.JButton();
        sprintPropertiesSecondPanel = new javax.swing.JPanel();
        periodLabel = new javax.swing.JLabel();
        sprintPropertiesThirdPanel = new javax.swing.JPanel();
        notesButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        velocityLeftPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lbVelocityTitle = new javax.swing.JLabel();
        resultingVelocityTitle = new javax.swing.JLabel();
        requiredVelocityTitle = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        velocityLabel = new javax.swing.JLabel();
        resultingVelocityPanel = new javax.swing.JPanel();
        resultingVelocityLabel = new org.scrinch.gui.StatusLabel();
        requiredVelocityLabel = new javax.swing.JLabel();
        velocityRightPanel = new javax.swing.JPanel();
        burnUpChart = new org.scrinch.gui.BurnChartButton();
        workPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        totalWorkLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        excessWorkLabel = new org.scrinch.gui.StatusLabel();

        setLayout(new java.awt.GridLayout(1, 0));

        sprintPropertiesMainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sprint properties"));
        sprintPropertiesMainPanel.setLayout(new java.awt.GridLayout(3, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name:"); // NOI18N
        sprintPropertiesTopPanel.add(jLabel1);

        titleTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        titleTextField.setPreferredSize(new java.awt.Dimension(150, 20));
        titleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTextFieldActionPerformed(evt);
            }
        });
        titleTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleTextFieldFocusLost(evt);
            }
        });
        sprintPropertiesTopPanel.add(titleTextField);

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        btRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRemove.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });
        sprintPropertiesTopPanel.add(btRemove);

        sprintPropertiesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/20-gear2.png"))); // NOI18N
        sprintPropertiesButton.setPreferredSize(new java.awt.Dimension(25, 25));
        sprintPropertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sprintPropertiesButtonActionPerformed(evt);
            }
        });
        sprintPropertiesTopPanel.add(sprintPropertiesButton);

        sprintPropertiesMainPanel.add(sprintPropertiesTopPanel);

        periodLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        periodLabel.setMaximumSize(new java.awt.Dimension(65635, 14));
        sprintPropertiesSecondPanel.add(periodLabel);

        sprintPropertiesMainPanel.add(sprintPropertiesSecondPanel);

        notesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/179-notepad.png"))); // NOI18N
        notesButton.setText("Notes"); // NOI18N
        notesButton.setToolTipText("Meeting Notes");
        notesButton.setMinimumSize(new java.awt.Dimension(150, 25));
        notesButton.setPreferredSize(new java.awt.Dimension(150, 25));
        notesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesButtonActionPerformed(evt);
            }
        });
        sprintPropertiesThirdPanel.add(notesButton);

        sprintPropertiesMainPanel.add(sprintPropertiesThirdPanel);

        add(sprintPropertiesMainPanel);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Velocity"));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        velocityLeftPanel.setLayout(new java.awt.GridLayout(1, 2));

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(3, 0));

        lbVelocityTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbVelocityTitle.setText("We can do:"); // NOI18N
        jPanel10.add(lbVelocityTitle);

        resultingVelocityTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        resultingVelocityTitle.setText("We have done:"); // NOI18N
        jPanel10.add(resultingVelocityTitle);

        requiredVelocityTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        requiredVelocityTitle.setText("We must do:"); // NOI18N
        jPanel10.add(requiredVelocityTitle);

        velocityLeftPanel.add(jPanel10);

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(3, 0));

        velocityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        velocityLabel.setMaximumSize(new java.awt.Dimension(65635, 14));
        velocityLabel.setMinimumSize(new java.awt.Dimension(0, 14));
        velocityLabel.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel9.add(velocityLabel);

        resultingVelocityPanel.setOpaque(false);
        resultingVelocityPanel.setLayout(new java.awt.GridBagLayout());

        resultingVelocityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultingVelocityLabel.setText("00"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        resultingVelocityPanel.add(resultingVelocityLabel, gridBagConstraints);

        jPanel9.add(resultingVelocityPanel);

        requiredVelocityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        requiredVelocityLabel.setMaximumSize(new java.awt.Dimension(65635, 14));
        requiredVelocityLabel.setMinimumSize(new java.awt.Dimension(0, 14));
        requiredVelocityLabel.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel9.add(requiredVelocityLabel);

        velocityLeftPanel.add(jPanel9);

        jPanel2.add(velocityLeftPanel);

        velocityRightPanel.setLayout(new java.awt.GridBagLayout());

        burnUpChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burnUpChartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        velocityRightPanel.add(burnUpChart, gridBagConstraints);

        jPanel2.add(velocityRightPanel);

        add(jPanel2);

        workPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Work management"));
        workPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total Work:"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel5.add(jLabel2, gridBagConstraints);

        totalWorkLabel.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        totalWorkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalWorkLabel.setText("00"); // NOI18N
        totalWorkLabel.setMaximumSize(new java.awt.Dimension(80, 20));
        totalWorkLabel.setMinimumSize(new java.awt.Dimension(80, 20));
        totalWorkLabel.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel5.add(totalWorkLabel, gridBagConstraints);

        workPanel.add(jPanel5);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Available:"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel4.add(jLabel3, gridBagConstraints);

        excessWorkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        excessWorkLabel.setText("00"); // NOI18N
        excessWorkLabel.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 20);
        jPanel4.add(excessWorkLabel, gridBagConstraints);

        workPanel.add(jPanel4);

        add(workPanel);
    }// </editor-fold>//GEN-END:initComponents

private void updateSprintTitle(){
        sprint.setTitle(titleTextField.getText());
        this.sprintsMainPanel.updateSprint(this);
}

private void burnUpChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burnUpChartActionPerformed
        seeGraphButtonActionPerformed(evt);
}//GEN-LAST:event_burnUpChartActionPerformed

    private void sprintPropertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sprintPropertiesButtonActionPerformed
        Frame appFrame = (Frame)SwingUtilities.windowForComponent(this);
        SprintPropertiesDialog dialog = new SprintPropertiesDialog(appFrame, sprint, toolkit);
        dialog.pack();
        dialog.setResizable(false);
        WindowUtilities.centerFrame(dialog, appFrame);
        dialog.setVisible(true);
        dialog.releaseAllResources();
    }//GEN-LAST:event_sprintPropertiesButtonActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        sprintPanel.removeWholeItemSet();
    }//GEN-LAST:event_btRemoveActionPerformed

    private void titleTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleTextFieldFocusLost
        updateSprintTitle();
    }//GEN-LAST:event_titleTextFieldFocusLost

    private void titleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextFieldActionPerformed
        updateSprintTitle();
    }//GEN-LAST:event_titleTextFieldActionPerformed

    private void notesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesButtonActionPerformed
        Frame appFrame = (Frame)SwingUtilities.windowForComponent(this);
        JDialog dialog = ReportsDialog.getInstance(appFrame, this.sprint);
        WindowUtilities.centerFrame(dialog, appFrame);
        dialog.setModal(true);
        dialog.setVisible(true);
        updateNotesButton();
    }//GEN-LAST:event_notesButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRemove;
    private org.scrinch.gui.BurnChartButton burnUpChart;
    private javax.swing.ButtonGroup buttonGroup1;
    private org.scrinch.gui.StatusLabel excessWorkLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbVelocityTitle;
    private javax.swing.JButton notesButton;
    private javax.swing.JLabel periodLabel;
    private javax.swing.JLabel requiredVelocityLabel;
    private javax.swing.JLabel requiredVelocityTitle;
    private org.scrinch.gui.StatusLabel resultingVelocityLabel;
    private javax.swing.JPanel resultingVelocityPanel;
    private javax.swing.JLabel resultingVelocityTitle;
    private javax.swing.JButton sprintPropertiesButton;
    private javax.swing.JPanel sprintPropertiesMainPanel;
    private javax.swing.JPanel sprintPropertiesSecondPanel;
    private javax.swing.JPanel sprintPropertiesThirdPanel;
    private javax.swing.JPanel sprintPropertiesTopPanel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JLabel totalWorkLabel;
    private javax.swing.JLabel velocityLabel;
    private javax.swing.JPanel velocityLeftPanel;
    private javax.swing.JPanel velocityRightPanel;
    protected javax.swing.JPanel workPanel;
    // End of variables declaration//GEN-END:variables

}
