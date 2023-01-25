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
import org.scrinch.model.Project;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ScrinchEnvToolkit;

public class ItemSetComboBoxModel implements ComboBoxModel {

    public class ItemSetModel {
        public ProjectItemSet projectItemSet;
        public Project project;
        
        @Override
        public String toString(){
            return project.toString() + " : " + projectItemSet.toString();
        }
    }
    
    private List<ItemSetModel> itemSetList = new Vector<ItemSetModel>();
    private ItemSetModel selectedItemSet;
    private List<ListDataListener> listDataListenerList = new Vector();

    public ItemSetComboBoxModel(ScrinchEnvToolkit toolkit) {
        List<Project> projects;
        if(toolkit.isOldProjectsAndSprintsVisible()){
            projects = toolkit.getProjectFactory().getList();
        } else {
            projects = toolkit.getProjectFactory().findTopicals();
        }
        for(Project project : projects){
            for(ProjectItemSet projectItemSet : project.getProjectItemSetList()){
                if( !project.isMaintenanceProject() || !projectItemSet.isArchive() ){
                    ItemSetModel itemSetModel = new ItemSetModel();
                    itemSetModel.project = project;
                    itemSetModel.projectItemSet = projectItemSet;
                    itemSetList.add(itemSetModel);
                }
            }
        }
    }

    public void setSelectedItem(Object object) {
        try {
            this.selectedItemSet = (ItemSetModel)object;
        } catch(Exception e){
            this.selectedItemSet = null;
        }
    }

    public Object getElementAt(int i) {
        return itemSetList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return this.itemSetList.size();
    }

    public Object getSelectedItem() {
        return selectedItemSet;
    }
}
