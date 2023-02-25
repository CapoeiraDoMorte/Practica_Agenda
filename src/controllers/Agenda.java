package controllers;

import controllers.dialogs.AddContactDialog;
import controllers.dialogs.EditContactDialog;
import models.Contact;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        mainPanel.setBorder(new EmptyBorder(15,15,15,0));
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

    private void createContactsJList() {
        model = new DefaultListModel<>();
        model.addElement(new Contact("Jaime Altozano", "525-1234", "jaltoza@email.com"));
        model.addElement(new Contact("Jaime Riquelme", "515-5648", "jriquelme@email.com"));
        model.addElement(new Contact("Bob Johnson", "555-2468", "bjohnson@email.com"));

        contactList = new JList<>(model);
        contactList.setFixedCellWidth(200);
        contactList.setFixedCellHeight(100);
        contactList.setCellRenderer(new ContactRenderer());
    }

    private void createAddButton() {
        addButton = new JButton("Add");
        addButton.setForeground(Color.BLACK);
        addButton.setFont (new Font("arial",Font.BOLD,15));
        addButton.setBackground(Color.LIGHT_GRAY);
        ImageIcon edit = new ImageIcon("src/controllers/Pictures/anadir.png");
        addButton.setIcon(new ImageIcon(edit.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));
        addButton.addActionListener(e -> {
            new AddContactDialog(Agenda.this, model).setVisible(true);
        });
    }

    private void createEditButton() {
        editButton = new JButton("Edit");
        editButton.setForeground(Color.BLACK);
        editButton.setFont (new Font("arial",Font.BOLD,15));
        editButton.setBackground(Color.LIGHT_GRAY);
        ImageIcon edit = new ImageIcon("src/controllers/Pictures/edit.png");
        editButton.setIcon(new ImageIcon(edit.getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH)));
        editButton.addActionListener(e -> {
            if (contactList.getSelectedValue() == null)
                return;

            new EditContactDialog(Agenda.this, model, contactList.getSelectedValue()).setVisible(true);
        });
    }

    private void createRemoveButton() {
        removeButton = new JButton("Remove");
        removeButton.setForeground(Color.BLACK);
        removeButton.setFont (new Font("arial",Font.BOLD,15));
        removeButton.setBackground(Color.LIGHT_GRAY);
        ImageIcon edit = new ImageIcon("src/controllers/Pictures/borrar.png");
        removeButton.setIcon(new ImageIcon(edit.getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
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

    private void createInfoPanel() {
        infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 5, 5, 5);

        nameLabel = new JLabel("Name: ");
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(nameLabel, c);
        nameValueLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 0;
        infoPanel.add(nameValueLabel, c);

        phoneLabel = new JLabel("Phone: ");
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(phoneLabel, c);
        phoneValueLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 1;
        infoPanel.add(phoneValueLabel, c);

        emailLabel = new JLabel("Email: ");
        c.gridx = 0;
        c.gridy = 2;
        infoPanel.add(emailLabel, c);
        emailValueLabel = new JLabel();
        c.gridx = 1;
        c.gridy = 2;
        infoPanel.add(emailValueLabel, c);

        contactList.addListSelectionListener(e -> {
            Contact selectedContact = contactList.getSelectedValue();
            if (selectedContact != null) {
                nameValueLabel.setText(selectedContact.getName());
                phoneValueLabel.setText(selectedContact.getPhoneNumber());
                emailValueLabel.setText(selectedContact.getEmail());
            } else {
                nameValueLabel.setText("");
                phoneValueLabel.setText("");
                emailValueLabel.setText("");
            }
        });
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setSize(800, 600);
        agenda.setResizable(false);
        agenda.setVisible(true);
    }
}
