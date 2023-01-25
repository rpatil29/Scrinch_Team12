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
import org.scrinch.gui.model.ItemSetTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import org.scrinch.gui.model.FiboPointComboBoxModel;
import org.scrinch.gui.model.OriginTypeComboBoxModel;
import org.scrinch.gui.model.TargetComboBoxModel;
import org.scrinch.gui.model.WorkTypeComboBoxModel;
import org.scrinch.gui.tableeditors.ScrinchComboBoxEditor;
import org.scrinch.gui.tableeditors.ItemStatusEditor;
import org.scrinch.model.FiboPoint;
import org.scrinch.model.Item;
import org.scrinch.model.ItemSet;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.OriginType;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.ScrinchException;
import org.scrinch.model.Target;
import org.scrinch.model.WorkType;

public class ItemTablePanel extends JPanel implements HeadedPane, ItemSet.Listener, Item.StatusOrEvaluationChangeListener {

    private ItemSet itemSet;
    protected ItemSetTableModel tableModel;
    private JTabbedPane tabbedPane;
    private int position;
    protected ItemSetPdfExporter pdfExporter;
    protected final ScrinchEnvToolkit toolkit;
    private ItemStatusEditor itemStatusRenderer;
    private ItemStatusEditor itemStatusEditor;

    public void changeOccured(Item item) {
        refreshTable();
    }

    private class KeyboardManager implements KeyEventDispatcher {

        public boolean dispatchKeyEvent(KeyEvent event) {
            if(event.getID() == KeyEvent.KEY_PRESSED
                    && event.getKeyCode() == KeyEvent.VK_N
                    && event.isControlDown()) {
                if(ItemTablePanel.this.isShowing()){
                    createNewItem();
                    return true;
                }
            }
            return false;
        }
    }
    
    public ItemTablePanel(ScrinchEnvToolkit toolkit) {
        this(false, toolkit);
    }

