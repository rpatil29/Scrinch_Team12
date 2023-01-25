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
import java.util.Collection;
import javax.swing.JOptionPane;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ProjectItemSetFactory;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Sprint;

public class ProjectItemSetPanel extends ItemTablePanel{

    public ProjectItemSetPanel(ScrinchEnvToolkit toolkit) {
        super(toolkit);
    }

    @Override
    protected void removeWholeItemSet(){
        Collection<Sprint> sprints = ItemToolkit.findSprintsUsingItems(getItemSet().getItemList(), toolkit);
        if(sprints!=null){
            JOptionPane.showMessageDialog(this,
                                          "You can't delete the " + getItemSet().getTitle() + " set\n"
                                          + "Sprints using items : " + sprints,
                                          "Found " + sprints.size() + " sprint" + (sprints.size()>1?"s":"")
                                          + " using items from this set.",
                                          JOptionPane.OK_OPTION);
        }else{
            if(JOptionPane.showConfirmDialog(this,  "Are you sure you want\n"
                                                    + "to remove the " + getItemSet().getTitle() + " set?",
                                                    "Set removing requested", JOptionPane.WARNING_MESSAGE)==JOptionPane.OK_OPTION){

                ProjectItemSet projectItemSet = (ProjectItemSet)getItemSet();
                projectItemSet.getProject().removeItemSet(projectItemSet);
                projectItemSet.setProject(null);
                ProjectItemSetFactory.getInstance().dispose(projectItemSet);
                Container parent = this.getParent();
                parent.remove(this);
                parent.repaint();
            }
        }
    }
}
