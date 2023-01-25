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

public class DurationComboBoxModel implements ComboBoxModel {

    public static class Duration {
        private String label;
        private int durationInWeeks;

        public Duration(String label, int durationInWeeks){
            this.label = label;
            this.durationInWeeks = durationInWeeks;
        }

        public int getDurationInWeeks(){
            return durationInWeeks;
        }

        public String toString(){
            return this.label;
        }
    }

    private static List<Duration> durationList = new Vector();
    private Duration selectedItem;

    static{
        durationList.add(new Duration("1 week",1));
        durationList.add(new Duration("2 weeks",2));
        durationList.add(new Duration("3 weeks",3));
        durationList.add(new Duration("4 weeks",4));
    }

    public DurationComboBoxModel() {
    }

    public void setSelectedItem(Object object) {
        this.selectedItem = (Duration)object;
    }

    public Object getElementAt(int i) {
        return durationList.get(i);
    }

    public void removeListDataListener(ListDataListener listDataListener) {
    }

    public void addListDataListener(ListDataListener listDataListener) {
    }

    public int getSize() {
        return this.durationList.size();
    }

    public Object getSelectedItem() {
        return selectedItem;
    }
}