    public ItemTablePanel(boolean projectNameVisible, ScrinchEnvToolkit toolkit) {
        initComponents();
        this.toolkit = toolkit;
        scrollPane.getViewport().setBackground(new Color(250, 250, 250));
        this.scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        this.scrollPane.getVerticalScrollBar().setBlockIncrement(100);
        tableModel = new ItemSetTableModel();
        itemTable.setModel(tableModel);
        pdfExporter = new ItemSetPdfExporter(this);
        if( !toolkit.useItemSetScrollPane() ){
            this.scrollPane.remove(this.itemTable);
            remove(this.scrollPane);
            add(this.itemTable, BorderLayout.CENTER);
        }
        KeyboardManager manager = new KeyboardManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(manager);
        scrollPane.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent ce) {
                resetColumnSizes();
            }
        });
        resetJTableRenderersAndEditors();
    }
    
    private void resetJTableRenderersAndEditors(){
        itemTable.setDefaultEditor(FiboPoint.class, new ScrinchComboBoxEditor(new JComboBox(new FiboPointComboBoxModel())));
        itemTable.setDefaultEditor(OriginType.class, new ScrinchComboBoxEditor(new JComboBox(new OriginTypeComboBoxModel(toolkit))));
        itemTable.setDefaultEditor(WorkType.class, new ScrinchComboBoxEditor(new JComboBox(new WorkTypeComboBoxModel(toolkit))));
        itemTable.setDefaultEditor(Target.class, new ScrinchComboBoxEditor(new JComboBox(new TargetComboBoxModel(true, true, toolkit))));
        
        itemStatusEditor = new ItemStatusEditor(toolkit, this);
        itemTable.setDefaultEditor(Item.class, itemStatusEditor);
        
        itemStatusRenderer = new ItemStatusEditor(toolkit, this);
        itemTable.setDefaultRenderer(Item.class, itemStatusRenderer);
        itemTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                if (c instanceof JLabel && value != null) {
                    int availableWidth = table.getColumnModel().getColumn(column).getWidth();
                    availableWidth -= table.getIntercellSpacing().getWidth();
                    Insets borderInsets = getBorder().getBorderInsets(c);
                    availableWidth -= (borderInsets.left + borderInsets.right);
                    FontMetrics fm = getFontMetrics( getFont() );
                    String cellText = value.toString();

                    if (fm.stringWidth(cellText) > availableWidth){
                        ((javax.swing.JLabel) c).setToolTipText(value.toString());
                    }else{
                        ((javax.swing.JLabel) c).setToolTipText(null);
                    }
                }
                
                return c;
            }
        });
        
        ((DefaultCellEditor)itemTable.getDefaultEditor(String.class)).setClickCountToStart(1);
    }
    
    private void resetColumnSizes(){
        int tableWidth = scrollPane.getWidth();
        itemTable.setSize(tableWidth,itemTable.getHeight());

        int column0Size = 20;
        int column1Size = 40;
        int column4Size = 100;
        int column5Size = 100;
        int column6Size = 50;
        int column7Size = 50;
        int column8Size = 120;
        int column9Size = 80;
        int dynamicSize = tableWidth - column0Size - column1Size - column4Size - column5Size - column6Size - column7Size - column8Size - column9Size;
        
        itemTable.getColumnModel().getColumn(0).setPreferredWidth(column0Size);
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(column1Size);
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(dynamicSize*15/100);
        itemTable.getColumnModel().getColumn(3).setPreferredWidth(dynamicSize*60/100);
        itemTable.getColumnModel().getColumn(4).setPreferredWidth(column4Size);
        itemTable.getColumnModel().getColumn(5).setPreferredWidth(column5Size);
        itemTable.getColumnModel().getColumn(6).setPreferredWidth(column6Size);
        itemTable.getColumnModel().getColumn(7).setPreferredWidth(column7Size);
        itemTable.getColumnModel().getColumn(8).setPreferredWidth(column8Size);
        itemTable.getColumnModel().getColumn(9).setPreferredWidth(column9Size);
        itemTable.getColumnModel().getColumn(10).setPreferredWidth(dynamicSize*15/100);
        itemTable.getColumnModel().getColumn(11).setPreferredWidth(dynamicSize*10/100);
    }

    private class MyJTable extends JTable{
        
        public MyJTable() {
            addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {  
                        int row = itemTable.rowAtPoint(e.getPoint());
                        int column = itemTable.columnAtPoint(e.getPoint());
                        editCellAt(row,column);
                        if(column == 9){
                            ItemStatusEditor statusEditor = (ItemStatusEditor)getCellEditor(row, column);
                            statusEditor.resetCache(getValueAt(row, column));
                            statusEditor.dispatchEvent(new MouseEvent(statusEditor, e.getID(), e.getWhen(), e.getModifiers(), e.getX(), e.getY(), e.getClickCount(), e.isPopupTrigger()));
                        }
                    }
                    else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {  
                        int row = itemTable.rowAtPoint(e.getPoint());
                        int column = itemTable.columnAtPoint(e.getPoint());
                        if(column == 6 || column == 7){
                            changeSelection(row, column, true, true);
                        }
                    }
                }
            });  
        }
        
        @Override    
        public void changeSelection(final int row, final int column, boolean toggle, boolean extend) {
            super.changeSelection(row, column, toggle, extend);
            this.editCellAt(row, column);
            this.transferFocus();
        }
    }
    
    protected void releaseAllResources() {
        if (this.itemSet != null) {
            this.itemSet.removeListener(this);
        }
        itemTable.removeAll();
        itemStatusEditor.setItemTablePanel(null);
        itemStatusRenderer.setItemTablePanel(null);
    }

    public void setTabbedPaneAndPosition(JTabbedPane tabbedPane, int position) {
        this.tabbedPane = tabbedPane;
        this.position = position;
    }

    public void titleChanged(ItemSet itemSet) {
        resetTitle();
    }

    public void orderChanged(ItemSet itemSet) {
        setItemSet(itemSet);
    }

    public void refreshTable() {
        resetItemSet();
    }

    private void resetEvaluation() {
        totalWorkLabel.setText(Integer.toString(ItemToolkit.getActiveItemEvaluationCount(itemSet.getItemList())));
    }

    private void resetTitle() {
        titleTextField.setText(itemSet.getTitle());
        JTabbedPane tempTabbedPane = this.tabbedPane;
        if (tempTabbedPane != null) {
            if (position < tempTabbedPane.getTabCount()) {
                tempTabbedPane.setTitleAt(position, itemSet.getTitle());
            }
        }
        if (itemSet instanceof ProjectItemSet && ((ProjectItemSet) itemSet).getProject().isMaintenanceProject()) {
            archiveCheckBox.setVisible(true);
            archiveCheckBox.setSelected(((ProjectItemSet) itemSet).isArchive());
        } else {
            archiveCheckBox.setVisible(false);
        }
    }

    protected void removeTitlePanel() {
        itemSetMainPanel.remove(this.titlePanel);
    }

    public void setItems(Collection<Item> items, String itemsListName) {
        ItemSet localItemSet = new ItemSet();
        localItemSet.setTitle(itemsListName);
        localItemSet.addItems(items);
        setItemSet(localItemSet);
    }

    public ItemSet getItemSet() {
        return itemSet;
    }

    public void setItemSet(ItemSet itemSet) {
        if (this.itemSet != null) {
            this.itemSet.removeListener(this);
            for(Item item:itemSet.getItemList()){
                item.removeStatusOrEvaluationChangeListener(this);
            }
        }
        this.itemSet = itemSet;
        resetItemSet();
        if (this.itemSet != null) {
            this.itemSet.addListener(this);
            for(Item item:itemSet.getItemList()){
                item.addStatusOrEvaluationChangeListener(this);
            }
        }
    }

    private void resetItemSet() {
        if (itemSet != null) {
            resetTitle();
            resetEvaluation();
            tableModel.setItemSet(itemSet);
        }
        resetColumnSizes();
    }

    protected void removeWholeItemSet() {
    }
   
    private Item createNewItem(){
        final Item item = doCreateNewItem();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                itemTable.changeSelection(0,2,true,true);
            }
        });
        return item;
    }
    
    protected Item doCreateNewItem() {
        Item item = toolkit.getItemFactory().createItem();
        itemSet.addItem(item);
        resetItemSet();
        select(item);
        return item;
    }

    public void toggleHeaderVisible() {
        this.titlePanel.setVisible(!this.titlePanel.isVisible());
        this.buttonsPanel.setVisible(!this.buttonsPanel.isVisible());
    }

    public boolean isHeaderVisible() {
        return this.titlePanel.isVisible();
    }

    public List<Item> getSelectedItems() {
        ItemSetTableModel model = (ItemSetTableModel)itemTable.getModel();
        return model.getSelectedItems();        
    }

    public void selectItems(Collection<Item> items) {
        for(Item itemToSelect:items){
            select(itemToSelect);
        }
    }

    protected void removeItems(Collection<Item> items, boolean removeAndDispose) throws ScrinchException {
        for (Item item:items) {
            removeItem(item, removeAndDispose);
        }
    }

    protected void removeItem(Item item, boolean removeAndDispose) throws ScrinchException {
        this.itemSet.removeItem(item, !removeAndDispose);
        if ((this.itemSet instanceof ProjectItemSet) && removeAndDispose) { //we don't dispose item if deselected in sprint
            toolkit.getItemFactory().dispose(item);
        }
        resetItemSet();
    }

    protected void removeButtonPerformed() throws ScrinchException {
        Collection<Item> selectedItems = getSelectedItems();
        if (checkSomethingSelected()) {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want\n"
                    + "to remove the " + selectedItems.size() + " selected items?",
                    "Remove items...", JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
                this.removeItems(selectedItems, true);
            }
        }
    }

    private void handleThrowable(Throwable t) {
        JOptionPane.showMessageDialog(this, t.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void showSprintChooserDialog() {
        if (checkSomethingSelected()) {
            showDialog(new SprintChooserDialog(getFrameForThis(), this, toolkit));
        }
    }

    protected void showDialog(Dialog dialog) {
        try {
            dialog.pack();
            WindowUtilities.centerFrame(dialog, getFrameForThis());
            dialog.setVisible(true);
        } catch (Exception e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "Could not show dialog", e);
        }
    }

    protected Frame getFrameForThis() {
        return ((Frame) SwingUtilities.windowForComponent(this));
    }

    public void unselectAll() {
        for (int i = 0; i < itemTable.getModel().getRowCount(); i++) {
            itemTable.getModel().setValueAt(Boolean.FALSE, i, 0);
        }
    }

    public void select(Item item) {
        TableModel itemTableModel = itemTable.getModel();
        for (int i = 0; i < itemTable.getModel().getRowCount(); i++) {
            if (itemTableModel.getValueAt(i, 1).equals(item)) {
                itemTableModel.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }

    private void showItemSetChooserDialog() {
        if (checkSomethingSelected()) {
            showDialog(new ProjectItemMovingDialog(getFrameForThis(), this, toolkit));
        }
    }

    protected void exportItemSetToPdf() {
        try {
            pdfExporter.exportSetToPdfFile();
        } catch (Throwable t) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "Could not export Item Set to PDF", t);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        topPanel = new javax.swing.JPanel();
        itemSetMainPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        totalWorkLabel = new javax.swing.JLabel();
        btRemove = new javax.swing.JButton();
        archiveCheckBox = new javax.swing.JCheckBox();
        buttonsPanel = new javax.swing.JPanel();
        btsPanel = new javax.swing.JPanel();
        addItemButton = new javax.swing.JButton();
        affectToSprintButton = new javax.swing.JButton();
        moveItemButton = new javax.swing.JButton();
        splitItemButton = new javax.swing.JButton();
        empty = new javax.swing.JPanel();
        removeButton = new javax.swing.JButton();
        duplicateItemButton = new javax.swing.JButton();
        sortItemsButton = new javax.swing.JButton();
        expPanel = new javax.swing.JPanel();
        btExportSet = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        itemTable = new MyJTable();

        setLayout(new java.awt.BorderLayout());

        topPanel.setLayout(new javax.swing.BoxLayout(topPanel, javax.swing.BoxLayout.Y_AXIS));

        itemSetMainPanel.setLayout(new javax.swing.BoxLayout(itemSetMainPanel, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Work :"); // NOI18N

        titleTextField.setMinimumSize(new java.awt.Dimension(100, 20));
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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Total Estimated Work :"); // NOI18N

        totalWorkLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalWorkLabel.setText("asdas");
        totalWorkLabel.setMaximumSize(new java.awt.Dimension(100, 20));
        totalWorkLabel.setMinimumSize(new java.awt.Dimension(100, 20));
        totalWorkLabel.setPreferredSize(new java.awt.Dimension(100, 20));

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        btRemove.setToolTipText("delete this work category");
        btRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRemove.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        archiveCheckBox.setText("Old maintenance");
        archiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btRemove)
                .addGap(36, 36, 36)
                .addComponent(archiveCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalWorkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(totalWorkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(archiveCheckBox)
                    .addComponent(jLabel1)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btRemove))
                .addGap(11, 11, 11))
        );

        titlePanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btRemove, titleTextField});

        itemSetMainPanel.add(titlePanel);

        topPanel.add(itemSetMainPanel);

        buttonsPanel.setLayout(new java.awt.BorderLayout());

        btsPanel.setLayout(new java.awt.GridBagLayout());

        addItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/104-index-cards.png"))); // NOI18N
        addItemButton.setText("Add"); // NOI18N
        addItemButton.setToolTipText("Add a new task");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(addItemButton, gridBagConstraints);

        affectToSprintButton.setText("Affect to sprint"); // NOI18N
        affectToSprintButton.setToolTipText("Affect selected task(s) to a given Sprint");
        affectToSprintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                affectToSprintButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(affectToSprintButton, gridBagConstraints);

        moveItemButton.setText("Move"); // NOI18N
        moveItemButton.setToolTipText("Move selected task(s) to another work category");
        moveItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(moveItemButton, gridBagConstraints);

        splitItemButton.setText("Split"); // NOI18N
        splitItemButton.setToolTipText("Split selected task(s) into 2 task(s) with same attributes and parents");
        splitItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(splitItemButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        btsPanel.add(empty, gridBagConstraints);

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        removeButton.setText("Remove selected"); // NOI18N
        removeButton.setToolTipText("Removes selected task(s)");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        btsPanel.add(removeButton, gridBagConstraints);

        duplicateItemButton.setText("Duplicate"); // NOI18N
        duplicateItemButton.setToolTipText("Duplicate task");
        duplicateItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicateItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(duplicateItemButton, gridBagConstraints);

        sortItemsButton.setText("Sort"); // NOI18N
        sortItemsButton.setToolTipText("Sort all items");
        sortItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortItemsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        btsPanel.add(sortItemsButton, gridBagConstraints);

        buttonsPanel.add(btsPanel, java.awt.BorderLayout.CENTER);

        btExportSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-export.png"))); // NOI18N
        btExportSet.setText("Export"); // NOI18N
        btExportSet.setToolTipText("Export tasks list to PDF");
        btExportSet.setMaximumSize(new java.awt.Dimension(100, 29));
        btExportSet.setMinimumSize(new java.awt.Dimension(100, 29));
        btExportSet.setPreferredSize(new java.awt.Dimension(100, 29));
        btExportSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportSetActionPerformed(evt);
            }
        });
        expPanel.add(btExportSet);

        buttonsPanel.add(expPanel, java.awt.BorderLayout.EAST);

        topPanel.add(buttonsPanel);

        add(topPanel, java.awt.BorderLayout.NORTH);

        scrollPane.setBackground(new java.awt.Color(250, 250, 250));
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBar(null);

        itemTable.setBackground(new java.awt.Color(250, 250, 250));
        itemTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        itemTable.setRowHeight(20);
        itemTable.setRowSelectionAllowed(false);
        itemTable.getTableHeader().setResizingAllowed(false);
        itemTable.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(itemTable);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void titleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTextFieldActionPerformed
    itemSet.setTitle(titleTextField.getText());
}//GEN-LAST:event_titleTextFieldActionPerformed

