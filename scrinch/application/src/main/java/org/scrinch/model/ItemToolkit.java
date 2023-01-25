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
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class ItemToolkit {
    public static int getTotalFiboPoints(Collection<Item> items){
        int totalFiboPoints = 0;

        for(Item item : items){
            totalFiboPoints += item.getEvaluation().intValue();
        }

        return totalFiboPoints;
    }
    
    public static Date getOldestDate(Collection<Item> items){
        Date oldestDate = null;
        for(Item item : items){
            Date currentItemDate = item.getFirstVisaDate();
            if(currentItemDate!=null){
                if(oldestDate==null){
                    oldestDate = currentItemDate;
                } else {
                    if(currentItemDate.before(oldestDate)){
                        oldestDate = currentItemDate;
                    }
                }
            }
        }
        return oldestDate;
    }
    
    public static Date getLatestDate(Collection<Item> items){
        Date latestDate = null;
        for(Item item : items){
            Date currentItemDate = item.getVisasHistory().peek().date;
            if(currentItemDate!=null){
                if(latestDate==null){
                    latestDate = currentItemDate;
                } else {
                    if(currentItemDate.after(latestDate)){
                        latestDate = currentItemDate;
                    }
                }
            }
        }
        return latestDate;
    }
    
    public static Collection<Item> findWorkDoneItems(Collection<Item> items){
        Collection<Item> itemsFound = new ArrayList<>();
        for(Item item : items) {
            if(item.getStatus().isWorkDone()){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Collection<Item> findWorkToBeDoneItems(Collection<Item> items){
        Collection<Item> itemsFound = new ArrayList<>();
        for(Item item : items) {
            if(item.getStatus().isWorkToBeDone()){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Item findItem(Collection<Item> items, String key){
        for (Item item : items) {
            if(key!=null && key.equals(item.getKey())){
                return item;
            }
        }
        return null;
    }

    public static Collection<Item> findItemsWithTarget(Collection<Item> items, Target target){
        Collection<Item> itemsFound = new ArrayList<>();
        for ( Item item : items) {
            if(target!=null && target.equals(item.getTarget())){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Collection<Item> findItemsWithStatus(Collection<Item> items, Item.Status status){
        Collection<Item> itemsFound = new ArrayList<>();
        for ( Item item : items) {
            if(item.getStatus().equals(status)){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Collection<Item> findItemsWithVisaFrom(Collection<Item> items, String nickname){
        Collection<Item> itemsFound = null;
        for ( Item item : items) {
            Stack<Item.Visa> visas = item.getVisasHistory();
            for(Item.Visa visa : visas){
                if(visa.member!=null && nickname.equals(visa.member.getNickname())){
                    if(itemsFound==null){
                        itemsFound = new ArrayList<>();
                    }
                    itemsFound.add(item);
                    break;
                }
            }
        }
        return itemsFound;
    }

    public static Collection<Item> findByKeyword(Collection<Item> items, String keyword){
        Collection<Item> itemsFound = new ArrayList<>();
        String lowerCaseKeyWord = keyword.toLowerCase();
        for (Item item : items) {
            if(item.getTitle().toLowerCase().contains(lowerCaseKeyWord) || item.getDescription().toLowerCase().contains(lowerCaseKeyWord)){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }

    public static Collection<Item> findOrphanItems(ScrinchEnvToolkit toolkit){
        Collection<Item> orphans = new ArrayList<>();
        Collection<Item> items = toolkit.getItemFactory().getItemList();
        for(Item item : items) {
            if(item.isOrphan()){
                orphans.add(item);
            }
        }
        return orphans;
    }

    public static Collection<Item> findToBeReaffectedItems(Collection<Item> items, ScrinchEnvToolkit toolkit){
        Collection<Item> itemsFound = new ArrayList<>();

        Collection<Item> workToBeDoneItems = new ArrayList<>();
        workToBeDoneItems.addAll(ItemToolkit.findWorkToBeDoneItems(items));

        Collection<Item> workToBeDoneItemsInTopicalSprints = new ArrayList<>();
        workToBeDoneItemsInTopicalSprints.addAll(findWorkToBeDoneItemsInTopicalSprints(items, toolkit));

        itemsFound.addAll(workToBeDoneItems);
        itemsFound.removeAll(workToBeDoneItemsInTopicalSprints);

        return itemsFound;
    }

    public static Collection<Item> findWorkToBeDoneItemsInTopicalSprints(Collection<Item> items, ScrinchEnvToolkit toolkit){
        Collection<Item> itemsFound = new ArrayList<>();
        List<Sprint> topicalSprints = toolkit.getSprintFactory().findTopicalSprints();
        for(Sprint sprint : topicalSprints){
            itemsFound.addAll(sprint.getItemList());
        }
        itemsFound = ItemToolkit.findWorkToBeDoneItems(itemsFound);
        return itemsFound;
    }

    public static Collection<Item> findItemsWithWorkType(Collection<Item> items, WorkType workType){
        Collection<Item> itemsFound = new ArrayList<>();
        for(Item item : items){
            if(item.getWorkType().equals(workType)){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Collection<Item> findWorkDoneItemsWithWorkType(Collection<Item> items, WorkType workType){
        Collection<Item> workTypeItems = findItemsWithWorkType(items, workType);
        return ItemToolkit.findWorkDoneItems(workTypeItems);
    }
    
    public static Collection<Item> findWorkToBeDoneItemsWithWorkType(Collection<Item> items, WorkType workType){
        Collection<Item> workTypeItems = findItemsWithWorkType(items, workType);
        return ItemToolkit.findWorkToBeDoneItems(workTypeItems);
    }
    
    public static Collection<Item> findWorkDoneItemsWithOriginType(Collection<Item> items, OriginType originType){
        Collection<Item> originTypeItems = findItemsWithOriginType(items, originType);
        return ItemToolkit.findWorkDoneItems(originTypeItems);
    }
    
    public static Collection<Item> findWorkToBeDoneItemsWithOriginType(Collection<Item> items, OriginType originType){
        Collection<Item> originTypeItems = findItemsWithOriginType(items, originType);
        return ItemToolkit.findWorkToBeDoneItems(originTypeItems);
    }

    public static Collection<Item> findItemsWithOriginType(Collection<Item> items, OriginType originType){
        Collection<Item> itemsFound = new ArrayList<>();
        for(Item item : items){
            if(item.getOriginType().equals(originType)){
                itemsFound.add(item);
            }
        }
        return itemsFound;
    }
    
    public static Collection<Sprint> findSprintsUsingItems(Collection<Item> items, ScrinchEnvToolkit toolkit){
        Collection<Sprint> sprints = null;
        for(Item item:items){
            for(Sprint sprint:toolkit.getSprintFactory().getSprintList()){
                if(sprint.hasItem(item.getKey())){
                    if(sprints==null){
                        sprints = new HashSet<Sprint>();
                    }
                    sprints.add(sprint);
                }
            }
        }
        return sprints;
    }
    
    public static int getActiveItemEvaluationCount(Collection<Item> items){
        int itemEvaluationCount=0;
        for(Item item : items){
            if(item.isActive()){
                itemEvaluationCount+=item.getEvaluation().intValue();
            }
        }
        return itemEvaluationCount;
    }

    public static int getWorkDoneItemEvaluationCount(Collection<Item> items){
        return ItemToolkit.getTotalFiboPoints(ItemToolkit.findWorkDoneItems(items));
    }
    
    public static int getWorkToBeDoneItemEvaluationCount(Collection<Item> items){
        return ItemToolkit.getTotalFiboPoints(ItemToolkit.findWorkToBeDoneItems(items));
    }
}
