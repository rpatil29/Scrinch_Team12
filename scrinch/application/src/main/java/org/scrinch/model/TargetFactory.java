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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class TargetFactory implements Target.Listener {

    public interface Listener {
        public void targetChanged(Target target);
        public void targetListChanged();
    }

    private List<Listener> listeners = new Vector();

    private List<Target> targetList = new Vector();

    private final ScrinchEnvToolkit toolkit;

    protected TargetFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void removeListener(Listener listener){
        listeners.remove(listener);
    }

    private void fireListChanged(){
        for(Listener listener : listeners){
            listener.targetListChanged();
        }
        this.toolkit.dataChanged();
    }

    public void targetChanged(Target target){
        for(Listener listener : listeners){
            listener.targetChanged(target);
        }
    }
    
    public void memberSortablePropertiesChanged(Target target){
        Collections.sort(targetList);
        fireListChanged();        
    }

    public Target createTarget(){
        Target target = new Target(toolkit);
        targetList.add(target);
        target.addListener(this);
        fireListChanged();
        Collections.sort(targetList);
        return target;
    }

    public List<Target> getTargetList(){
        List<Target> allTargets = new Vector();
        allTargets.addAll(targetList);
        return allTargets;
    }

    public List<Target> findActiveTargets(){
        List<Target> activeTargets = new ArrayList<Target>();
        for(Target target : targetList){
            if(target.isActive()){
                activeTargets.add(target);
            }
        }
        return activeTargets;
    }

    public Target findTargetWithLabel(String label){
        Target targetFound = null;
        for(Target target : targetList){
            if(target.getLabel().equals(label)){
                targetFound = target;
                break;
            }
        }
        return targetFound;
    }

    public void dispose(Target target) throws ScrinchException {
        if(ItemToolkit.findItemsWithTarget(toolkit.getItemFactory().getItemList(), target).size()>0){
            throw new ScrinchException("Target "+target.getLabel()
                    +" could not be deleted : target still in use");
        }
        targetList.remove(target);
        fireListChanged();
    }

    public void disposeAll(){
        for(Target target : targetList){
            target.removeListener(this);
        }
        targetList.clear();
        fireListChanged();
    }

    public org.scrinch.model.castor.Targets toCastor() throws UnsupportedEncodingException{
        org.scrinch.model.castor.Targets targets = new org.scrinch.model.castor.Targets();
        for(Target target : targetList){
            targets.addTarget(target.toCastor());
        }
        return targets;
    }

    public void loadFromCastor(org.scrinch.model.castor.Targets targets)
        throws ScrinchException, UnsupportedEncodingException{
        for(int i=0; i<targets.getTargetCount(); i++){
            Target target = Target.fromCastor(targets.getTarget(i), toolkit);
            target.addListener(this);
            this.targetList.add(target);
        }
        Collections.sort(targetList);
        fireListChanged();
    }
}
