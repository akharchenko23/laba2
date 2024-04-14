package Interface;

import Storage.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

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

    public WareHouseWindow() {
        super(title);
        this.setLocation(screen.width / 4, screen.height / 4);
        this.setSize(screen.width / 2, screen.height / 2);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            Object obj = ois.readObject();
            if (obj.getClass() == Storage.class) {
                storage = (Storage) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Виникла проблема під час читання файлу збереження! Вийдіть з програми не зберігаючись, якщо не хочете пошкодити файл збереження.");
            storage = new Storage();
        }
    }

    private void init() {
        createMenu();

        createTreePanel();
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
    }

    private void createInfoPanel() {
        c.gridy = 0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.7;
        infoPanel.setBorder(defaultBorder);
        panel.add(infoPanel, c);
    }

    private static void createTreePanel() {
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.15;
        c.weighty = 1;
        treePanel.setBorder(defaultBorder);
        treePanel.setLayout(new BorderLayout());
        panel.add(treePanel, c);

        createTree();
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
            RemoveGroupDialog rgp = new RemoveGroupDialog();
            rgp.setVisible(true);
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
            AddProductDialog apd = new AddProductDialog();
            apd.setVisible(true);
        });
        JButton removeButton = new JButton("-");
        removeButton.addActionListener(e -> {
            RemoveProductDialog rpd = new RemoveProductDialog();
            rpd.setVisible(true);
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
        treePanel.add(treeMenu, BorderLayout.NORTH);
    }

    static void updateTreePanel(){
        treePanel.removeAll();
        treePanel.revalidate();
        treePanel.repaint();
        createTreePanel();
    }

    private static void createTree() {
        JTree tree = getjTree();
        JScrollPane treeView = new JScrollPane(tree);
        treePanel.add(treeView, BorderLayout.CENTER);
        for (int i = storage.getListOfProductGroups().size(); i > 0; i--) {
            tree.expandRow(i);
        }
    }

    private static JTree getjTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(title);
        DefaultMutableTreeNode group;
        DefaultMutableTreeNode element;

        for (ProductGroup productGroup : storage.getListOfProductGroups()) {
            group = new DefaultMutableTreeNode(productGroup.getName());
            top.add(group);
            for (Product product : productGroup.getListOfProducts()) {
                element = new DefaultMutableTreeNode(product.getName());
                group.add(element);
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