private void titleTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleTextFieldFocusLost
    itemSet.setTitle(titleTextField.getText());
}//GEN-LAST:event_titleTextFieldFocusLost

private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
    removeWholeItemSet();
}//GEN-LAST:event_btRemoveActionPerformed

private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
    createNewItem();
}//GEN-LAST:event_addItemButtonActionPerformed

private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
    try {
        removeButtonPerformed();
    } catch (Throwable t) {
        handleThrowable(t);
    }
}//GEN-LAST:event_removeButtonActionPerformed

private void affectToSprintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_affectToSprintButtonActionPerformed
    showSprintChooserDialog();
}//GEN-LAST:event_affectToSprintButtonActionPerformed

private void moveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveItemButtonActionPerformed
    showItemSetChooserDialog();
}//GEN-LAST:event_moveItemButtonActionPerformed

private void btExportSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportSetActionPerformed
    exportItemSetToPdf();
}//GEN-LAST:event_btExportSetActionPerformed

private void archiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveCheckBoxActionPerformed
    if (itemSet instanceof ProjectItemSet) {
        ((ProjectItemSet) itemSet).setArchive(archiveCheckBox.isSelected());
    }
}//GEN-LAST:event_archiveCheckBoxActionPerformed

private void splitItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitItemButtonActionPerformed
    if( checkSomethingSelected() ){
        List<Item> items = getSelectedItems();
        for (Item item : items) {
            splitItem(item);
        }
        resetItemSet();
    }
}//GEN-LAST:event_splitItemButtonActionPerformed

    private void duplicateItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicateItemButtonActionPerformed
        if( checkSomethingSelected() ){
            List<Item> items = getSelectedItems();
            for (Item item : items) {
                duplicateItem(item);
            }
            resetItemSet();
        }
    }//GEN-LAST:event_duplicateItemButtonActionPerformed

    private void sortItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortItemsButtonActionPerformed
        itemSet.forceSortingElements();
        resetItemSet();
    }//GEN-LAST:event_sortItemsButtonActionPerformed

    private void splitItem(Item item){
        try{
            Item newItem = toolkit.getItemFactory().createCopy(item);

            FiboPoint eval = FiboPoint.getPrevious(item.getEvaluation());
            //taking previous value for work load
            item.setEvaluation(eval);
            newItem.setEvaluation(eval);
            newItem.setDescription(item.getDescription()+" (split #2)");
            newItem.setTitle(item.getTitle()+" #2");
            
            item.getProjectItemSet().addItem(newItem);
            if( item.getProjectItemSet()!=this.itemSet){
                this.itemSet.addItem(newItem);
            }
        }catch(Exception e){
            toolkit.logger.log(Level.WARNING,"Item "+item+" could not be duplicated",e);
        }
    }

    private void duplicateItem(Item item){
        try{
            Item newItem = toolkit.getItemFactory().createCopy(item);
            item.getProjectItemSet().addItem(newItem);
            if( item.getProjectItemSet()!=this.itemSet){
                this.itemSet.addItem(newItem);
            }
        }catch(Exception e){
            toolkit.logger.log(Level.WARNING,"Item "+item+" could not be duplicated",e);
        }
    }

    private boolean checkSomethingSelected(){
        List<Item> selectedItems = getSelectedItems();
        boolean somethingSelected = selectedItems!=null && !selectedItems.isEmpty();
        if (!somethingSelected) {
            JOptionPane.showMessageDialog(this,
                    "...because no item is selected.",
                    "Cannot do anything...",
                    JOptionPane.WARNING_MESSAGE);
        }
        return somethingSelected;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addItemButton;
    protected javax.swing.JButton affectToSprintButton;
    private javax.swing.JCheckBox archiveCheckBox;
    private javax.swing.JButton btExportSet;
    private javax.swing.JButton btRemove;
    private javax.swing.JPanel btsPanel;
    private javax.swing.JPanel buttonsPanel;
    protected javax.swing.JButton duplicateItemButton;
    private javax.swing.JPanel empty;
    private javax.swing.JPanel expPanel;
    protected javax.swing.JPanel itemSetMainPanel;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    protected javax.swing.JButton moveItemButton;
    protected javax.swing.JButton removeButton;
    private javax.swing.JScrollPane scrollPane;
    protected javax.swing.JButton sortItemsButton;
    protected javax.swing.JButton splitItemButton;
    protected javax.swing.JPanel titlePanel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel totalWorkLabel;
    // End of variables declaration//GEN-END:variables
}
