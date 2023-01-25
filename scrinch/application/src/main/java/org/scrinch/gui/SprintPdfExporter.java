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

import org.scrinch.model.Item;
import org.scrinch.model.Sprint;

public class SprintPdfExporter extends ItemSetPdfExporter {
    
    private boolean onlyItemsToBeDone;
    private Sprint sprint;
   
    public SprintPdfExporter(ItemTablePanel panel){
        super(panel);
    }
    
    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    protected Sprint getSprint() {
        return sprint;
    }
    
    public void setExportOnlyItemsToBeDone(boolean onlyItemsToBeDone){
        this.onlyItemsToBeDone = onlyItemsToBeDone;
    }
    
    @Override
    protected boolean getItemPrintValidity(Item item){
        return (! (onlyItemsToBeDone && !item.getStatus(sprint).isWorkToBeDone()));
    }
    
    @Override
    protected String getPdfHeaderTitle(){
        return sprint.getTitle() 
                + " (" + ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getStartDate()) 
                + " to " + ScrinchGuiToolkit.getDefaultDayFormat().format(sprint.getEndDate())+")";
    }
}
