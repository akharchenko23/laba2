package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WareHouseWindow extends JFrame {
    private static final String title = "Склад";
    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private static final JPanel panel = new JPanel();
    private static final JPanel buttonPanel = new JPanel();
    private static final JPanel groupPanel = new JPanel();
    private static final JPanel consolePanel = new JPanel();
    private static final JMenuBar menuBar = new JMenuBar();
    private static final GridBagConstraints c = new GridBagConstraints();

    public WareHouseWindow() {
        super();
        this.setTitle(title);
        this.setLocation(screen.width / 4, screen.height / 4);
        this.setSize(screen.width / 2, screen.height / 2);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() { //if there is any unsaved progress asks the user to save it
            @Override
            public void windowClosing(WindowEvent e) {
                int whatToDo = JOptionPane.showOptionDialog(null, "Зберегти зміни?", "Незбережені зміни", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Так", "Ні", "Скасувати"}, "Скасувати");
                if (whatToDo == JOptionPane.YES_OPTION || whatToDo == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                //TODO Add actual saving
            }
        });


        this.setLayout(new BorderLayout()); //Layout of the window
        c.fill = GridBagConstraints.BOTH;

        this.add(menuBar, BorderLayout.NORTH);

        panel.setLayout(new GridBagLayout()); //Main Layout of the program!!
        this.add(panel, BorderLayout.CENTER);

        init();

        consolePanel.setBorder(new TitledBorder("Консоль"));
        groupPanel.setBorder(new TitledBorder("Групи товарів"));


        this.add(panel, BorderLayout.CENTER);
    }

    private void init() {
        createMenu();

        panel.setLayout(new GridBagLayout());
        WareHouseWindow.this.add(panel, BorderLayout.CENTER);
        c.fill = GridBagConstraints.BOTH;

        createButtonPanel();
        createGroupPanel();
        createConsolePanel();
    }

    private void createConsolePanel() {
        c.gridy = 1;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.3;
        panel.add(consolePanel, c);
    }

    private void createGroupPanel() {
        c.gridy = 0;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.7;
        panel.add(groupPanel, c);


    }

    private void createButtonPanel() {
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.weighty = 1;
        panel.add(buttonPanel, c);

        buttonPanel.setBorder(new TitledBorder("Дії"));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,10,10);
        buttonPanel.setLayout(layout);

        JButton test = new JButton("Біолабораторія");
        test.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //test.setBorder(new EmptyBorder(20,20,20,20));
        test.setFocusPainted(false);
        test.setFont(new Font("TimesNewRoman", Font.PLAIN, 16));
        test.setPreferredSize(new Dimension(100,100));
        buttonPanel.add(test);
        buttonPanel.add(new JButton("rvwefb"));
    }

    private void createMenu() {
        JMenu menu = new JMenu("Файл");
        JMenuItem saveItem = new JMenuItem("Зберегти");
        menu.add(saveItem);
        menuBar.add(menu);
    }
}
