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
    }