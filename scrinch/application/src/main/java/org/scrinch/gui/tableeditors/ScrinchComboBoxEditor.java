package org.scrinch.gui.tableeditors;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

public class ScrinchComboBoxEditor extends ComboBoxCellEditor{

    private JComboBox comboBox;

    public JComboBox getComboBox() {
        return comboBox;
    }
    
    public ScrinchComboBoxEditor(JComboBox comboBox) {
        super(comboBox);
        this.comboBox = comboBox;
        this.comboBox.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    ScrinchComboBoxEditor.super.fireEditingStopped();
                    ScrinchComboBoxEditor.this.comboBox.setPopupVisible(false);
                    ScrinchComboBoxEditor.this.comboBox.transferFocus();
                }else if(!ScrinchComboBoxEditor.this.comboBox.isPopupVisible()){
                    ScrinchComboBoxEditor.this.comboBox.showPopup();
                }
            }
        });
        this.comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                ScrinchComboBoxEditor.this.comboBox.showPopup();
            }
        });
        comboBox.setMaximumRowCount(10);
    }
}

