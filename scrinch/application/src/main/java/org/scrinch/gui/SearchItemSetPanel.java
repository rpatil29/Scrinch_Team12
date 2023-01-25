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

package org.scrinch.gui;

import java.util.Collection;
import org.scrinch.model.Item;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.OriginType;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Target;
import org.scrinch.model.WorkType;

public class SearchItemSetPanel extends ItemTablePanel {

    public SearchItemSetPanel(ScrinchEnvToolkit toolkit) {
        super(true, toolkit);
        addItemButton.setVisible(false);
        removeButton.setVisible(false);
        splitItemButton.setVisible(false);
        removeTitlePanel();
        this.moveItemButton.setVisible(false);
    }
    
    @Override
    protected void releaseAllResources(){
        super.releaseAllResources();
    }
    
    @Override
    public void removeWholeItemSet(){
        setItemSet(null);
    }

    public void searchByStatus(final Item.Status status){
        Collection results = ItemToolkit.findItemsWithStatus(toolkit.getItemFactory().getItemList(),status);
        setItems(results, "List of items with status "+status);
    }

    public void searchByTarget(final Target target){
        Collection results = ItemToolkit.findItemsWithTarget(toolkit.getItemFactory().getItemList(),target);
        setItems(results, "List of items with target "+target);
    }
    
    public void searchByWorkType(final WorkType workType){
        Collection results = ItemToolkit.findItemsWithWorkType(toolkit.getItemFactory().getItemList(),workType);
        setItems(results, "List of items with workType "+workType);
    }
    
    public void searchByOriginType(final OriginType originType){
        Collection results = ItemToolkit.findItemsWithOriginType(toolkit.getItemFactory().getItemList(),originType);
        setItems(results, "List of items with originType "+originType);
    }

    public void searchToBeReaffectedItems(){
        Collection results = ItemToolkit.findToBeReaffectedItems(toolkit.getItemFactory().getItemList(), toolkit);
        setItems(results, "List of items to be reaffected");
    }
    
    public void searchByKeyword(String keyword){
        Collection results = ItemToolkit.findByKeyword(toolkit.getItemFactory().getItemList(), keyword);
        setItems(results, "List of items for keyword");
    }
}
