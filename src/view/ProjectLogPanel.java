package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Log;
import model.Main;
import model.Project;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectLogPanel.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectLogPanel extends ProjectSecondaryPanelTemplate {
    private static final String addButtonName = "Add Log";
    private static final JLabel nameLabel = new JLabel("Name");
    private static final JLabel dateLabel = new JLabel("Date");
    private static final JLabel descriptionLabel = new JLabel("Description");
    private static final JLabel spacerLabel = new JLabel("                       ");
    private static final JLabel spacerLabelTwo = new JLabel("                       ");
    private LinkedList<Log> projectLogs;
    private HashMap<ProjectEntryRow, Log> logTableEntries = new HashMap<>();
    private int rowCount = 0;
    private Project project;

    public ProjectLogPanel(Project project) {
        super(addButtonName);
        this.project = project;
        projectLogs = project.getLogs();
        nameLabel.setFont(Main.TABLE_FONT);
        nameLabel.setForeground(Main.TEXT);
        descriptionLabel.setFont(Main.TABLE_FONT);
        descriptionLabel.setForeground(Main.TEXT);
        dateLabel.setFont(Main.TABLE_FONT);
        dateLabel.setForeground(Main.TEXT);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 2;
        c.weighty = 1;
        contentPanel.add(nameLabel, c);
        c.gridx++;
        contentPanel.add(dateLabel, c);
        c.gridx++;
        c.weightx = 8;
        contentPanel.add(descriptionLabel, c);
        c.gridx++;
        c.weightx = 0;
        spacerLabel.setFont(Main.TABLE_FONT);
        contentPanel.add(spacerLabel, c);
        c.gridx++;
        spacerLabelTwo.setFont(Main.TABLE_FONT);
        contentPanel.add(spacerLabelTwo, c);
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 10;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRow(new ProjectEntryRow(3, 1));
            }
        });
        load();
    }

    private void load() {
        for (int i = 0; i < projectLogs.size(); i++) {
            Log log = projectLogs.get(i);
            ProjectEntryRow entryRow = new ProjectEntryRow(3, 1);
            addRow(entryRow);
            entryRow.textFields.get(0).setText(log.getName());
            entryRow.textFields.get(1).setText(new SimpleDateFormat("MM/dd/yyyy").format(log.getDate()));
            String description = log.getDescription();
            if (description != null && !description.isEmpty()) {
                entryRow.textFields.get(2).setText(description);
            }
            entryRow.updateButton.setVisible(false);
        }
    }

    private void addRow(ProjectEntryRow entryRow) {
        CustomTextField nameTextField = entryRow.textFields.get(0);
        CustomTextField dateTextField = entryRow.textFields.get(1);
        CustomTextField descriptionTextField = entryRow.textFields.get(2);
        logTableEntries.put(entryRow, null);
        rowCount++;
        c.gridx = 0;
        c.gridy++;
        c.weightx = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nameTextField, c);
        c.gridx++;
        contentPanel.add(dateTextField, c);
        c.gridx++;
        c.weightx = 8;
        contentPanel.add(descriptionTextField, c);
        c.gridx++;
        c.weightx = 0;
        contentPanel.add(entryRow.deleteButton, c);
        c.gridx++;
        entryRow.updateButton.setText("Update");
        contentPanel.add(entryRow.updateButton, c);
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth() - ((Integer)UIManager.get("ScrollBar.width")).intValue(), 36 * (rowCount + 2)));
        contentPanel.setSize(getPreferredSize());
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                nameTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        dateTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                dateTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                dateTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        descriptionTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                dateTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                dateTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        entryRow.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log newLog = updateLog(nameTextField, dateTextField, descriptionTextField);
                if (newLog != null) {
                    if (logTableEntries.get(entryRow) != null) {
                        project.removeLog(logTableEntries.get(entryRow));
                    }
                    project.addLog(newLog);
                    logTableEntries.put(entryRow, newLog);
                }
                entryRow.updateButton.setVisible(false);
            }
        });
        entryRow.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowCount--;
                if (logTableEntries.get(entryRow) != null) {
                    project.removeLog(logTableEntries.get(entryRow));
                }
                contentPanel.remove(nameTextField);
                contentPanel.remove(dateTextField);
                contentPanel.remove(descriptionTextField);
                contentPanel.remove(entryRow.updateButton);
                contentPanel.remove(entryRow.deleteButton);
                contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), 36 * (rowCount + 2)));
                contentPanel.setSize(getPreferredSize());
            }
        });
    }

    private Log updateLog(CustomTextField nameTextField, CustomTextField dateTextField, CustomTextField descriptionTextField) {
        boolean failure = false;
        dateTextField.fireAction();
        String name = nameTextField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        try {
            date = sdf.parse(dateTextField.getText());
        }
        catch (Exception e) {
            dateTextField.setBackground(Main.TEXT_ERROR);
            failure = true;
        }
        if (!dateTextField.getText().equals(sdf.format(date))) {
            failure = true;
            dateTextField.setBackground(Main.TEXT_ERROR);
        }
        if (!name.isEmpty()) {
            if (!failure) {
                Log log = new Log(date, name);
                String description = descriptionTextField.getText();
                if (!description.isEmpty()) {
                    log.setDescription(description);
                }
                return log;
            }
            else {
                return null;
            }
        }
        else {
            nameTextField.setBackground(Main.TEXT_ERROR);
            return null;
        }
    }

}
