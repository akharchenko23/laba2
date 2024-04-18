package Interface;

import Storage.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashSet;
import java.util.Objects;

/**
 * Creates a window with full functionality of a warehouse
 */
public class WareHouseWindow extends JFrame {
    //Main window properties
    private static final String title = "Склад";
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private static final JPanel panel = new JPanel();
    static JPanel treePanel = new JPanel();
    private static final JPanel infoPanel = new JPanel();
    private static final JPanel consolePanel = new JPanel();
    private static final JMenuBar menuBar = new JMenuBar();

    private static final LineBorder defaultBorder = new LineBorder(Color.black);
    private static final GridBagConstraints c = new GridBagConstraints();

    static Storage storage;
    static JConsole console = new JConsole();

    public WareHouseWindow() {
        super(title);
        this.setLocation(screen.width / 4, screen.height / 4);
        this.setSize(screen.width / 2, screen.height / 2);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        getStorage();

        //if there is any unsaved progress asks the user to save it
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int whatToDo = JOptionPane.showOptionDialog(null, "Зберегти зміни?", "Незбережені зміни", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Так", "Ні", "Скасувати"}, "Скасувати");
                if (whatToDo == JOptionPane.YES_OPTION) {
                    save();
                    System.exit(0);
                }
                if (whatToDo == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });


        this.setLayout(new BorderLayout()); //Layout of the window
        c.fill = GridBagConstraints.BOTH;

        this.add(menuBar, BorderLayout.NORTH);

        panel.setLayout(new GridBagLayout()); //Main Layout of the program!!
        this.add(panel, BorderLayout.CENTER);

        init();
    }

