package Interface;

import Storage.Product;
import Storage.ProductGroup;

import javax.swing.*;
import java.awt.*;

public class AddProductDialog extends JFrame {

    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = screen.width / 4;
    private static final int height = screen.height / 4;

    private static final Font defaultFont = new Font("Arial", Font.PLAIN, 18);
    private static final Font miniFont = new Font("Arial", Font.PLAIN, 14);

    public AddProductDialog() {
        super("Новий товар");
        this.setLocation(screen.width / 2 - width / 2, screen.height / 2 - height / 2);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        JLabel nameWarning = new JLabel("Заповніть бланк нового товару", JLabel.CENTER);
        nameWarning.setFont(defaultFont);
        nameWarning.setBounds(10, 10, width - 30, 20);
        this.add(nameWarning);

        JLabel name = new JLabel("Назва");
        name.setFont(defaultFont);
        name.setBounds(10, 40, 100, 20);
        this.add(name);

        JTextField nameField = new JTextField();
        nameField.setFont(miniFont);
        nameField.setBounds(120, 40, width - 150, 20);
        this.add(nameField);

        JLabel prod = new JLabel("Виробник");
        prod.setFont(defaultFont);
        prod.setBounds(10, 70, 100, 20);
        this.add(prod);

        JTextField prodField = new JTextField();
        prodField.setFont(miniFont);
        prodField.setBounds(120, 70, width - 150, 20);
        this.add(prodField);

        JLabel desc = new JLabel("Опис");
        desc.setFont(defaultFont);
        desc.setBounds(10, 100, 100, 20);
        this.add(desc);

        JTextField descField = new JTextField();
        descField.setFont(miniFont);
        descField.setBounds(120, 100, width - 150, 20);
        this.add(descField);

        JLabel group = new JLabel("Група");
        group.setFont(defaultFont);
        group.setBounds(10, 130, 100, 20);
        this.add(group);

        JComboBox<ProductGroup> groupField = new JComboBox<>(WareHouseWindow.storage.getListOfProductGroups().toArray(new ProductGroup[0]));
        groupField.setFont(defaultFont);
        groupField.setBounds(120, 125, width - 150, 30);
        this.add(groupField);

        JLabel number = new JLabel("Кількість");
        number.setFont(defaultFont);
        number.setBounds(10, 160, 100, 20);
        this.add(number);

        JSpinner numberField = new JSpinner();
        numberField.setFont(defaultFont);
        numberField.setModel(new SpinnerNumberModel(1, 0, 1000000, 1));

        JComponent editor1 = numberField.getEditor();
        JSpinner.DefaultEditor spinnerEditor1 = (JSpinner.DefaultEditor) editor1;
        spinnerEditor1.getTextField().setHorizontalAlignment(JTextField.LEFT);

        numberField.setBounds(120, 160, width / 2 - 140, 20);
        this.add(numberField);

        JLabel value = new JLabel("Ціна");
        value.setFont(defaultFont);
        value.setBounds(width / 2 + 40, 160, 100, 20);
        this.add(value);

        JSpinner valueField = new JSpinner();
        valueField.setFont(defaultFont);
        valueField.setModel(new SpinnerNumberModel(1, 0, 1000000, 1));

        JComponent editor2 = valueField.getEditor();
        JSpinner.DefaultEditor spinnerEditor2 = (JSpinner.DefaultEditor) editor2;
        spinnerEditor2.getTextField().setHorizontalAlignment(JTextField.LEFT);

        valueField.setBounds(width / 2 + 110, 160, width / 2 - 140, 20);
        this.add(valueField);

        JButton cancelButton = new JButton("Скасувати");
        cancelButton.setFont(defaultFont);
        cancelButton.addActionListener(e -> this.dispose());
        cancelButton.setBounds(15, 210, 200, 30);
        this.add(cancelButton);

        JButton okButton = new JButton("Створити");
        okButton.setFont(defaultFont);
        okButton.addActionListener(e -> {
            String productName = nameField.getText().trim();
            String productDesc = descField.getText().trim();
            String productProd = prodField.getText().trim();
            ProductGroup productGroup = (ProductGroup) groupField.getSelectedItem();
            int productNumber = (int) numberField.getValue();
            int productValue = (int) valueField.getValue();

            if (!productName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+")) {
                nameWarning.setText("Назва має складатись лише з літер, цифр, пробілів, _ і тире!");
                nameWarning.setFont(new Font("Arial", Font.PLAIN, 16));
                nameWarning.setForeground(Color.RED);
            } else {
                nameWarning.setText("Заповніть бланк нового товару");
                nameWarning.setFont(defaultFont);
                nameWarning.setForeground(Color.black);
            }

            if (productDesc.isEmpty()) {
                desc.setForeground(Color.RED);
            } else {
                desc.setForeground(Color.black);
            }

            if (productProd.isEmpty()) {
                prod.setForeground(Color.RED);
            } else {
                prod.setForeground(Color.black);
            }

            if (WareHouseWindow.storage.productExistsInStorageByName(productName)) {
                nameWarning.setText("Товар з такою назвою вже існує!");
                nameWarning.setForeground(Color.RED);
            }

            if (!productDesc.isEmpty() && !productProd.isEmpty() && !WareHouseWindow.storage.productExistsInStorageByName(productName) && productName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+")) {
                if (productGroup != null) {
                    Product product = new Product(productName, productDesc, productProd, productNumber, productValue);
                    productGroup.addProduct(product);
                    WareHouseWindow.updateTreePanel();
                    WareHouseWindow.console.productAdded(product, productNumber);
                    this.dispose();
                }
            }
        });
        okButton.setBounds(width / 2 + 10, 210, 200, 30);
        this.add(okButton);
    }
}
