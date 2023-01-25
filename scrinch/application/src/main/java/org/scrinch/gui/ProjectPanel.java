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

import java.awt.Container;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.scrinch.gui.reporting.WorkloadGraphPanel;
import org.scrinch.model.Project;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ProjectItemSetFactory;
import org.scrinch.model.ScrinchEnvToolkit;

public class ProjectPanel extends JPanel implements HeadedPane, Project.Listener {

    private TabbedPaneHeaderButtonUI centerTabbedPaneHeaderButtonUI = new TabbedPaneHeaderButtonUI();
    private Project project;
    private boolean loaded = false;
    private List<ItemTablePanel> itemSetPanelList = new ArrayList<ItemTablePanel>();
    private static boolean headerVisible = true;
    private final ProjectsMainPanel mainPanel;
    private final ScrinchEnvToolkit toolkit;

    public ProjectPanel(ProjectsMainPanel mainPanel, ScrinchEnvToolkit toolkit) {
        this.mainPanel = mainPanel;
        this.toolkit = toolkit;
        initComponents();
        refreshHeaderVisible();
    }

    public void releaseAllResources() {
        if (project != null) {
            project.removeListener(this);
        }
        this.unload();
    }

    public void toggleHeaderVisible() {
        headerVisible = !headerVisible;
        refreshHeaderVisible();
    }

    private void refreshHeaderVisible() {
        this.topPanel.setVisible(headerVisible);
    }

    public boolean isHeaderVisible() {
        return headerVisible;
    }

    public void setProject(Project project) {
        this.project = project;
        project.addListener(this);
        projectNameTextField.setText(project.getProjectName());
        descriptionTextArea.setText(project.getDescription());
        resetDatesAndMaintenance();
    }

