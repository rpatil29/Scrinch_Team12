package org.scrinch.gui;

import org.scrinch.model.ProjectItemSet;
import org.scrinch.model.ScrinchEnvToolkit;

public class SprintProjectItemSetPickerDialog extends ProjectItemSetPickerDialog{
    
    private ProjectItemSet pickedProjectItemSet;
    
    public SprintProjectItemSetPickerDialog(java.awt.Frame parent, ScrinchEnvToolkit toolkit) {
        super(parent, toolkit);
        lbItems.setText("NEW TASK");
    }
    
    @Override
    protected void itemSetPickerValidation() {
        this.pickedProjectItemSet = getSelectedProjectItemSet();
        this.dispose();
    }
    
    public ProjectItemSet getPickedProjectItemSet(){
        return pickedProjectItemSet;
    }
}
