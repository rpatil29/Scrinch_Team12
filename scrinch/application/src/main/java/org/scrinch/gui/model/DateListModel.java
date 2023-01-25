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

import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.scrinch.gui.ScrinchGuiToolkit;
import org.scrinch.model.ScrinchEnvToolkit;

public class DateListModel implements ListModel {

    private List<ListDataListener> listDataListenerList = new Vector();
    private List<Date> dateList = new Vector();

    public class FormattedDate {
        private Date date;

        public FormattedDate(Date date){
            this.date = date;
        }

        @Override
        public String toString(){
            return ScrinchGuiToolkit.getDefaultDayFormat().format(this.date);
        }
    }

    public DateListModel(List<Date> dateList, boolean refuseFutureDates, ScrinchEnvToolkit toolkit) {
        for(Date date : dateList ){
            if( !date.after(toolkit.getCurrentDate()) ){
                this.dateList.add(date);
            }
        }
    }

    public Object getElementAt(int index) {
        return new FormattedDate(dateList.get(index));
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return dateList.size();
    }
}
