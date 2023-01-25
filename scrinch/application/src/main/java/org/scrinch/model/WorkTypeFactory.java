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

public class WorkTypeFactory implements AbstractType.Listener<WorkType>{

    private final WorkType DEFAULT_WORK_TYPE = new WorkType("Undefined");
    
    public interface Listener{
        public void workTypeListChanged();
    }
    
    private final List<Listener> listenerList = new ArrayList<>();
    
    public void addListener(Listener listener){
        this.listenerList.add(listener);
    }
    
    public void removeListener(Listener listener){
        this.listenerList.remove(listener);
    }
    
    private final List<WorkType> workTypes = new ArrayList<>();
    private final ScrinchEnvToolkit toolkit;
    
    protected WorkTypeFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        workTypes.add(DEFAULT_WORK_TYPE);
    }
    
    public WorkType getDefaultWorkType(){
        return DEFAULT_WORK_TYPE;
    }

    private void notifyListeners(){
        for(Listener listener : listenerList){
            listener.workTypeListChanged();
        }
    }
    
    @Override
    public void typeSortablePropertyChanged(WorkType workType){
        Collections.sort(workTypes);
        notifyListeners();
    }
    
    public WorkType findWorkTypeWithLabel(String label){
        WorkType workType = null;
        for (WorkType currentWorkType : workTypes) {
            if(currentWorkType.getLabel().equals(label)){
                workType = currentWorkType;
                break;
            }
        }
        if(workType==null){
            workType = createWorkType(label);
        }
        return workType;
    }

    public boolean existsWorkTypeWithLabel(String label){
        for (WorkType currentWorkType : workTypes) {
            if(currentWorkType.getLabel().equals(label)){
                return true;
            }
        }
        return false;
    }

    public WorkType createWorkType(String label){
        WorkType workType = DEFAULT_WORK_TYPE;
        if( label!=null && ! label.equals(DEFAULT_WORK_TYPE.getLabel())){
            String defaultLabel = label;
            workType = new WorkType(defaultLabel);
            addWorkType(workType);
        }
        return workType;
    }
    
    public void addWorkType(WorkType workType){
        addWorkTypeInternal(workType);
        workTypesListChanged();
    }

    private  void addWorkTypeInternal(WorkType workType){
        if( !workType.equals(DEFAULT_WORK_TYPE) ){
            workTypes.remove(DEFAULT_WORK_TYPE);
            workType.addListener(this);
            workTypes.add(workType);
            this.toolkit.dataChanged();
        }
    }
    
    private void workTypesListChanged(){
        Collections.sort(workTypes);
        notifyListeners();
    }

    public void loadFromCastor(org.scrinch.model.castor.WorkType[] castorWorkTypes){
        for (org.scrinch.model.castor.WorkType castorWorkType : castorWorkTypes) {
            WorkType workType = WorkType.fromCastor(castorWorkType, toolkit);
            addWorkTypeInternal(workType);
        }
        workTypesListChanged();
    }

    public org.scrinch.model.castor.WorkTypes toCastor(){
        org.scrinch.model.castor.WorkTypes cWorkTypes = new org.scrinch.model.castor.WorkTypes();
        for(WorkType workType : workTypes){
            cWorkTypes.addWorkType(workType.toCastor());
        }
        return cWorkTypes;
    }

    public List<WorkType> getWorkTypeList(){
        return workTypes;
    }

    public void dispose(WorkType workType){
        for(Item item : ItemToolkit.findItemsWithWorkType(toolkit.getItemFactory().getItemList(), workType)){
            item.setWorkType(getDefaultWorkType());
        }
        workTypes.remove(workType);
        workType.removeListener(this);
        if( workTypes.isEmpty() ){
            workTypes.add(DEFAULT_WORK_TYPE);
        }
        this.toolkit.dataChanged();
        workTypesListChanged();
    }
    
    public void disposeAll(){
        WorkType[] workTypeArray = this.workTypes.toArray(new WorkType[0]);
        for(WorkType workType : workTypeArray){
            dispose(workType);
        }
        this.toolkit.dataChanged();
    }
}
