package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Expense;
import model.Main;
import model.Project;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * view.ProjectExpensePanel.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectExpensePanel extends ProjectSecondaryPanelTemplate {
    private static final String addButtonName = "Add Expense";
    private static final JLabel categoryLabel = new JLabel("Category");
    private static final JLabel nameLabel = new JLabel("Name");
    private static final JLabel numberLabel = new JLabel("#");
    private static final JLabel priceLabel = new JLabel("Price");
    private static final JLabel spacerLabel = new JLabel("                       ");
    private static final JLabel spacerLabelTwo = new JLabel("                       ");
    private LinkedList<Expense> projectExpenses;
    private HashMap<ProjectEntryRow, Expense> expenseTableEntries = new HashMap<>();
    private int rowCount = 0;
    private Project project;
    private ProjectExpenseScreen overviewScreen;

    public ProjectExpensePanel(Project project, ProjectExpenseScreen overviewScreen) {
        super(addButtonName);
        this.project = project;
        this.overviewScreen = overviewScreen;
        projectExpenses = project.getExpenses();
        nameLabel.setFont(Main.TABLE_FONT);
        nameLabel.setForeground(Main.TEXT);
        categoryLabel.setFont(Main.TABLE_FONT);
        categoryLabel.setForeground(Main.TEXT);
        numberLabel.setFont(Main.TABLE_FONT);
        numberLabel.setForeground(Main.TEXT);
        priceLabel.setFont(Main.TABLE_FONT);
        priceLabel.setForeground(Main.TEXT);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 8;
        c.weighty = 1;
        contentPanel.add(nameLabel, c);
        c.gridx++;
        contentPanel.add(categoryLabel, c);
        c.gridx++;
        c.weightx = 2;
        contentPanel.add(numberLabel, c);
        c.gridx++;
        c.weightx = 6;
        contentPanel.add(priceLabel, c);
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
                addRow(new ProjectEntryRow(4, rowCount, 2, 3));
            }
        });
        load();
    }

    private void load() {
        for (int i = 0; i < projectExpenses.size(); i++) {
            ProjectEntryRow entryRow = new ProjectEntryRow(4, rowCount, 2, 3);
            addRow(entryRow);
            Expense expense = projectExpenses.get(i);
            expenseTableEntries.put(entryRow, expense);
            entryRow.textFields.get(0).setText(expense.getName());
            entryRow.textFields.get(1).setText(expense.getCategory());
            entryRow.textFields.get(2).setText(String.valueOf(expense.getQuantity()));
            entryRow.textFields.get(3).setText(String.valueOf(expense.getPrice()));
            entryRow.updateButton.setVisible(false);
        }
    }

    private void addRow(ProjectEntryRow entryRow) {
        CustomTextField nameTextField = entryRow.textFields.get(0);
        CustomTextField categoryTextField = entryRow.textFields.get(1);
        CustomTextField numberTextField = entryRow.textFields.get(2);
        CustomTextField priceTextField = entryRow.textFields.get(3);
        expenseTableEntries.put(entryRow, null);
        rowCount++;
        c.gridx = 0;
        c.gridy++;
        c.weightx = 8;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nameTextField, c);
        c.gridx++;
        contentPanel.add(categoryTextField, c);
        c.gridx++;
        c.weightx = 2;
        contentPanel.add(numberTextField, c);
        c.gridx++;
        c.weightx = 6;
        contentPanel.add(priceTextField, c);
        c.gridx++;
        c.weightx = 0;
        contentPanel.add(entryRow.deleteButton, c);
        c.gridx++;
        contentPanel.add(entryRow.updateButton, c);
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth() - ((Integer)UIManager.get("ScrollBar.width")).intValue(), 36 * (rowCount + 2)));
        contentPanel.setSize(getPreferredSize());
        // nameTextField.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Expense newExpense = updateExpense(nameTextField, categoryTextField, priceTextField, numberTextField);
        //         if (newExpense != null) {
        //             Expense currentExpense = expenseTableEntries.get(entryRow);
        //             if (currentExpense != null) {
        //                 expenses.set(entryRow.index, newExpense);
        //             }
        //             else {
        //                 expenses.add(entryRow.index, newExpense);
        //             }
        //         }
        //         entryRow.updateButton.setVisible(false);
        //     }
        // });
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
        // categoryTextField.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Expense newExpense = updateExpense(nameTextField, categoryTextField, priceTextField, numberTextField);
        //         if (newExpense != null) {
        //             Expense currentExpense = expenseTableEntries.get(entryRow);
        //             if (currentExpense != null) {
        //                 expenses.set(entryRow.index, newExpense);
        //             }
        //             else {
        //                 expenses.add(entryRow.index, newExpense);
        //             }
        //         }
        //         entryRow.updateButton.setVisible(false);
        //     }
        // });
        categoryTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                categoryTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                categoryTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        // numberTextField.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Expense newExpense = updateExpense(nameTextField, categoryTextField, priceTextField, numberTextField);
        //         if (newExpense != null) {
        //             Expense currentExpense = expenseTableEntries.get(entryRow);
        //             if (currentExpense != null) {
        //                 expenses.set(entryRow.index, newExpense);
        //             }
        //             else {
        //                 expenses.add(entryRow.index, newExpense);
        //             }
        //         }
        //         entryRow.updateButton.setVisible(false);
        //     }
        // });
        numberTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                numberTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                numberTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        // priceTextField.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Expense newExpense = updateExpense(nameTextField, categoryTextField, priceTextField, numberTextField);
        //         if (newExpense != null) {
        //             Expense currentExpense = expenseTableEntries.get(entryRow);
        //             if (currentExpense != null) {
        //                 expenses.set(entryRow.index, newExpense);
        //             }
        //             else {
        //                 expenses.add(entryRow.index, newExpense);
        //             }
        //         }
        //         entryRow.updateButton.setVisible(false);
        //     }
        // });
        priceTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                priceTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                entryRow.updateButton.setVisible(true);
                priceTextField.setBackground(Main.TEXT_BOX_BACKGROUND);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        entryRow.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expense newExpense = updateExpense(nameTextField, categoryTextField, priceTextField, numberTextField);
                if (newExpense != null) {
                    project.updateExpense(newExpense);
                    expenseTableEntries.put(entryRow, newExpense);
                    overviewScreen.refreshBudget();
                }
                entryRow.updateButton.setVisible(false);
                System.out.println("Expenses stored in project: ");
                for (int i = 0; i < projectExpenses.size(); i++) {
                    System.out.println(projectExpenses.get(i).toString());
                }
                System.out.println(project.getTotalCost());
            }
        });
        entryRow.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expense currentExpense = expenseTableEntries.get(entryRow);
                rowCount--;
                if (currentExpense != null) {
                    project.removeExpense(currentExpense);
                }
                expenseTableEntries.remove(entryRow);
                contentPanel.remove(nameTextField);
                contentPanel.remove(categoryTextField);
                contentPanel.remove(numberTextField);
                contentPanel.remove(priceTextField);
                contentPanel.remove(entryRow.updateButton);
                contentPanel.remove(entryRow.deleteButton);
                contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), 36 * (rowCount + 2)));
                contentPanel.setSize(getPreferredSize());
            }
        });
    }

    private Expense updateExpense(CustomTextField nameField, CustomTextField categoryField, CustomTextField priceField, CustomTextField quantityField) {
        priceField.fireAction();
        quantityField.fireAction();
        String name = nameField.getText();
        String category = categoryField.getText();
        String priceString = priceField.getText();
        String quantityString = quantityField.getText();
        if (!(name.isEmpty() || category.isEmpty() || priceString.isEmpty() || quantityString.isEmpty())) {
            int price = ((Number) priceField.getValue()).intValue();
            int quantity = ((Number) quantityField.getValue()).intValue();
            return new Expense(name, category, price, quantity);
        }
        else {
            if (name.isEmpty()) {
                nameField.setBackground(Main.TEXT_ERROR);
            }
            if (category.isEmpty()) {
                categoryField.setBackground(Main.TEXT_ERROR);
            }
            if (priceString.isEmpty()) {
                priceField.setBackground(Main.TEXT_ERROR);
            }
            if (quantityString.isEmpty()) {
                quantityField.setBackground(Main.TEXT_ERROR);
            }
            return null;
        }
    }

    // private void updateExpenses(){
    //     ArrayList<Expense> expenses = new ArrayList<>();
    //     for (int i = 0; i < expenseTextFields.size(); i++) {
    //         ArrayList<JTextField> rowTextFields = expenseTextFields.get(i);
    //         if (!rowTextFields.get(0).getText().isEmpty() && !rowTextFields.get(1).getText().isEmpty() && !rowTextFields.get(2).getText().isEmpty() && !rowTextFields.get(3).getText().isEmpty()) {
    //             Expense expense = new Expense(rowTextFields.get(0).getText(), rowTextFields.get(1).getText(), Integer.parseInt(rowTextFields.get(2).getText()), Integer.parseInt(rowTextFields.get(3).getText()));
    //             expenses.add(expense);
    //         }
    //     }
    //     project.setExpenses(expenses);
    //     overviewScreen.refreshBudget();
    //     Main.BASE_FRAME.repaint();
    // }

}