    private void resetDatesAndMaintenance() {
        if (project.isMaintenanceProject()) {
            fromLabel.setVisible(false);
            toLabel.setVisible(false);
            fromTextField.setVisible(false);
            toTextField.setVisible(false);
            maintenanceCheckBox.setSelected(true);
            refreshButton.setVisible(!toolkit.isOldProjectsAndSprintsVisible());
        } else {
            fromLabel.setVisible(true);
            toLabel.setVisible(true);
            fromTextField.setVisible(true);
            toTextField.setVisible(true);
            fromTextField.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(project.getStartDate()));
            toTextField.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(project.getEndDate()));
            maintenanceCheckBox.setSelected(false);
            refreshButton.setVisible(false);
        }
    }

    public void datesChanged(Project project) {
        resetDatesAndMaintenance();
    }

    public Project getProject() {
        return project;
    }

    private void projectNameUpdated() {
        project.setProjectName(projectNameTextField.getText());
        this.mainPanel.updateProject(this);
    }

    private void remove() {
        if (this.project.getProjectItemSetList().size() > 0) {
            JOptionPane.showMessageDialog(this,
                    "You can't delete the " + this.project.getProjectName() + " project\n"
                    + "It still contains some item sets.",
                    "Project removing forbidden",
                    JOptionPane.OK_OPTION);
        } else {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want\n"
                    + "to remove the " + this.project.getProjectName() + " project?",
                    " removing requested", JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
                this.project.removeListener(this);
                toolkit.getProjectFactory().dispose(this.project);
                Container parent = this.getParent();
                parent.remove(this);
                parent.repaint();
            }
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

        buttonPanel = new javax.swing.JPanel();
        showHideHeader = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        fromLabel = new javax.swing.JLabel();
        fromTextField = new org.scrinch.gui.DatePanel();
        toLabel = new javax.swing.JLabel();
        toTextField = new org.scrinch.gui.DatePanel();
        maintenanceCheckBox = new javax.swing.JCheckBox();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        projectNameTextField = new javax.swing.JTextField();
        btRemove = new javax.swing.JButton();
        btWorkloadDistribution = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        addProjectItemSetButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        centerPanel = new javax.swing.JTabbedPane();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        showHideHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/12-eye.png"))); // NOI18N
        showHideHeader.setToolTipText("show/hide project actions");
        showHideHeader.setPreferredSize(new java.awt.Dimension(49, 24));
        showHideHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHideHeaderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        buttonPanel.add(showHideHeader, gridBagConstraints);

        jPanel5.setPreferredSize(new java.awt.Dimension(222, 20));
        jPanel5.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel8, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        buttonPanel.add(jPanel5, gridBagConstraints);

        add(buttonPanel, java.awt.BorderLayout.NORTH);

        contentPanel.setLayout(new java.awt.BorderLayout());

        topPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(901, 160));

        fromLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fromLabel.setText("From:");
        fromLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        fromTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromTextFieldActionPerformed(evt);
            }
        });

        toLabel.setText("To:");

        toTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toTextFieldActionPerformed(evt);
            }
        });

        maintenanceCheckBox.setText("Maintenance:");
        maintenanceCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        maintenanceCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        maintenanceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenanceCheckBoxActionPerformed(evt);
            }
        });

        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        descriptionLabel.setText("Description:");
        descriptionLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTextAreaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(descriptionTextArea);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name:");

        projectNameTextField.setMinimumSize(new java.awt.Dimension(100, 20));
        projectNameTextField.setPreferredSize(new java.awt.Dimension(100, 20));
        projectNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectNameTextFieldActionPerformed(evt);
            }
        });
        projectNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                projectNameTextFieldFocusLost(evt);
            }
        });

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        btRemove.setToolTipText("Delete this project");
        btRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRemove.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        btWorkloadDistribution.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/17-bar-chart-16.png"))); // NOI18N
        btWorkloadDistribution.setToolTipText("Project Workload Distribution");
        btWorkloadDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWorkloadDistributionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fromLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(fromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toLabel)
                                .addGap(5, 5, 5)
                                .addComponent(toTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btRemove)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btWorkloadDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maintenanceCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fromTextField, toTextField});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btRemove, btWorkloadDistribution});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maintenanceCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(projectNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btWorkloadDistribution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fromLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fromTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(toTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(toLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btRemove, fromTextField, projectNameTextField, toTextField});

        topPanel.add(jPanel7);

        addProjectItemSetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/work.png"))); // NOI18N
        addProjectItemSetButton.setText("Add Work");
        addProjectItemSetButton.setToolTipText("Click to add a new work category");
        addProjectItemSetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProjectItemSetButtonActionPerformed(evt);
            }
        });
        jPanel3.add(addProjectItemSetButton);

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/01-refresh.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.setToolTipText("If any Item Set has been marked as \"archive\", and if you are running in \"topical\" mode, archive Item Set will disappear");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel3.add(refreshButton);

        topPanel.add(jPanel3);

        contentPanel.add(topPanel, java.awt.BorderLayout.NORTH);
        contentPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        add(contentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btWorkloadDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWorkloadDistributionActionPerformed
        try {
            JPanel panel = new WorkloadGraphPanel(project, toolkit);
            Window window = SwingUtilities.windowForComponent(this);
            WindowUtilities.showCloseOnlyModalDialog(window, panel, "Workload for project "+project.getProjectName());
        } catch (Error e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "btWorkloadDistributionActionPerformed", e);
            ScrinchEnvToolkit.exit(-1);
        }
    }//GEN-LAST:event_btWorkloadDistributionActionPerformed

    private void maintenanceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenanceCheckBoxActionPerformed
        project.setMaintenanceProject(!project.isMaintenanceProject());
        resetDatesAndMaintenance();
    }//GEN-LAST:event_maintenanceCheckBoxActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        try {
            remove();
        } catch (Error e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "btRemoveActionPerformed", e);
            ScrinchEnvToolkit.exit(-1);
        }
    }//GEN-LAST:event_btRemoveActionPerformed

    private void toTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toTextFieldActionPerformed
        try {
            project.setEndDate(ScrinchGuiToolkit.getDefaultDayFormat().parse(toTextField.getText()));
        } catch (Exception e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "toTextFieldActionPerformed", e);
        } catch (Error e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "toTextFieldActionPerformed", e);
            ScrinchEnvToolkit.exit(-1);
        }
    }//GEN-LAST:event_toTextFieldActionPerformed

    private void fromTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromTextFieldActionPerformed
        try {
            project.setStartDate(ScrinchGuiToolkit.getDefaultDayFormat().parse(fromTextField.getText()));
        } catch (Exception e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "fromTextFieldActionPerformed", e);
        } catch (Error e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "fromTextFieldActionPerformed", e);
            ScrinchEnvToolkit.exit(-1);
        }
    }//GEN-LAST:event_fromTextFieldActionPerformed

    private void descriptionTextAreaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextAreaFocusLost
        project.setDescription(descriptionTextArea.getText());
    }//GEN-LAST:event_descriptionTextAreaFocusLost

    private void projectNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_projectNameTextFieldFocusLost
        projectNameUpdated();
}//GEN-LAST:event_projectNameTextFieldFocusLost

    private void projectNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectNameTextFieldActionPerformed
        projectNameUpdated();
}//GEN-LAST:event_projectNameTextFieldActionPerformed

