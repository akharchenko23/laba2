package Interface;

import Storage.*;

import javax.swing.*;
import java.awt.*;

public class AddGroupDialog extends JFrame {

    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Font defaultFont = new Font("Arial", Font.PLAIN, 20);
    private static final JLabel nameWarning = new JLabel();

    public AddGroupDialog() {
        super("Створіть групу");
        this.setSize(screen.width / 4, screen.height / 4);
        this.setLocation(screen.width / 8 * 3, screen.height / 8 * 3);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;

        this.add(new JPanel(), c);


        nameWarning.setForeground(Color.RED);
        nameWarning.setFont(defaultFont);
        nameWarning.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridy++;
        this.add(nameWarning, c);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(new JPanel());
        namePanel.add(new JLabel("Назва групи:"));
        namePanel.add(new JPanel());
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(1000000, 30));
        namePanel.add(nameField);
        namePanel.add(new JPanel());
        c.gridy++;
        this.add(namePanel, c);

        c.gridy++;
        this.add(new JPanel(), c);

        JLabel descWarning = new JLabel();
        descWarning.setForeground(Color.RED);
        descWarning.setFont(defaultFont);
        descWarning.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridy++;
        this.add(descWarning, c);

        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.X_AXIS));
        descPanel.add(new JPanel());
        descPanel.add(new JLabel("Опис групи:"));
        JTextField descField = new JTextField();
        descField.setMaximumSize(new Dimension(1000000, 30));
        descPanel.add(new JPanel());
        descPanel.add(descField);
        descPanel.add(new JPanel());
        c.gridy++;
        this.add(descPanel, c);
        c.gridy++;
        this.add(new JPanel(), c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(new JPanel());
        JButton cancelButton = new JButton("Скасувати");
        buttonPanel.add(cancelButton);
        buttonPanel.add(new JPanel());
        JButton okButton = new JButton("Створити");
        buttonPanel.add(okButton);
        buttonPanel.add(new JPanel());
        c.gridy++;
        this.add(buttonPanel, c);

        c.gridy++;
        this.add(new JPanel(), c);

        cancelButton.addActionListener(e -> this.dispose());

        okButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String desc = descField.getText().trim();

            if (name.isEmpty()) {
                nameWarning.setText("Введіть ім'я!");
            } else {
                nameWarning.setText("");
            }

            if (desc.isEmpty()) {
                descWarning.setText("Введіть опис!");
            } else {
                nameWarning.setText("");
            }


            if (!name.isEmpty() && !desc.isEmpty()) {
                nameWarning.setText("");
                descWarning.setText("");
                ProductGroup productGroup = new ProductGroup(name, desc);
                try {
                    WareHouseWindow.storage.addProductGroup(productGroup);
                    WareHouseWindow.updateTreePanel();
                    WareHouseWindow.console.groupAdded(productGroup);
                    this.dispose();
                } catch (Exception ex) {
                    nameWarning.setText("Група з такою назвою вже існує!");
                }

            }
        });
    }
}