    private void getStorage() {
        File saveFile = new File("warehouse.save");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            Object obj = ois.readObject();
            if (obj.getClass() == Storage.class) {
                storage = (Storage) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getClass());
            System.out.println("Виникла проблема під час читання файлу збереження! Вийдіть з програми не зберігаючись, якщо не хочете пошкодити файл збереження.");
            storage = new Storage();
        }
    }

    private void init() {
        createMenu();

        createTreePanel("*");
        createInfoPanel();
        createConsolePanel();
    }

    private void createConsolePanel() {
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.3;
        consolePanel.setBorder(defaultBorder);
        panel.add(consolePanel, c);

        consolePanel.setLayout(new BorderLayout());
        consolePanel.add(console, BorderLayout.CENTER);
    }

    private static void createInfoPanel() {
        c.gridy = 0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.7;
        infoPanel.setBorder(defaultBorder);
        panel.add(infoPanel, c);

        infoPanel.setLayout(null);
        createWarehouseInfo();
    }

    private static void updateWarehouseInfo() {
        infoPanel.removeAll();
        infoPanel.revalidate();
        infoPanel.repaint();
        createWarehouseInfo();
    }

    private static void updateGroupInfo(ProductGroup group) {
        infoPanel.removeAll();
        infoPanel.revalidate();
        infoPanel.repaint();
        createGroupInfo(group);
    }

    private static void updateProductInfo(Product product){
        infoPanel.removeAll();
        infoPanel.revalidate();
        infoPanel.repaint();
        createProductInfo(product);
    }

    private static void createProductInfo(Product product) {
        JTextArea name = new JTextArea(product.getName());
        name.setFont(new Font("Arial", Font.BOLD, 34));
        name.setBackground(Color.LIGHT_GRAY);
        JScrollPane namePane = new JScrollPane(name);
        namePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        namePane.setBounds(10, 10, 600, 55);
        infoPanel.add(namePane);

        JTextArea desc = new JTextArea(product.getDescription());
        desc.setFont(new Font("Arial", Font.PLAIN, 24));
        desc.setBackground(Color.LIGHT_GRAY);
        JScrollPane descPane = new JScrollPane(desc);
        descPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        descPane.setBounds(10, 75, 600, 45);
        infoPanel.add(descPane);

        JLabel prod = new JLabel("Виробник: ");
        prod.setFont(new Font("Arial", Font.PLAIN, 24));
        prod.setBounds(10, 125, 150, 45);
        infoPanel.add(prod);

        JTextArea prodText = new JTextArea(product.getProducer());
        prodText.setFont(new Font("Arial", Font.PLAIN, 24));
        prodText.setBackground(Color.LIGHT_GRAY);
        JScrollPane prodTextPane = new JScrollPane(prodText);
        prodTextPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        prodTextPane.setBounds(200, 125, 410, 45);
        infoPanel.add(prodTextPane);

        JLabel num = new JLabel("Кількість: ");
        num.setFont(new Font("Arial", Font.PLAIN, 24));
        num.setBounds(10, 175, 150, 35);
        infoPanel.add(num);

        JSpinner numText = new JSpinner(new SpinnerNumberModel(1, 0, 1000000, 1));
        numText.setValue(product.getNumber());
        numText.setFont(new Font("Arial", Font.PLAIN, 24));
        numText.setBackground(Color.LIGHT_GRAY);
        numText.setBounds(170, 175, 135, 35);

        JComponent editor2 = numText.getEditor();
        JSpinner.DefaultEditor spinnerEditor2 = (JSpinner.DefaultEditor)editor2;
        spinnerEditor2.getTextField().setHorizontalAlignment(JTextField.LEFT);

        infoPanel.add(numText);

        JLabel value = new JLabel("Ціна: ");
        value.setFont(new Font("Arial", Font.PLAIN, 24));
        value.setBounds(315, 175, 150, 35);
        infoPanel.add(value);

        JSpinner valueText = new JSpinner(new SpinnerNumberModel(1, 0, 1000000, 1));
        valueText.setValue(((Double)product.getPrice()).intValue());
        valueText.setFont(new Font("Arial", Font.PLAIN, 24));
        valueText.setBackground(Color.LIGHT_GRAY);
        valueText.setBounds(475, 175, 135, 35);

        JComponent editor3 = valueText.getEditor();
        JSpinner.DefaultEditor spinnerEditor3 = (JSpinner.DefaultEditor)editor3;
        spinnerEditor3.getTextField().setHorizontalAlignment(JTextField.LEFT);

        infoPanel.add(valueText);

        JLabel totalValue = new JLabel("Загальна вартість товару: " + product.getPrice() * product.getNumber() + " грн.");
        totalValue.setFont(new Font("Arial", Font.PLAIN, 24));
        totalValue.setBounds(10, 215, 600, 30);
        infoPanel.add(totalValue);

        JButton saveButton = new JButton("Зберегти");
        saveButton.addActionListener(e -> {
            String productName = name.getText().trim();
            String productDesc = desc.getText().trim();
            String productProd = prodText.getText().trim();
            int productNumber = (int)numText.getValue();
            double productValue = ((Integer)valueText.getValue()).doubleValue();

            if(productName.equals(product.getName()) && productDesc.equals(product.getDescription()) && productProd.equals(product.getProducer()) && productNumber == product.getNumber() && productValue == product.getPrice()){
                JOptionPane.showMessageDialog(null, "Відредагуйте товар перед тим як зберігати зміни :)", "Зміни не було внесено", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if(!productName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+")){
                JOptionPane.showMessageDialog(null, "Назва товару має складатись лише з літер, цифр, пробілів, _ і тире!", "Некоректна назва", JOptionPane.INFORMATION_MESSAGE);
            }

            if(productDesc.isEmpty()){
                JOptionPane.showMessageDialog(null, "Заповніть опис товару!", "Порожній опис", JOptionPane.INFORMATION_MESSAGE);
            }

            if(productProd.isEmpty()){
                JOptionPane.showMessageDialog(null, "Вкажіть виробника!", "Відсутній виробник", JOptionPane.INFORMATION_MESSAGE);
            }

            if (storage.productExistsInStorageByName(productName) && !productName.equals(product.getName())){
                JOptionPane.showMessageDialog(null, "Товар з такою назвою вже існує!", "некоректна назва", JOptionPane.INFORMATION_MESSAGE);
            }

            if(!productDesc.isEmpty() && !productProd.isEmpty() && productName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+") && (!storage.productExistsInStorageByName(productName) || productName.equals(product.getName()))){
                    product.setDescription(productDesc);
                    product.setNumber(productNumber);
                    product.setPrice(productValue);
                    product.setProducer(productProd);
                    product.setName(productName);
                updateTreePanel();
            }
        });
        saveButton.setBounds(495, 310, 100, 30);
        infoPanel.add(saveButton);
    }

    private static void createGroupInfo(ProductGroup group) {
        JTextArea name = new JTextArea(group.getName());
        name.setFont(new Font("Arial", Font.BOLD, 34));
        name.setBounds(10, 10, 600, 45);
        name.setBackground(Color.LIGHT_GRAY);
        infoPanel.add(name);

        JLabel value = new JLabel("Загальна вартість групи: " + group.priceOfAllProductsInAGroup() + " грн.");
        value.setFont(new Font("Arial", Font.PLAIN, 24));
        value.setBounds(10, 60, 600, 30);
        infoPanel.add(value);

        int numOfProds = group.getListOfProducts().size();
        String[][] items = new String[numOfProds][5];
        int i = 0;
        for (String[] smth : group.getProductsAsString(false)) {
            items[i] = smth;
            i++;
        }
        String[] categories = {"Товар", "Виробник", "Опис", "К-ть", "Ціна"};
        JTable table = new JTable(items, categories) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setCellSelectionEnabled(false);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 100, 600, 200);
        infoPanel.add(scroll);

        JButton saveButton = new JButton("Зберегти");
        saveButton.addActionListener(e -> {
            String groupName = name.getText().trim();
            if (!groupName.equals(group.getName())) {
                if (!groupName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+")) {
                    JOptionPane.showMessageDialog(null, "Назва групи має складатись лише з літер, цифр, пробілів, _ і тире!", "Некоректна назва", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    group.setName(groupName);
                    updateTreePanel();
                }
            }
        });
        saveButton.setBounds(495, 310, 100, 30);
        infoPanel.add(saveButton);
    }

    private static void createWarehouseInfo() {
        JTextArea name = new JTextArea(storage.getName());
        name.setFont(new Font("Arial", Font.BOLD, 34));
        name.setBounds(10, 10, 600, 45);
        name.setBackground(Color.LIGHT_GRAY);
        infoPanel.add(name);

        JLabel value = new JLabel("Загальна вартість складу: " + storage.priceOfAllProductsOnStorage() + " грн.");
        value.setFont(new Font("Arial", Font.PLAIN, 24));
        value.setBounds(10, 60, 600, 30);
        infoPanel.add(value);

        int numOfProds = 0;
        for (ProductGroup pg : storage.getListOfProductGroups()) {
            numOfProds += pg.getListOfProducts().size();
        }
        String[][] items = new String[numOfProds][6];
        int i = 0;
        for (ProductGroup pg : storage.getListOfProductGroups()) {
            for (String[] smth : pg.getProductsAsString(true)) {
                items[i] = smth;
                i++;
            }
        }
        String[] categories = {"Товар", "Виробник", "Опис", "Група", "К-ть", "Ціна"};
        JTable table = new JTable(items, categories) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setCellSelectionEnabled(false);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 100, 600, 200);
        infoPanel.add(scroll);

        JButton saveButton = new JButton("Зберегти");
        saveButton.addActionListener(e -> {
            String storageName = name.getText().trim();
            if (!storageName.equals(storage.getName())) {
                if (!storageName.matches("[A-Za-zА-Яа-яІіЇїҐґЄє_ 0-9-]+")) {
                    JOptionPane.showMessageDialog(null, "Назва складу має складатись лише з літер, цифр, пробілів, _ і тире!", "Некоректна назва", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    storage.setName(storageName);
                    updateTreePanel();
                }
            }
        });
        saveButton.setBounds(495, 310, 100, 30);
        infoPanel.add(saveButton);
    }

    private static void createTreePanel(String search) {
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.15;
        c.weighty = 1;
        treePanel.setBorder(defaultBorder);
        treePanel.setLayout(new BorderLayout());
        panel.add(treePanel, c);

        createTree(search);
        createTreeMenu();
        createGroupButtons();
    }

    private static void createGroupButtons() {
        JButton createGroupButton = new JButton("Додати групу");
        createGroupButton.addActionListener(e -> {
            AddGroupDialog agp = new AddGroupDialog();
            agp.setVisible(true);
        });
        JButton removeGroupButton = new JButton("Видалити групу");
        removeGroupButton.addActionListener(e -> {
            if (!storage.getListOfProductGroups().isEmpty()) {
                RemoveGroupDialog rgp = new RemoveGroupDialog();
                rgp.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Нема що видаляти!", "Нема груп", JOptionPane.INFORMATION_MESSAGE);
            }

        });
        JPanel miniPanel = new JPanel(new BorderLayout());
        miniPanel.add(createGroupButton, BorderLayout.EAST);
        miniPanel.add(removeGroupButton, BorderLayout.WEST);
        treePanel.add(miniPanel, BorderLayout.SOUTH);
    }

    private static void createTreeMenu() {
        JMenuBar treeMenu = new JMenuBar();

        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            if (!storage.getListOfProductGroups().isEmpty()) {
                AddProductDialog apd = new AddProductDialog();
                apd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Створіть нову групу товарів, перед тим як додавати новий товар!", "Нема груп", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton removeButton = new JButton("-");
        removeButton.addActionListener(e -> {
            if (!storage.getListOfProductGroups().isEmpty()) {
                RemoveProductDialog rpd = new RemoveProductDialog();
                rpd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Склад повністю порожній!", "Нема груп", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        JButton searchButton = new JButton("Пошук");

        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        treeMenu.add(addButton);
        treeMenu.add(removeButton);
        treeMenu.add(searchButton);

        JTextField search = new JTextField();
        treeMenu.add(search);

        searchButton.addActionListener(e -> {
            if (search.getText().isEmpty()) {
                updateTreePanel();
            } else {
                treePanel.removeAll();
                treePanel.revalidate();
                treePanel.repaint();
                createTreePanel(search.getText() + '*');
            }
        });
        treePanel.add(treeMenu, BorderLayout.NORTH);
    }

    private static int currentInfo = 1;
    private static ProductGroup currentGroup = null;
    private static Product currentProduct = null;

    static void updateTreePanel() {
        treePanel.removeAll();
        treePanel.revalidate();
        treePanel.repaint();
        createTreePanel("*");
        switch (currentInfo) {
            case 1:
                updateWarehouseInfo();
                break;
            case 2:
                updateGroupInfo(currentGroup);
                break;
            case 3:
                updateProductInfo(currentProduct);
                break;
        }
    }

    private static void createTree(String search) {
        JTree tree = getjTree(search);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath path = e.getPath();
                switch (e.getPath().getPathCount()) {
                    case 1:
                        currentInfo = 1;
                        updateWarehouseInfo();
                        break;
                    case 2:
                        currentInfo = 2;
                        currentGroup = storage.getListOfProductGroups().stream().filter(group -> group.getName().equals(e.getPath().getLastPathComponent().toString())).findFirst().get();
                        updateGroupInfo(currentGroup);
                        break;
                    case 3:
                        currentInfo = 3;
                        currentGroup = storage.getListOfProductGroups().stream().filter(group -> group.getName().equals(e.getPath().getPath()[1].toString())).findFirst().get();
                        currentProduct = currentGroup.getListOfProducts().stream().filter(product -> product.getName().equals(e.getPath().getLastPathComponent().toString())).findFirst().get();
                        updateProductInfo(currentProduct);
                        break;
                }
            }
        });
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        treePanel.add(treeView, BorderLayout.CENTER);
        for (int i = storage.getListOfProductGroups().size(); i > 0; i--) {
            tree.expandRow(i);
        }
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {

            }
        });
    }

    private static JTree getjTree(String search) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(storage.getName());
        DefaultMutableTreeNode group;
        DefaultMutableTreeNode element;

        HashSet<Product> found = storage.searchProductByName(search);

        for (ProductGroup productGroup : storage.getListOfProductGroups()) {
            group = new DefaultMutableTreeNode(productGroup.getName());
            top.add(group);
            for (Product product : productGroup.getListOfProducts()) {
                if (found.contains(product)) {
                    element = new DefaultMutableTreeNode(product.getName());
                    group.add(element);
                }
            }
            if (top.getLastChild().isLeaf() && !search.equals("*")) {
                top.remove(group);
            }
        }
        JTree tree = new JTree(top);
        tree.setFont(new Font("Arial", Font.PLAIN, 20));
        return tree;
    }

    private void createMenu() {
        JMenu menu = new JMenu("Файл");
        JMenuItem save = new JMenuItem("Зберегти");
        save.addActionListener(e -> save());

        menu.add(save);
        menuBar.add(menu);
    }

    private void save() {
        String path = "warehouse.save";
        File saveFile = new File(path);
        try {
            if (!saveFile.createNewFile()) {
                new FileOutputStream(path).close();
            }
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(saveFile));
            bw.writeObject(storage);
            bw.close();
        } catch (IOException ex) {
            System.out.println("Виникла проблема під час збереження!");
        }
    }
}