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
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Target;
import org.scrinch.model.TargetFactory;

public class TargetComboBoxModel implements ComboBoxModel, TargetFactory.Listener {

    private List<Target> targetList;
    private Target selectedItem;
    private List<ListDataListener> listDataListenerList = new Vector();
    private boolean activeOnly;
    private Target fakeTarget;
    private final ScrinchEnvToolkit toolkit;

    public TargetComboBoxModel(boolean activeOnly, boolean withFakeTarget, ScrinchEnvToolkit toolkit) {
        this.activeOnly = activeOnly;
        if (withFakeTarget) {
            this.fakeTarget = new Target(toolkit);
            this.fakeTarget.setLabel("");
        }
        this.toolkit = toolkit;
        toolkit.getTargetFactory().addListener(this);
        refresh();
    }

    public void releaseAllResources() {
        toolkit.getTargetFactory().removeListener(this);
    }

    public void targetChanged(Target target) {
        refresh();
    }

    public void targetListChanged() {
        refresh();
    }

    private void refresh() {
        if (activeOnly) {
            targetList = toolkit.getTargetFactory().findActiveTargets();
        } else {
            targetList = toolkit.getTargetFactory().getTargetList();
        }
        if (fakeTarget != null) {
            targetList.add(0, fakeTarget);
        }
        fireUpdate();
    }

    private void fireUpdate() {
        ListDataEvent dataEvent = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, targetList.size() - 1);

        for (ListDataListener listener : listDataListenerList) {
            listener.contentsChanged(dataEvent);
        }
    }

    public void setSelectedItem(Object object) {
        System.out.println("trying to select item "+object);
        try {
            if(object == null || object.equals(fakeTarget)) {
                this.selectedItem = fakeTarget;
            } else {
                this.selectedItem = (Target) object;
            }
        } catch (Exception e) {
            this.selectedItem = null;
        }
    }

    public Object getElementAt(int i) {
        return targetList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return this.targetList.size();
    }

    public Object getSelectedItem() {
        return selectedItem;
    }
}
