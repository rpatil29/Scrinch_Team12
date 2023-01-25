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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class ItemSet {

    public static final Comparator<ItemSet> BY_TITLE_DESCENDING_COMPARATOR = new Comparator<ItemSet>(){
        public int compare(ItemSet o1, ItemSet o2) {
            int toReturn = 0;
            if(o1.getTitle()==null && o2.getTitle()==null){
                toReturn =0;
            } else if (o1.getTitle()==null){
                toReturn = -1;
            } else if (o2.getTitle()==null){
                toReturn = 1;
            }else {
                toReturn = o2.getTitle().compareTo(o1.getTitle());
            }
            return toReturn;
        }
    };

    public static final Comparator<ItemSet> BY_TITLE_ASCENDING_COMPARATOR = new Comparator<ItemSet>(){
        public int compare(ItemSet o1, ItemSet o2) {
            return BY_TITLE_DESCENDING_COMPARATOR.compare(o2, o1);
        }
    };

    public interface Listener {
        public void orderChanged(ItemSet itemSet);
        public void titleChanged(ItemSet itemSet);
    };

    protected List<Item> itemList = new Vector<Item>();
    private String title;
    private List<Listener> listeners = new Vector<Listener>();

    public ItemSet() {
    }
         
    public final void addListener(Listener listener){
        if(!this.listeners.contains(listener)){
            this.listeners.add(listener);
        }
    }

    public final void removeListener(Listener listener){
        this.listeners.remove(listener);
    }

    private void notifyOrderChanged(){
        for(int i=0 ; i<listeners.size() ; i++){
            this.listeners.get(i).orderChanged(this);
        }
    }
    
    private void notifyTitleChanged(){
        for(int i=0 ; i<listeners.size() ; i++){
            this.listeners.get(i).titleChanged(this);
        }
    }

    public boolean containsItem(Item item){
        return itemList.contains(item);
    }

    public void rankChanged(Item item){
        sortElements();
        notifyOrderChanged();
    }

    public void addItems(Collection<Item> items){
        for(Item item:items){
            affectItem(item);
        }
        sortElements();
        notifyOrderChanged();
    }

    public void addItem(Item item){
        if(!itemList.contains(item)) {
            affectItem(item);
            sortElements();
        }
        notifyOrderChanged();
    }

    protected void sortElements(){
        Collections.sort(itemList, new Comparator() {
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Item && o2 instanceof Item){
                    Item item1 = (Item)o1;
                    Item item2 = (Item)o2;
                    return item1.compareTo(item2,ItemSet.this);
                } else{
                    return 0;
                }
            }
            @Override
            public boolean equals(Object obj) {
                return this==obj;
            }
        });
    }
    
    public void forceSortingElements(){
        sortElements();
    }

    protected void affectItem(Item item) {
        if(item!=null){
            itemList.add(item);
        }
    }

    public void removeItem(Item item, boolean itemWillBeMoved) throws ScrinchException{
        itemList.remove(item);
    }

    public Collection<Item> getItemList(){
        return itemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyTitleChanged();
    }

    @Override
    public String toString(){
        return this.getTitle();
    }
}
