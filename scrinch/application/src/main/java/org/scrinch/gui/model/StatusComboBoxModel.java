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

import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import org.scrinch.model.Item;

public class StatusComboBoxModel implements ComboBoxModel {

    private List<Item.Status> itemStatusList = new Vector();
    private List<ListDataListener> listDataListenerList = new Vector();
    
    private Item.Status selectedStatusInCombo;
    
    private Item item;
    private boolean onlyPossibleStatuses;

    public StatusComboBoxModel(Item item) {
        this(item,false);
    }
    
    public StatusComboBoxModel(Item item, boolean onlyPossibleStatuses) {
        this.item = item;
        this.onlyPossibleStatuses = onlyPossibleStatuses;
        resetModel();
    }

    public void setSelectedItem(Object object) {
        if(object instanceof Item.Status){
            selectedStatusInCombo = (Item.Status)object;
        }
    }

    public Object getElementAt(int i) {
        return itemStatusList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return this.itemStatusList.size();
    }

    public Object getSelectedItem() {
        return selectedStatusInCombo;
    }
    
    public void checkUpdates(){
        resetModel();
    }

    public void resetModel() {
        itemStatusList.clear();
        selectedStatusInCombo = null;
        Item.Status[] concreteStatuses = Item.Status.getConcreteStatuses();
        for(int i=0; i<concreteStatuses.length; i++){
            if( !onlyPossibleStatuses || Item.isStatusValid(concreteStatuses[i], item.getVisasHistory())){
                itemStatusList.add(concreteStatuses[i]);
            }
        }
        notifyListeners();
    }
    
    private void notifyListeners(){
        for(ListDataListener listener : listDataListenerList ){
            listener.contentsChanged(null);
        }
    }
}
