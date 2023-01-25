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

import java.awt.Container;
import javax.swing.JOptionPane;
import org.scrinch.model.Item;
import org.scrinch.model.ItemSet;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class SprintPanel extends ItemTablePanel implements Sprint.Listener {

    private SprintDetailsPanel sprintDetailsPanel;
    
    public SprintPanel(SprintsMainPanel sprintsMainPanel, ScrinchEnvToolkit toolkit) {
        super(toolkit);
        sprintDetailsPanel = new SprintDetailsPanel(this, sprintsMainPanel, toolkit);
        removeTitlePanel();
        this.moveItemButton.setVisible(false);
        itemSetMainPanel.add(sprintDetailsPanel);
        this.pdfExporter = new SprintPdfExporter(this);
    }
    
    @Override
    public void releaseAllResources(){
        super.releaseAllResources();
        if(sprintDetailsPanel!=null){
            sprintDetailsPanel.releaseAllResources();
        }
    }
    
    @Override
    protected Item doCreateNewItem(){
        this.unselectAll();
        SprintProjectItemSetPickerDialog picker = new SprintProjectItemSetPickerDialog(getFrameForThis(), toolkit);
        showDialog(picker);
        ProjectItemSet projectItemSet = picker.getPickedProjectItemSet();
        Item item = null;
        if(projectItemSet!=null){
            item = super.doCreateNewItem();
            projectItemSet.addItem(item);
            this.refreshTable();
        }
        return item;
    }

    public void realVelocityChanged(Sprint sprint){
    }

    public void datesChanged(Sprint sprint){
    }

    public void workChanged(Sprint sprint){
    }

    public void itemsChanged(Sprint sprint){
        setItemSet(sprint);
    }

    protected void setExportOnlyWorkToBeDone(boolean exportOnlyWorkToBeDone) {
        ((SprintPdfExporter)pdfExporter).setExportOnlyItemsToBeDone(exportOnlyWorkToBeDone);
    }
    
    @Override
    protected void exportItemSetToPdf(){
        PrintSprintDialog dialog = new PrintSprintDialog(SprintPanel.this, true);
        dialog.setVisible(true);
        if(dialog.getReturnStatus()== PrintSprintDialog.RET_OK){
            super.exportItemSetToPdf();
        }
    }

    @Override
    public void setItemSet(ItemSet itemSet){
        Sprint sprint = (Sprint)itemSet;
        super.setItemSet(sprint);
        sprintDetailsPanel.setSprint(sprint);
        ((SprintPdfExporter)pdfExporter).setSprint((Sprint)itemSet);
//        sprint.addListener(this);
    }

    @Override
    protected void removeWholeItemSet(){
        if(JOptionPane.showConfirmDialog(this,
                                         "Are you sure you want\n"
                                         + "to remove this sprint : " + getItemSet().getTitle() + " ?",
                                         "Sprint removing requested",
                                         JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION ){
            toolkit.getSprintFactory().dispose((Sprint) getItemSet());
            Container parent = this.getParent();
            parent.remove(this);
            parent.repaint();
        }
    }

}
