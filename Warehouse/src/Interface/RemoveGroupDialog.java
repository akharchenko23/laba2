package Interface;

import Storage.*;
import javax.swing.*;
import java.awt.*;

public class RemoveGroupDialog extends JFrame {
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public RemoveGroupDialog() {
        super("Смітник");
        this.setSize(screen.width / 8, screen.height / 8);
        this.setLocation(screen.width / 16 * 7, screen.height / 16 * 7);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        JComboBox<ProductGroup> comboBox = new JComboBox<>(WareHouseWindow.storage.getListOfProductGroups().toArray(new ProductGroup[0]));
        comboBox.setBounds(screen.height / 40 - 5, screen.height / 80 + 2, screen.width / 8 - screen.height / 20, screen.height / 40);
        this.add(comboBox);

        JButton deleteButton = getDelButton(comboBox);
        this.add(deleteButton);
    }

    private JButton getDelButton(JComboBox<ProductGroup> comboBox) {
        JButton deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(e -> {
            ProductGroup deleteGroup = (ProductGroup) comboBox.getSelectedItem();
            if (deleteGroup != null) {
                int fate = JOptionPane.showConfirmDialog(null, "Групу і весь її вміст буде видалено!");
                if (fate == JOptionPane.YES_OPTION) {
                    WareHouseWindow.storage.deleteProductGroup(deleteGroup);
                    comboBox.removeItem(deleteGroup);
                    WareHouseWindow.updateTreePanel();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Схоже що склад порожній...");
                this.dispose();
            }

        });
        deleteButton.setBounds(screen.height / 40 - 5, screen.height / 80 * 4 + 2, screen.width / 8 - screen.height / 20, screen.height / 40);
        return deleteButton;
    }
}
