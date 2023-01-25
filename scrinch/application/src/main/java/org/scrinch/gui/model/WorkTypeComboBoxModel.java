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
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.WorkType;
import org.scrinch.model.WorkTypeFactory;

public class WorkTypeComboBoxModel implements WorkTypeFactory.Listener, ComboBoxModel {

    private List<WorkType> workTypeList = new ArrayList<WorkType>();
    private WorkType selectedWork;
    private List<ListDataListener> listDataListenerList = new ArrayList<ListDataListener>();
    private final ScrinchEnvToolkit toolkit;

    public WorkTypeComboBoxModel(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        updateWorkTypeList();
        toolkit.getWorkTypeFactory().addListener(this);
    }

    public void workTypeListChanged(){
        updateWorkTypeList();
        notifyListeners();
    }
    
    private void updateWorkTypeList(){
        workTypeList.clear();
        workTypeList.addAll(toolkit.getWorkTypeFactory().getWorkTypeList());
    }
    
    public void releaseAllResources(){
        toolkit.getWorkTypeFactory().removeListener(this);
    }

    public void setSelectedItem(Object object) {
        try {
            this.selectedWork = (WorkType)object;
        } catch(Exception e){
            this.selectedWork = null;
        }
    }

    public Object getElementAt(int i) {
        return workTypeList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }
    
    private void notifyListeners(){
        for(ListDataListener listener : listDataListenerList){
            listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, listDataListenerList.size()-1));
        }
    }

    public int getSize() {
        return this.workTypeList.size();
    }

    public Object getSelectedItem() {
        return selectedWork;
    }
}
