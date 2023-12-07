import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * TCSS 360B
 * Team MVP - Deliverable 3
 * ProjectExpensePanel.java
 * 
 * @author Nathan Grimsey
 *
 */
public class ProjectExpensePanel extends ProjectOverviewPanel {
    private static final String addButtonName = "Add Expense";
    private static final JLabel categoryLabel = new JLabel("Category");
    private static final JLabel nameLabel = new JLabel("Name");
    private static final JLabel numberLabel = new JLabel("#");
    private static final JLabel priceLabel = new JLabel("Price");
    private ArrayList<ArrayList<JTextField>> expenseTextFields;
    private int rowCount = 1;
    private boolean expenseAdded = false;
    private Project project;
    private ProjectOverviewScreen overviewScreen;

    public ProjectExpensePanel(Project project, ProjectOverviewScreen overviewScreen) {
        super(addButtonName);
        this.project = project;
        this.overviewScreen = overviewScreen;
        nameLabel.setFont(Main.TABLE_FONT);
        categoryLabel.setFont(Main.TABLE_FONT);
        numberLabel.setFont(Main.TABLE_FONT);
        priceLabel.setFont(Main.TABLE_FONT);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 4;
        contentPanel.add(nameLabel, c);
        c.gridx++;
        contentPanel.add(categoryLabel, c);
        c.gridx++;
        c.weightx = 1;
        contentPanel.add(numberLabel, c);
        c.gridx++;
        c.weightx = 3;
        contentPanel.add(priceLabel, c);
        expenseTextFields = new ArrayList<>();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!expenseAdded) {
                    c.gridx = 1;
                    c.gridy = 1;
                    c.weightx = 1;
                    add(updateButton, c);
                    overviewScreen.repaint();
                    c.gridx = 0;
                    c.gridy = 1;
                    expenseAdded = true;
                }
                addRow();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExpenses();
            }
        });
    }

    private void addRow() {
        rowCount++;
        JTextField nameTextField = new JTextField();
        JTextField categoryTextField = new JTextField();
        JTextField numberTextField = new JTextField();
        JTextField priceTextField = new JTextField();
        nameTextField.setFont(Main.TABLE_FONT);
        categoryTextField.setFont(Main.TABLE_FONT);
        numberTextField.setFont(Main.TABLE_FONT);
        priceTextField.setFont(Main.TABLE_FONT);
        ArrayList<JTextField> rowTextFields = new ArrayList<>();
        rowTextFields.add(nameTextField);
        rowTextFields.add(categoryTextField);
        rowTextFields.add(numberTextField);
        rowTextFields.add(priceTextField);
        expenseTextFields.add(rowTextFields);
        c.gridx = 0;
        c.gridy++;
        c.weightx = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nameTextField, c);
        c.gridx++;
        contentPanel.add(categoryTextField, c);
        c.gridx++;
        c.weightx = 1;
        contentPanel.add(numberTextField, c);
        c.gridx++;
        c.weightx = 3;
        contentPanel.add(priceTextField, c);
        contentPanel.setPreferredSize(new Dimension(contentScrollPane.getWidth(), 36 * (rowCount + 1)));
        contentPanel.setSize(getPreferredSize());
    }

    private void updateExpenses(){
        ArrayList<Expense> expenses = new ArrayList<>();
        for (int i = 0; i < expenseTextFields.size(); i++) {
            ArrayList<JTextField> rowTextFields = expenseTextFields.get(i);
            if (!rowTextFields.get(0).getText().isEmpty() && !rowTextFields.get(1).getText().isEmpty() && !rowTextFields.get(2).getText().isEmpty() && !rowTextFields.get(3).getText().isEmpty()) {
                Expense expense = new Expense(rowTextFields.get(0).getText(), rowTextFields.get(1).getText(), Integer.parseInt(rowTextFields.get(2).getText()), Integer.parseInt(rowTextFields.get(3).getText()));
                expenses.add(expense);
            }
        }
        project.setExpenses(expenses);
        overviewScreen.refreshBudget();
        Main.BASE_FRAME.repaint();
    }

}
