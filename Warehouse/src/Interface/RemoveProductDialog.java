package Interface;

import Storage.Product;
import Storage.ProductGroup;

import javax.swing.*;
import java.awt.*;

public class RemoveProductDialog extends JFrame {
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private static JComboBox<Product> prodBox;
    private static JComboBox<ProductGroup> groupBox;
    private static ProductGroup selectedGroup;

    public RemoveProductDialog() {
        super("Смітник");
        this.setSize(screen.width / 8, screen.height / 16 * 3);
        this.setLocation(screen.width / 16 * 7, screen.height / 16 * 7);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);


        init();
    }

    private void init() {
        createGroupBox();
        JButton deleteButton = getDelButton();
        this.add(deleteButton);
    }

    private void createGroupBox() {
        groupBox = new JComboBox<>(WareHouseWindow.storage.getListOfProductGroups().toArray(new ProductGroup[0]));
        selectedGroup = (ProductGroup) groupBox.getSelectedItem();
        createProdBox();
        groupBox.setBounds(screen.height / 40 - 5, screen.height / 80 + 2, screen.width / 8 - screen.height / 20, screen.height / 40);
        groupBox.addItemListener(e -> {
            selectedGroup = (ProductGroup) groupBox.getSelectedItem();
            this.remove(prodBox);
            createProdBox();
            this.revalidate();
            this.repaint();
        });
        this.add(groupBox);
    }

    private void createProdBox() {
        prodBox = new JComboBox<>(selectedGroup.getListOfProducts().toArray(new Product[0]));
        prodBox.setBounds(screen.height / 40 - 5, screen.height / 20 + 2, screen.width / 8 - screen.height / 20, screen.height / 40);
        this.add(prodBox);
    }

    private JButton getDelButton() {
        JButton deleteButton = new JButton("Видалити");
        deleteButton.addActionListener(e -> {

            if (RemoveProductDialog.prodBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Ця група і так порожня!", "Нема товару", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int fate = JOptionPane.showConfirmDialog(null, "Цей товар буде видалено!");
                if (fate == JOptionPane.YES_OPTION) {
                    Product prod = (Product) RemoveProductDialog.prodBox.getSelectedItem();
                    selectedGroup.deleteProduct(prod);
                    RemoveProductDialog.prodBox.removeItem(prod);
                    WareHouseWindow.updateTreePanel();
                    WareHouseWindow.console.productBought(prod,prod.getNumber());
                }
            }


        });
        deleteButton.setBounds(screen.height / 40 - 5, screen.height / 8 - 10, screen.width / 8 - screen.height / 20, screen.height / 40);
        return deleteButton;
    }
}
