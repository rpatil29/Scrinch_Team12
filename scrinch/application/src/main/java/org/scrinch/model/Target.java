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

import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

public class Target implements Comparable<Target> {
    
    private final ScrinchEnvToolkit toolkit;

    public interface Listener {
        public void targetChanged(Target target);
        public void memberSortablePropertiesChanged(Target target);
    }

    private List<Listener> listeners = new Vector();

    private String label = "";
    private String description;
    private Date deadline;
    private boolean active = true;

    public Target(ScrinchEnvToolkit toolkit){
        this.toolkit = toolkit;
        this.deadline = toolkit.getCurrentDate();
    }

    private void fireTargetChanged(){
        for(Listener listener : listeners){
            listener.targetChanged(this);
        }
        this.toolkit.dataChanged();
    }
    
    private void fireSortablePropertiesChanged(){
        for(Listener listener : listeners){
            listener.memberSortablePropertiesChanged(this);
            listener.targetChanged(this);
        }
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
        fireSortablePropertiesChanged();
        this.toolkit.dataChanged();
    }

    @Override
    public String toString(){
        return this.getLabel();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if( !label.equals(this.label) ){
            this.label = label;
            fireTargetChanged();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        fireTargetChanged();
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
        fireSortablePropertiesChanged();
        this.toolkit.dataChanged();
    }
    
    public int compareTo(Target anotherTarget){
        if(anotherTarget.isActive()!=this.isActive()){
            return anotherTarget.isActive()?1:-1;
        }
        if(deadline.equals(anotherTarget.deadline)){
            return this.getLabel().compareTo(anotherTarget.getLabel());
        }
        return deadline.compareTo(anotherTarget.deadline);
    }
    
    public boolean equals(Object o){
        if( ! (o instanceof Target)){
            return false;
        }
        return (this.compareTo((Target)o)==0);
    }

    public static Target fromCastor(org.scrinch.model.castor.Target castorTarget, ScrinchEnvToolkit toolkit){
        Target target = new Target(toolkit);
        target.setActive(castorTarget.getActive());
        target.setDescription(castorTarget.getDescription());
        if(castorTarget.getCfoDate()!=null){
            // cfodate struct is deprecated (replaced by deadline), 
            // this parsing has been let for compatibility purposes
            target.setDeadline(castorTarget.getCfoDate().toDate());
            ScrinchEnvToolkit.logger.log(Level.WARNING,"Deprecated cfodate used)");
        } else {
            target.setDeadline(castorTarget.getDeadline().toDate());
        }
        
        target.setLabel(castorTarget.getLabel());
        return target;
    }

    public org.scrinch.model.castor.Target toCastor(){
        org.scrinch.model.castor.Target castorTarget = new org.scrinch.model.castor.Target();
        castorTarget.setActive(isActive());
        castorTarget.setDescription(getDescription());
        castorTarget.setDeadline(new org.exolab.castor.types.Date(getDeadline()));
        castorTarget.setLabel(getLabel());
        return castorTarget;
    }
}
