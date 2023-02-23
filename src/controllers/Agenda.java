package controllers;

import controllers.dialogs.AddContactDialog;
import controllers.dialogs.EditContactDialog;
import models.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Agenda extends JFrame {
    private final GridBagConstraints constraints;
    DefaultListModel<Contact> model;
    private JList<Contact> contactList;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JPanel infoPanel;
    private JLabel nameLabel;
    private JLabel nameValueLabel;
    private JLabel phoneLabel;
    private JLabel phoneValueLabel;
    private JLabel emailLabel;
    private JLabel emailValueLabel;

    public Agenda() {
        super("Epic Agenda");
        setLayout(new BorderLayout());
        constraints = new GridBagConstraints();
        createContactsJList();
        createAddButton();
        createEditButton();
        createRemoveButton();
        createInfoPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(contactList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        private void createContactsJList() {
            model = new DefaultListModel<>();
            model.addElement(new Contact("John Smith", "555-1234", "jsmith@email.com"));
            model.addElement(new Contact("Jane Doe", "555-5678", "jdoe@email.com"));
            model.addElement(new Contact("Bob Johnson", "555-2468", "bjohnson@email.com"));

            contactList = new JList<>(model);
            contactList.setCellRenderer(new ContactRenderer());
        }

        private void createAddButton() {
            addButton = new JButton("Add");
            addButton.addActionListener(e -> {
                new AddContactDialog(Agenda.this, model).setVisible(true);
            });
        }
        private void createEditButton() {
            addButton = new JButton("Edit");
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
        private void createRemoveButton() {
            removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                if (contactList.getSelectedValue() == null)
                    return;

                int option = JOptionPane.showConfirmDialog(Agenda.this,
                        "Are you sure you want to delete this contact?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    model.removeElement(contactList.getSelectedValue());
                }
            });
        }
    }
