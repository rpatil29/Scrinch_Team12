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
import java.util.Date;
import java.util.Vector;

public class ItemFactory {

    private Date oldestEvaluatedItemDate;

    private Collection<Item> items = new Vector<Item>();

    private final ScrinchEnvToolkit toolkit;

    protected ItemFactory(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
    }

    public Item createItem(){
        Item item = new Item(toolkit);
        items.add(item);
        this.toolkit.dataChanged();
        return item;
    }
    
    public Item createCopy(Item item) throws Exception {
        org.scrinch.model.castor.Item castorItem = item.toCastor();
        Item copyItem = Item.fromCastor(castorItem, toolkit);
        copyItem.setKey(Item.getNextId()+"");
        items.add(copyItem);
        this.toolkit.dataChanged();
        return copyItem;
    }

    public Collection<Item> getItemList(){
        return items;
    }

    public Date getOldestEvaluatedItemDate(){
        if(oldestEvaluatedItemDate==null){
            for(Item item : items){
                if (oldestEvaluatedItemDate==null ||
                        (item.getFirstVisaDate()!=null && oldestEvaluatedItemDate.after(item.getFirstVisaDate()))){
                    oldestEvaluatedItemDate = item.getFirstVisaDate();
                }
            }
        }
        return oldestEvaluatedItemDate;
    }

    public void dispose(Item item){
        items.remove(item);
        item.setProjectItemSet(null);
        this.toolkit.dataChanged();
    }

    public void disposeAll(){
        for(Item item : items){
            item.releaseAllResources();
        }
        items.clear();
        this.toolkit.dataChanged();
    }

    public org.scrinch.model.castor.Items toCastor(){
        org.scrinch.model.castor.Items cItems = new org.scrinch.model.castor.Items();
        synchronized(items){
            for ( Item item : this.items) {
                org.scrinch.model.castor.Item castorItem = item.toCastor();
                cItems.addItem(castorItem);
            }
        }
        return cItems;
    }

    public void loadFromCastor(org.scrinch.model.castor.Items cItems) throws ScrinchException{
        Item.resetNextId();
        for(int i=0; i<cItems.getItemCount(); i++){
            org.scrinch.model.castor.Item cItem = cItems.getItem(i);
            Item loadedItem = Item.fromCastor(cItem, toolkit);
            this.items.add(loadedItem);
            if (oldestEvaluatedItemDate==null ||
                    (loadedItem.getFirstVisaDate()!=null && oldestEvaluatedItemDate.after(loadedItem.getFirstVisaDate()))){
                oldestEvaluatedItemDate = loadedItem.getFirstVisaDate();
            }
        }
    }
}
