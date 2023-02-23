package controllers.dialogs;

import models.Contact;

import javax.swing.*;
import java.awt.*;

public class AddContactDialog extends JDialog {
    private JLabel nameLabel;
    private JLabel telephoneLabel;
    private JLabel emailLabel;
    private JTextField nameField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JButton addButton;
    private JButton cancelButton;
    private DefaultListModel<Contact> model;

    public AddContactDialog(JFrame parent, DefaultListModel<Contact> model) {
        super(parent, "Add Contact", true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        this.model = model;

        createNameField(constraints);
        createTelephoneField(constraints);
        createEmailField(constraints);
        createAddButton();
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
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(emailField, constraints);
    }
