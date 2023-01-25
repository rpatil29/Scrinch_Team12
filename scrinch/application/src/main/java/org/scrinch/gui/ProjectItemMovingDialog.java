package org.scrinch.gui;

import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.scrinch.gui.model.ItemSetComboBoxModel;
import org.scrinch.model.Item;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ScrinchEnvToolkit;

public class ProjectItemMovingDialog extends ProjectItemSetPickerDialog{
    
    private ItemTablePanel tablePanel;
    private List<Item> selectedItems;

    public ProjectItemMovingDialog(java.awt.Frame parent, ItemTablePanel panel, ScrinchEnvToolkit toolkit) {
        super(parent, toolkit);
        this.tablePanel = panel;
        this.selectedItems = panel.getSelectedItems();
        this.lbItems.setText(idList(this.selectedItems));  
    }

    private String idList(List<Item> itemList){
        StringBuilder sb = new StringBuilder();
        for(Item item : itemList){
            sb.append('#').append(item.getKey()).append(", ");
        }
        if(itemList.size()>0){
            sb.delete(sb.length()-2, sb.length()-1);
        }
        return sb.toString();
    }
    
    private void moveItemsToSelectedItemSet(){
        try{
            if( ! (this.tablePanel instanceof SprintPanel)){
                this.tablePanel.removeItems(this.selectedItems, false);
            }
            ProjectItemSet selectedProjectItemSet = getSelectedProjectItemSet();
            if( selectedProjectItemSet!=null ){
                selectedProjectItemSet.addItems(this.selectedItems);
                this.tablePanel.unselectAll();
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Choose an item set to affect to in the list", "", JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception e){
            ScrinchEnvToolkit.logger.log(Level.WARNING, "Could not affect selected items", e);
        }        
    }
    
    @Override
    protected void itemSetPickerValidation() {
        moveItemsToSelectedItemSet();
    }
}
