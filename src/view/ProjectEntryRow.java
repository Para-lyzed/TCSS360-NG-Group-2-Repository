package view;

import java.text.NumberFormat;
import java.util.ArrayList;

import model.Main;

public class ProjectEntryRow {
    public int index;
    public ArrayList<CustomTextField> textFields = new ArrayList<>();
    public CustomButton updateButton = new CustomButton("Update", Main.TABLE_FONT);
    public CustomButton deleteButton = new CustomButton("Delete", Main.TABLE_FONT);

    ProjectEntryRow(int columns, int index) {
        this.index = index;
        for (int i = 0; i < columns; i++) {
            textFields.add(new CustomTextField(Main.TABLE_FONT));
        }
        updateButton.setVisible(false);
    }

    ProjectEntryRow(int columns, int index, int quantityPosition, int pricePosition) {
        this.index = index;
        for (int i = 0; i < columns; i++) {
            if (i == quantityPosition || i == pricePosition) {
                textFields.add(new CustomTextField(NumberFormat.getIntegerInstance(), Main.TABLE_FONT));
            }
            else {                
                textFields.add(new CustomTextField(Main.TABLE_FONT));
            }
        }
        updateButton.setVisible(false);
    }
}
