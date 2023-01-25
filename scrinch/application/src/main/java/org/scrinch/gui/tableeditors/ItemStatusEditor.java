package org.scrinch.gui.tableeditors;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.scrinch.gui.ItemTablePanel;
import org.scrinch.gui.StatusButton;
import org.scrinch.gui.StatusDialog;
import org.scrinch.gui.StatusQuickPickerDialog;
import org.scrinch.gui.WindowUtilities;
import org.scrinch.model.Item;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class ItemStatusEditor extends StatusButton implements TableCellEditor, TableCellRenderer{

    private final ScrinchEnvToolkit toolkit;
    private ItemTablePanel itemTablePanel;

    public void setItemTablePanel(ItemTablePanel itemTablePanel) {
        this.itemTablePanel = itemTablePanel;
    }
    
    public ItemStatusEditor(ScrinchEnvToolkit toolkit, ItemTablePanel itemTablePanel){
        super();
        setContentAreaFilled(false);
        this.toolkit = toolkit;
        this.itemTablePanel = itemTablePanel;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    showQuickPickerDialog();
                } else {
                    Frame appFrame = (Frame) SwingUtilities.windowForComponent(ItemStatusEditor.this);
                    JDialog dialog = new StatusDialog(appFrame, getItem(), getSprint(), ItemStatusEditor.this.toolkit);
                    WindowUtilities.centerFrame(dialog, appFrame);
                    dialog.setVisible(true);
                }
                resetCache(item);
            }
        });
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                showQuickPickerDialog();
            }
        });
    }
    
    private void showQuickPickerDialog(){
        Frame appFrame = (Frame) SwingUtilities.windowForComponent(ItemStatusEditor.this);
        StatusQuickPickerDialog statusQuickPicker = new StatusQuickPickerDialog(appFrame, ItemStatusEditor.this, getItem(), ItemStatusEditor.this.toolkit);
        statusQuickPicker.setVisible(true);
    }

    public Item getItem() {
        return item;
    }
    
    private Item item;
    
    private Sprint getSprint(){
        if(itemTablePanel.getItemSet() instanceof Sprint){
            return (Sprint)itemTablePanel.getItemSet();
        }
        return null;
    }

    public void resetCache(Object o){
        this.item = (Item)o;
        if(itemTablePanel.getItemSet() instanceof Sprint){
            setStatus(item.getStatus((Sprint)itemTablePanel.getItemSet()));
        } else {
            setStatus(item.getStatus());
        }
    }
    
    private Component getComponent(Object o){
        resetCache(o);
        return this;
    }
    
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        return getComponent(o);
    }
    
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        return getComponent(o);
    }

    public Object getCellEditorValue() {
        return this.getStatus();
    }

    public boolean isCellEditable(EventObject eo) {
        return true;
    }

    public boolean shouldSelectCell(EventObject eo) {
        return true;
    }

    public boolean stopCellEditing() {
        return true;
    }

    public void cancelCellEditing() {
    }

    public void addCellEditorListener(CellEditorListener cl) {
    }

    public void removeCellEditorListener(CellEditorListener cl) {
    }
}
