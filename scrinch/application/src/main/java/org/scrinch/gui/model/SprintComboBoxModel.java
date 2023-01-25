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
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class SprintComboBoxModel implements ComboBoxModel {

    private List<Sprint> sprintList;
    private Sprint selectedSprint;
    private List<ListDataListener> listDataListenerList = new Vector();

    public SprintComboBoxModel(ScrinchEnvToolkit toolkit) {
        if(toolkit.isOldProjectsAndSprintsVisible()){
            sprintList = toolkit.getSprintFactory().getSprintList();
        } else {
            sprintList = toolkit.getSprintFactory().findTopicalSprints();
        }
    }

    public void setSelectedItem(Object object) {
        try {
            this.selectedSprint = (Sprint)object;
        } catch(Exception e){
            this.selectedSprint = null;
        }
    }

    public Object getElementAt(int i) {
        return sprintList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return this.sprintList.size();
    }

    public Object getSelectedItem() {
        return selectedSprint;
    }
}
