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

public class OriginTypeFactory implements AbstractType.Listener<OriginType>{

    private final OriginType DEFAULT_ORIGIN_TYPE = new OriginType("Undefined");

    public interface Listener{
        public void originTypeListChanged();
    }
    
    private final List<Listener> listenerList = Collections.synchronizedList(new ArrayList<Listener>());
    
    public void addListener(Listener listener){
        this.listenerList.add(listener);
    }
    
    public void removeListener(Listener listener){
        this.listenerList.remove(listener);
    }
    
    private final List<OriginType> originTypes = new ArrayList<>();
    private final ScrinchEnvToolkit toolkit;
    
    protected OriginTypeFactory(ScrinchEnvToolkit toolkit) {
        originTypes.add(DEFAULT_ORIGIN_TYPE);
        this.toolkit = toolkit;
    }
    
    public OriginType getDefaultOriginType(){
        return DEFAULT_ORIGIN_TYPE;
    }

    private void notifyListeners(){
        for(Listener listener : listenerList){
            listener.originTypeListChanged();
        }
    }
    
    @Override
    public void typeSortablePropertyChanged(OriginType originType){
        Collections.sort(originTypes);
        notifyListeners();
    }
    
    public boolean existsOriginTypeWithLabel(String label) {
        for (OriginType currentOriginType : originTypes) {
            if(currentOriginType.getLabel().equals(label)){
                return true;
            }
        }
        return false;
    }

    public OriginType findOriginTypeWithLabel(String label){
        OriginType originType = null;
        for (OriginType currentOriginType : originTypes) {
            if(currentOriginType.getLabel().equals(label)){
                originType = currentOriginType;
                break;
            }
        }
        if(originType==null){
            originType = createOriginType(label);
        }
        return originType;
    }

    public OriginType createOriginType(String label){
        OriginType originType = DEFAULT_ORIGIN_TYPE;
        if( label!=null && ! label.equals(DEFAULT_ORIGIN_TYPE.getLabel())){
            String defaultLabel = label;
            originType = new OriginType(defaultLabel);
            addOriginType(originType);
        }
        return originType;
    }
    
    public void addOriginType(OriginType originType){
        addOriginTypeInternal(originType);
        originTypesListChanged();
    }

    private void addOriginTypeInternal(OriginType originType){
        if( ! originType.equals(DEFAULT_ORIGIN_TYPE)){
            originTypes.remove(DEFAULT_ORIGIN_TYPE);
            originType.addListener(this);
            originTypes.add(originType);
            this.toolkit.dataChanged();
        }
    }

    public void loadFromCastor(org.scrinch.model.castor.OriginType[] castorOriginTypes){
        for (org.scrinch.model.castor.OriginType castorOriginType : castorOriginTypes) {
            OriginType originType = OriginType.fromCastor(castorOriginType, toolkit);
            addOriginTypeInternal(originType);
        }
        originTypesListChanged();
    }

    private void originTypesListChanged(){
        Collections.sort(originTypes);
        notifyListeners();
    }

    public org.scrinch.model.castor.OriginTypes toCastor(){
        org.scrinch.model.castor.OriginTypes cOriginTypes = new org.scrinch.model.castor.OriginTypes();
        for(OriginType originType : originTypes){
            cOriginTypes.addOriginType(originType.toCastor());
        }
        return cOriginTypes;
    }

    public List<OriginType> getOriginTypeList(){
        return originTypes;
    }
    
    public void dispose(OriginType originType){
        for(Item item : ItemToolkit.findItemsWithOriginType(toolkit.getItemFactory().getItemList(), originType)){
            item.setOriginType(getDefaultOriginType());
        }
        originTypes.remove(originType);
        originType.removeListener(this);
        if( originTypes.isEmpty() ){
            originTypes.add(DEFAULT_ORIGIN_TYPE);
        }
        this.toolkit.dataChanged();
        originTypesListChanged();
    }
    
    public void disposeAll(){
        OriginType[] originTypeArray = this.originTypes.toArray(new OriginType[0]);
        for(OriginType originType : originTypeArray){
            dispose(originType);
        }
        this.toolkit.dataChanged();
    }
}
