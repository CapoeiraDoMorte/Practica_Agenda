
package controllers;

import models.Contact;

import javax.swing.*;
import java.awt.*;
public class ContactRenderer extends JLabel implements ListCellRenderer<Contact> {
    public ContactRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Contact> list, Contact value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}