private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
    try {
        load();
    } catch (Error e) {
        ScrinchEnvToolkit.logger.log(Level.SEVERE, "formComponentShown", e);
        ScrinchEnvToolkit.exit(-1);
    }
}//GEN-LAST:event_formComponentShown

private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
    try {
        unload();
    } catch (Error e) {
        ScrinchEnvToolkit.logger.log(Level.SEVERE, "formComponentHidden", e);
        ScrinchEnvToolkit.exit(-1);
    }
}//GEN-LAST:event_formComponentHidden

private void addProjectItemSetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProjectItemSetButtonActionPerformed
    try {
        ProjectItemSet ProjectItemSet = ProjectItemSetFactory.getInstance().createProjectItemSet();
        String title = "ProjectItemSet #" + centerPanel.getTabCount();
        ProjectItemSet.setTitle(title);
        project.addItemSet(ProjectItemSet);
        addItemSetPanel(ProjectItemSet, true);
    } catch (Error e) {
        ScrinchEnvToolkit.logger.log(Level.SEVERE, "addProjectItemSetButtonActionPerformed", e);
        ScrinchEnvToolkit.exit(-1);
    }
}//GEN-LAST:event_addProjectItemSetButtonActionPerformed

private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
    reload();
}//GEN-LAST:event_refreshButtonActionPerformed

private void showHideHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showHideHeaderActionPerformed
    toggleHeaderVisible();
}//GEN-LAST:event_showHideHeaderActionPerformed

    private void addItemSetPanel(final ProjectItemSet projectItemSet, final boolean placeTabOnLast) {
        if (!projectItemSet.isArchive() || toolkit.isOldProjectsAndSprintsVisible()) {
            ItemTablePanel itemSetPanel = new ProjectItemSetPanel(toolkit);
            itemSetPanelList.add(itemSetPanel);
            itemSetPanel.setItemSet(projectItemSet);
            centerPanel.add(itemSetPanel, projectItemSet.getTitle()+"  ");
            itemSetPanel.setTabbedPaneAndPosition(centerPanel, centerPanel.getTabCount() - 1);
            centerPanel.setUI(centerTabbedPaneHeaderButtonUI);
            if (placeTabOnLast) {
                centerPanel.setSelectedIndex(centerPanel.getTabCount() - 1);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProjectItemSetButton;
    private javax.swing.JButton btRemove;
    private javax.swing.JButton btWorkloadDistribution;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTabbedPane centerPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel fromLabel;
    private org.scrinch.gui.DatePanel fromTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox maintenanceCheckBox;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton showHideHeader;
    private javax.swing.JLabel toLabel;
    private org.scrinch.gui.DatePanel toTextField;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    private final Object loadingLock = new Object();

    public void reload() {
        unload();
        load();
    }

    public void load() {
        refreshHeaderVisible();
        synchronized (loadingLock) {
            if (!loaded) {
                this.loaded = true;
                List<ProjectItemSet> itemSets = this.project.getProjectItemSetList();
                for (ProjectItemSet itemSet : itemSets) {
                    addItemSetPanel(itemSet, false);
                }
            }
        }
    }

    public void unload() {
        synchronized (loadingLock) {
            if (loaded) {
                this.loaded = false;
                centerPanel.removeAll();
                for (ItemTablePanel panel : itemSetPanelList) {
                    panel.releaseAllResources();
                }
                itemSetPanelList.clear();
            }
        }
    }
}
