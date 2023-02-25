package controllers.dialogs;

import models.Contact;

import javax.swing.*;
import java.awt.*;

public class EditContactDialog extends JDialog {
    private JLabel nameLabel;
    private JLabel telephoneLabel;
    private JLabel emailLabel;
    private JTextField nameField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JButton addButton;
    private JButton cancelButton;
    private DefaultListModel<Contact> model;
    private Contact contact;

    public EditContactDialog(JFrame parent, DefaultListModel<Contact> model, Contact contact) {
        super(parent, "Edit Contact", true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.model = model;
        this.contact = contact;

        createNameField(constraints);
        createTelephoneField(constraints);
        createEmailField(constraints);
        createEditButton();
        createCancelButton();
        createButtonPanel(constraints);

        pack();
        setLocationRelativeTo(parent);
    }

    private void createNameField(GridBagConstraints constraints) {
        nameLabel = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        add(nameLabel, constraints);

        nameField = new JTextField(20);
        nameField.setText(contact.getName());
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(nameField, constraints);
    }

    private void createTelephoneField(GridBagConstraints constraints) {
        telephoneLabel = new JLabel("Telephone:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(telephoneLabel, constraints);

        telephoneField = new JTextField(20);
        telephoneField.setText(contact.getPhoneNumber());
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(telephoneField, constraints);
    }

    private void createEmailField(GridBagConstraints constraints) {
        emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.NONE;
        add(emailLabel, constraints);

        emailField = new JTextField(20);
        emailField.setText(contact.getEmail());
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(emailField, constraints);
    }

    private void createEditButton() {
        addButton = new JButton("Edit");
        addButton.setForeground(Color.BLACK);
        addButton.setFont (new Font("arial",Font.BOLD,15));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String telephone = telephoneField.getText();
            String email = emailField.getText();

            if (name.trim().isEmpty() || telephone.trim().isEmpty() || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact newContact = new Contact(name, telephone, email);
            int index = model.indexOf(contact);
            model.removeElement(contact);
            model.insertElementAt(newContact, index);
            dispose();
        });
    }

    private void createCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setFont (new Font("arial",Font.BOLD,15));
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    private void createButtonPanel(GridBagConstraints constraints) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.NONE;
        add(buttonPanel, constraints);
    }

    public String getName() {
        return nameField.getText();
    }

    public String getTelephone() {
        return telephoneField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }
}
