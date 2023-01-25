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

import org.scrinch.gui.model.DateListModel;
import org.scrinch.gui.model.MembersComboBoxModel;
import org.scrinch.gui.model.StatusComboBoxModel;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.Date;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.scrinch.model.Member;
import org.scrinch.model.Item;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.ScrinchException;
import org.scrinch.model.Sprint;

public class StatusDialog extends javax.swing.JDialog {

    private Item item;
    private StatusComboBoxModel statusComboBoxModel;
    private Sprint sprint;

    private static final int DIALOG_HEIGHT = 240;
    private static final int DIALOG_WIDTH  = 560;

    private final ScrinchEnvToolkit toolkit;
    
    public StatusDialog(java.awt.Frame parent, Item item, Sprint sprint, ScrinchEnvToolkit toolkit) {
        super(parent, true);
        this.sprint = sprint;
        this.toolkit = toolkit;
        this.setTitle("Status chooser");
        statusComboBoxModel = new StatusComboBoxModel(item);
        initComponents();
        statusComboBox.setModel(statusComboBoxModel);
        memberComboBox.setModel(new MembersComboBoxModel(toolkit));
        Dimension dim = new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT);
        setSize(dim);
        setMinimumSize(dim);
        this.item = item;
        this.statusComboBox.setRenderer(new StatusCellRenderer(this.item.getVisasHistory(), sprint));
        resetStatusComboBox();
        resetList();
        if(sprint!=null){
            ((CardLayout)datePanel.getLayout()).show(datePanel,"sprintDate");
            sprintDateList.setModel(new DateListModel(sprint.getWorkingDates(), true, toolkit));
            final int currentIndex = sprintDateList.getModel().getSize()-1;
            sprintDateList.setSelectedIndex(currentIndex);
            sprintDateList.ensureIndexIsVisible(currentIndex);
        } else {
            ((CardLayout)datePanel.getLayout()).show(datePanel,"freeDate");
            freeDateTextField.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(toolkit.getCurrentDate()));
        }
    }

    private void resetList(){
        DefaultListModel model = (DefaultListModel)this.visaList.getModel();
        model.clear();
        Stack<Item.Visa> visas = this.item.getVisasHistory();
        for(Item.Visa visa:visas){
            model.addElement(visa);
        }
        this.delButton.setEnabled(this.item.getStatus()!=Item.Status.STANDBY);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visaList = new javax.swing.JList();
        memberComboBox = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        datePanel = new javax.swing.JPanel();
        freeDatePanel = new javax.swing.JPanel();
        freeDateTextField = new org.scrinch.gui.DatePanel();
        sprintDatePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sprintDateList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        commentLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        mainPanel.setEnabled(false);
        mainPanel.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Status History");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Signature");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jLabel2, gridBagConstraints);

        statusComboBox.setModel(statusComboBoxModel);
        statusComboBox.setMinimumSize(new java.awt.Dimension(100, 20));
        statusComboBox.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(statusComboBox, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jLabel9, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(25, 132));

        visaList.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(visaList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        mainPanel.add(jScrollPane1, gridBagConstraints);

        memberComboBox.setMinimumSize(new java.awt.Dimension(100, 20));
        memberComboBox.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(memberComboBox, gridBagConstraints);

        addButton.setText("<< Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        mainPanel.add(addButton, gridBagConstraints);

        delButton.setText("Del. last");
        delButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        mainPanel.add(delButton, gridBagConstraints);

        datePanel.setMinimumSize(new java.awt.Dimension(10, 50));
        datePanel.setPreferredSize(new java.awt.Dimension(10, 50));
        datePanel.setLayout(new java.awt.CardLayout());

        freeDatePanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        freeDatePanel.add(freeDateTextField, gridBagConstraints);

        datePanel.add(freeDatePanel, "freeDate");

        sprintDatePanel.setLayout(new java.awt.BorderLayout());

        sprintDateList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(sprintDateList);

        sprintDatePanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        datePanel.add(sprintDatePanel, "sprintDate");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(datePanel, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jLabel3, gridBagConstraints);

        commentTextArea.setColumns(20);
        commentTextArea.setRows(5);
        jScrollPane3.setViewportView(commentTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(jScrollPane3, gridBagConstraints);

        commentLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        commentLabel.setText("Comment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        mainPanel.add(commentLabel, gridBagConstraints);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        closeButton.setText("Close window");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        jPanel2.add(closeButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delButtonActionPerformed
        this.item.removeLastVisa();
        resetStatusComboBox();
        resetList();
    }//GEN-LAST:event_delButtonActionPerformed

    private void resetStatusComboBox(){
        this.statusComboBox.setSelectedItem(item.getNextValidStatus());
        this.statusComboBox.repaint();
    }

    private Date extractStatusDate() throws ScrinchException{
        Date checkedDate;
        try {
            if(sprint==null){
                checkedDate = ScrinchGuiToolkit.getDefaultDayFormat().parse(freeDateTextField.getText());
            } else {
                if(sprintDateList.getSelectedValue()!=null){
                    checkedDate = ScrinchGuiToolkit.getDefaultDayFormat().parse(""+sprintDateList.getSelectedValue());
                } else if (sprintDateList.getModel().getSize()<=0 
                        &&(statusComboBoxModel.getSelectedItem().equals(Item.Status.CANCELLED)
                           || statusComboBoxModel.getSelectedItem().equals(Item.Status.EVALUATED)
                           || statusComboBoxModel.getSelectedItem().equals(Item.Status.POSTPONED))){
                    checkedDate = toolkit.getCurrentDate();
                } else {
                    throw new ScrinchException("Please select a date first");
                }
            }
        } catch(ParseException pe){
            throw new ScrinchException("Date is not valid, should be DD/MM/YYYY");
        }
        return checkedDate;
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try{
            if( this.memberComboBox.getSelectedItem()==null ){
                throw new ScrinchException("No member specified... (Does a member exist?)");
            }
            Date date = extractStatusDate();

            this.item.addVisa(new Item.Visa((Item.Status)this.statusComboBox.getSelectedItem(),
                              (Member) this.memberComboBox.getSelectedItem(),
                              date,
                              commentTextArea.getText()));
            resetStatusComboBox();
        } catch(ScrinchException se){
            handleThrowable(se);
        }finally{
            resetList();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void handleThrowable(Throwable t){
        JOptionPane.showMessageDialog(this, t.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JPanel datePanel;
    private javax.swing.JButton delButton;
    private javax.swing.JPanel freeDatePanel;
    private org.scrinch.gui.DatePanel freeDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox memberComboBox;
    private javax.swing.JList sprintDateList;
    private javax.swing.JPanel sprintDatePanel;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JList visaList;
    // End of variables declaration//GEN-END:variables

}
