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

import java.util.logging.Level;

public class ProjectItemSet extends ItemSet {

    private Project project;
    private boolean archive;

    public ProjectItemSet() {
    }

    public void setProject(Project project){
        this.project = project;
    }

    public Project getProject(){
        return this.project;
    }
    
    public void setArchive(boolean archive){
        this.archive = archive;
    }
    
    public boolean isArchive(){
        return this.archive;
    }

    @Override
    protected void affectItem(Item item){
        super.affectItem(item);
        item.setProjectItemSet(this);
    }

    @Override
    public void removeItem(Item item, boolean itemWillBeMoved) throws ScrinchException{
        if(!itemWillBeMoved && !item.getRelatedSprints().isEmpty()){
            String sprintListAsString = "";
            for(Sprint sprint:item.getRelatedSprints()){
                sprintListAsString+=sprint.getTitle()+", ";
            }
            throw new ScrinchException("Item "+item.getKey()+" cannot be removed as it is still linked to sprint(s): "+sprintListAsString);
        }
        itemList.remove(item);
        item.setProjectItemSet(null);
    }

    public org.scrinch.model.castor.ItemSet toCastor(){
        org.scrinch.model.castor.ItemSet cItemSet = new org.scrinch.model.castor.ItemSet();
        for (int i = 0; i < itemList.size(); i++) {
            Item item = (Item) itemList.get(i);
            cItemSet.addItemLink(item.getKey());
        }
        cItemSet.setName(this.getTitle());
        cItemSet.setArchive(this.isArchive());
        return cItemSet;
    }

    public static ProjectItemSet fromCastor(org.scrinch.model.castor.ItemSet cItemSet, ScrinchEnvToolkit toolkit)
      throws ScrinchException{
        ProjectItemSet itemSet = new ProjectItemSet();
        for (int i = 0; i < cItemSet.getItemLinkCount(); i++) {
            String itemKey = cItemSet.getItemLink(i);
            Item item = ItemToolkit.findItem(toolkit.getItemFactory().getItemList(),itemKey);
            if(item!=null){
                itemSet.addItem(item);
            } else {
                ScrinchEnvToolkit.logger.log(Level.WARNING,"Item #"+itemKey+" does not exist");
            }
        }
        itemSet.setTitle(cItemSet.getName());
        itemSet.setArchive(cItemSet.getArchive());
        return itemSet;
    }

}
