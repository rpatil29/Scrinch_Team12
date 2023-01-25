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

package org.scrinch.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author christian
 */
public abstract class AbstractType implements Comparable<AbstractType>{

    private String label;
    private String description;

    private List<Listener> listeners = Collections.synchronizedList(new ArrayList<Listener>());

    public interface Listener<T extends AbstractType>{
        void typeSortablePropertyChanged(T originType);
    }

    public AbstractType(String label){
        this.label = label;
    }

    private void fireSortablePropertiesChanged(){
        for(Listener listener : this.listeners.toArray(new Listener[0])){
            listener.typeSortablePropertyChanged(this);
        }
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    public void setLabel(String label){
        this.label = label;
        fireSortablePropertiesChanged();
    }

    public String getLabel(){
        return this.label;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return label;
    }

    public final int compareTo(AbstractType anotherOriginType){

        return getLabel().compareTo(anotherOriginType.getLabel());
    }

    @Override
    public final boolean equals(Object o){
        if( ! (o instanceof AbstractType)){
            return false;
        }
        return (this.compareTo((AbstractType)o)==0);
    }

    @Override
    public final int hashCode() {
        return label.hashCode();
    }

}
