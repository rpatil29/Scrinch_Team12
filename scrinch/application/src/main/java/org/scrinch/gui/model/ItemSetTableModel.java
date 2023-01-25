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

package org.scrinch.gui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.scrinch.model.FiboPoint;
import org.scrinch.model.Item;
import org.scrinch.model.ItemSet;
import org.scrinch.model.OriginType;
import org.scrinch.model.Sprint;
import org.scrinch.model.Target;
import org.scrinch.model.WorkType;

public class ItemSetTableModel implements TableModel{
  
    private List<TableModelListener> listeners = new ArrayList<TableModelListener>();
    private List<Item> itemList = new ArrayList<Item>();
    private Map<Item, Boolean> selectionPerItemMap =  new HashMap<Item, Boolean>();

    public List<Item> getSelectedItems() {
        List<Item> selectedItems = new ArrayList<Item>();
        for(Entry<Item,Boolean> entry:selectionPerItemMap.entrySet()){
            if(entry.getValue()){
                selectedItems.add(entry.getKey());
            }
        }
        return selectedItems;
    }
    
    public ItemSetTableModel(){
    }
        
    private void notifyListeners(Integer row){
        for(TableModelListener listener : listeners){
            TableModelEvent event = null;
            if(row!=null){
                event = new TableModelEvent(this, row.intValue());
            } 
            listener.tableChanged(event);
        }
    }
        
    public void setItemSet(ItemSet itemSet){
        itemList.clear();
        selectionPerItemMap.clear();
        itemList.addAll((List<Item>)itemSet.getItemList());
        for(Item item:itemSet.getItemList()){
            selectionPerItemMap.put(item, false);
        }
        notifyListeners(null);
    }
    
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0: return Boolean.class;
            case 4: return OriginType.class;
            case 5: return WorkType.class;
            case 6: return FiboPoint.class;
            case 7: return FiboPoint.class;
            case 8: return Target.class;
            case 9: return Item.class;
        }
        return String.class;
    }

    public int getColumnCount() {
        return 12;
    }

    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0: return "";
            case 1: return "Id";
            case 2: return "Title";
            case 3: return "Description";
            case 4: return "Origin";
            case 5: return "Type";
            case 6: return "BV";
            case 7: return "W";
            case 8: return "Target";
            case 9: return "Status";
            case 10: return "Project";
            case 11: return "Sprint";
        }
        return "";
    }

    public int getRowCount() {
        return itemList.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return selectionPerItemMap.get(itemList.get(rowIndex));
            case 1: return itemList.get(rowIndex).getKey();
            case 2: return itemList.get(rowIndex).getTitle();
            case 3: return itemList.get(rowIndex).getDescription();
            case 4: return itemList.get(rowIndex).getOriginType();
            case 5: return itemList.get(rowIndex).getWorkType();
            case 6: return itemList.get(rowIndex).getBusinessValue();
            case 7: return itemList.get(rowIndex).getEvaluation();
            case 8: return itemList.get(rowIndex).getTarget();
            case 9: return itemList.get(rowIndex);
            case 10: return itemList.get(rowIndex).getProjectItemSet().getProject().getProjectName()+"/"+itemList.get(rowIndex).getProjectItemSet().getTitle();
            case 11: return getSprintInfoAsString(itemList.get(rowIndex));
        }
        return "";
    }
    
    private String getSprintInfoAsString(Item item) {
        String itemRelatedSprintsAsString = "";

        Set<Sprint> itemRelatedSprints = item.getRelatedSprints();
        for (Sprint relatedSprint : itemRelatedSprints) {
            itemRelatedSprintsAsString += relatedSprint.getTitle() + ", ";
        }
        if (itemRelatedSprints.size() > 0) {
            itemRelatedSprintsAsString = itemRelatedSprintsAsString.substring(0, itemRelatedSprintsAsString.length() - 2);
        }
        return itemRelatedSprintsAsString;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex==1 || columnIndex==10 || columnIndex==11){
            return false;
        }
        return true;
    }

    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: selectionPerItemMap.put(itemList.get(rowIndex),(Boolean)aValue); break;
            case 2: itemList.get(rowIndex).setTitle((String)aValue); break;
            case 3: itemList.get(rowIndex).setDescription((String)aValue); break;
            case 4: itemList.get(rowIndex).setOriginType((OriginType)aValue); break;
            case 5: itemList.get(rowIndex).setWorkType((WorkType)aValue); break;
            case 6: itemList.get(rowIndex).setBusinessValue((FiboPoint)aValue); break;
            case 7: itemList.get(rowIndex).setEvaluation((FiboPoint)aValue); break;
            case 8: itemList.get(rowIndex).setTarget((Target)aValue); break;
        }
        notifyListeners(new Integer(rowIndex));
    }
}
