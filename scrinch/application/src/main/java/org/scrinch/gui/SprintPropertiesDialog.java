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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import org.scrinch.model.Member;
import org.scrinch.model.SlowDown;
import org.scrinch.model.Sprint;
import org.scrinch.model.MemberFactory;
import org.scrinch.model.ScrinchEnvToolkit;

public class SprintPropertiesDialog extends javax.swing.JDialog implements Sprint.SprintListener {

    private Sprint sprint;
    private final ScrinchEnvToolkit toolkit;

    public SprintPropertiesDialog(java.awt.Frame parent, Sprint sprint, ScrinchEnvToolkit toolkit) {
        super(parent, true);
        this.sprint = sprint;
        this.toolkit = toolkit;
        this.sprint.addSprintListener(this);
        initComponents();
        initDateListeners();
        ScrinchGuiToolkit.centerFrame(this);
        resetAllValues();
    }

    public void releaseAllResources() {
        if (sprint != null) {
            sprint.removeSprintListener(this);
        }
    }

    private void initDateListeners() {
        startingDateTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStartingDate();
            }
        });

        endDateTextField.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEndDate();
            }
        });
    }

    protected void resetAllValues() {
        resetSprintProperties();
        resetSlowDownListPanel();
    }

    private void resetSprintProperties() {
        sprintNameLabel.setText(sprint.getTitle());
        baseVelocityTextField.setText(ScrinchGuiToolkit.getDisplayableDouble(sprint.getBaseVelocity()));
        velocityChanged(sprint);
        sprintMembers.setText(MemberFactory.getMembersAsHTMLvList(sprint.getMembers()));
        startingDateTextField.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getStartDate()));
        endDateTextField.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getEndDate()));
        sprintMembers.setPreferredSize(new Dimension(120, 18*sprint.getMembers().size()));
        pack();
    }

    private void velocityChanged(Sprint sprint) {
        realVelocityLabel.setText(ScrinchGuiToolkit.getDisplayableDouble(sprint.getPossibleVelocity()));
    }
    
    public void realVelocityChanged(Sprint sprint) {
        velocityChanged(sprint);
        resetSlowDownListPanel();
        pack();
    }

    public void datesChanged(Sprint sprint) {
        resetSprintProperties();
    }

    public void workChanged(Sprint sprint) {
    }

    public void itemsChanged(Sprint sprint) {
    }

    private void resetSlowDownListPanel() {
        slowDownListPanel.removeAll();
        int index = 0;
        for (SlowDown slowDown:sprint.getSlowDownList()) {
            addSlowDownPanel(slowDown, index);
            index++;
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = index;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        slowDownListPanel.add(new JPanel(), constraints);
        slowDownListPanel.updateUI();
    }

    private void updateBaseVelocity() {
        try {
            sprint.setBaseVelocity(Double.parseDouble(baseVelocityTextField.getText()));
        } catch (NumberFormatException nfe) {
        }
    }

    private void addSlowDownPanel(SlowDown slowDown, int index) {
        SlowDownPanel slowDownPanel = new SlowDownPanel();
        slowDownPanel.setSlowDown(slowDown);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = index;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        slowDownListPanel.add(slowDownPanel, constraints);
        slowDownListPanel.validate();
    }

    private void updateStartingDate() {
        try {
            sprint.setStartDate(ScrinchGuiToolkit.getDefaultDayFormat().parse(startingDateTextField.getText()));
        } catch (Exception e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "Starting date value is wrong", e);
        }
    }

    private void updateEndDate() {
        try {
            sprint.setEndDate(ScrinchGuiToolkit.getDefaultDayFormat().parse(endDateTextField.getText()));
        } catch (Exception e) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        slowDownCreationPanel = new javax.swing.JPanel();
        addSlowDownButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        slowDownListPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        sprintNameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        baseVelocityTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        realVelocityLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        startingDateTextField = new org.scrinch.gui.DatePanel();
        endDateTextField = new org.scrinch.gui.DatePanel();
        sprintMembers = new javax.swing.JLabel();
        customize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel3.add(closeButton, gridBagConstraints);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Slow down management"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        slowDownCreationPanel.setLayout(new java.awt.GridBagLayout());

        addSlowDownButton.setText("Add");
        addSlowDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSlowDownButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        slowDownCreationPanel.add(addSlowDownButton, gridBagConstraints);

        jPanel2.add(slowDownCreationPanel, java.awt.BorderLayout.NORTH);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        slowDownListPanel.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(slowDownListPanel);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Sprint global management"));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Sprint Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel2, gridBagConstraints);

        sprintNameLabel.setMaximumSize(new java.awt.Dimension(65635, 20));
        sprintNameLabel.setMinimumSize(new java.awt.Dimension(100, 20));
        sprintNameLabel.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(sprintNameLabel, gridBagConstraints);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Base velocity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel1, gridBagConstraints);

        baseVelocityTextField.setMaximumSize(new java.awt.Dimension(65635, 20));
        baseVelocityTextField.setMinimumSize(new java.awt.Dimension(50, 20));
        baseVelocityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseVelocityTextFieldActionPerformed(evt);
            }
        });
        baseVelocityTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                baseVelocityTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(baseVelocityTextField, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Possible velocity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel3, gridBagConstraints);

        realVelocityLabel.setMaximumSize(new java.awt.Dimension(65635, 20));
        realVelocityLabel.setMinimumSize(new java.awt.Dimension(50, 20));
        realVelocityLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(realVelocityLabel, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Start date :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel9, gridBagConstraints);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("End date :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel10, gridBagConstraints);

        jLabel7.setText("Team members :");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(startingDateTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(endDateTextField, gridBagConstraints);

        sprintMembers.setText("members");
        sprintMembers.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sprintMembers.setAutoscrolls(true);
        sprintMembers.setPreferredSize(new java.awt.Dimension(120, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(sprintMembers, gridBagConstraints);

        customize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/112-group.png"))); // NOI18N
        customize.setToolTipText("Click to edit team members list");
        customize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(customize, gridBagConstraints);

        getContentPane().add(jPanel6, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void baseVelocityTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_baseVelocityTextFieldFocusLost
        updateBaseVelocity();
    }//GEN-LAST:event_baseVelocityTextFieldFocusLost

    private void baseVelocityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseVelocityTextFieldActionPerformed
        updateBaseVelocity();
    }//GEN-LAST:event_baseVelocityTextFieldActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void addSlowDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSlowDownButtonActionPerformed
        Collection<Member> selectedMembersHolder = new TreeSet<Member>();

        JPanel membersSelectPanel = WindowUtilities.createMembersSelectionPanel(sprint.getMembers(), selectedMembersHolder, null);

        JPanel slowDownParams = new JPanel(new GridBagLayout());
        Insets insets = new Insets(5, 2, 5, 2);
        JLabel reasonLabel = new JLabel("Reason:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets;
        slowDownParams.add(reasonLabel, constraints);
        JTextField reasonTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets;
        constraints.weightx = 1;
        reasonTextField.setPreferredSize(new Dimension(240, 20));
        slowDownParams.add(reasonTextField, constraints);
        JLabel daysLabel = new JLabel("Days:");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets;
        slowDownParams.add(daysLabel, constraints);
        JSpinner daysSpinner = new JSpinner(new SpinnerNumberModel(1.0d, 0.25d, sprint.getBusinessDaysCount(), 0.25d));
        daysSpinner.setPreferredSize(new Dimension(45, 20));
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets;
        slowDownParams.add(daysSpinner, constraints);

        JPanel content = new JPanel(new BorderLayout(0, 0));
        content.add(membersSelectPanel, BorderLayout.NORTH);
        content.add(slowDownParams, BorderLayout.SOUTH);

        Window window = SwingUtilities.windowForComponent(this);
        boolean ok = WindowUtilities.showOKCancelModalDialog(window, content, "Add slow down on members");
        if (ok) {
            double dayCount = ((Number)daysSpinner.getValue()).doubleValue();
            if (dayCount>0 && !selectedMembersHolder.isEmpty()) {
                SlowDown slowDown = new SlowDown();
                slowDown.setDayCount(dayCount);
                slowDown.setDescription(reasonTextField.getText());
                slowDown.setMembers(selectedMembersHolder);
                slowDown.setSprintAndAddToSprint(this.sprint);
                resetAllValues();
            }
        }
    }//GEN-LAST:event_addSlowDownButtonActionPerformed

    private void customizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizeActionPerformed
        Collection<Member> members = toolkit.getMemberFactory().getMemberList();
        Collection<Member> selectedMembersHolder = new TreeSet<Member>();
        Collection<Member> currentlySelectedMembers = sprint.getMembers();
        JPanel membersSelectPanel = WindowUtilities.createMembersSelectionPanel(members, selectedMembersHolder, currentlySelectedMembers);
        Window window = SwingUtilities.windowForComponent(this);
        boolean ok = WindowUtilities.showOKCancelModalDialog(window, membersSelectPanel, "Available members");
        if (ok) {
            sprint.replaceExistingMemberList(selectedMembersHolder);
            resetAllValues();
        }
    }//GEN-LAST:event_customizeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSlowDownButton;
    private javax.swing.JTextField baseVelocityTextField;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton customize;
    private org.scrinch.gui.DatePanel endDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel realVelocityLabel;
    private javax.swing.JPanel slowDownCreationPanel;
    private javax.swing.JPanel slowDownListPanel;
    private javax.swing.JLabel sprintMembers;
    private javax.swing.JLabel sprintNameLabel;
    private org.scrinch.gui.DatePanel startingDateTextField;
    // End of variables declaration//GEN-END:variables
}
