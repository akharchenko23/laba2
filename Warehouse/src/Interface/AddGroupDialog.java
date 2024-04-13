package Interface;

import Storage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupDialog extends JFrame {

    private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final Font defaultFont = new Font("Arial", Font.PLAIN, 24);

    public AddGroupDialog(Storage storage) {
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

        JLabel nameWarning = new JLabel();
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
        JButton okButton = new JButton("Oк");
        buttonPanel.add(okButton);
        buttonPanel.add(new JPanel());
        c.gridy++;
        this.add(buttonPanel, c);

        c.gridy++;
        this.add(new JPanel(), c);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String desc = descField.getText().trim();

                if (name.isEmpty()) {
                    nameWarning.setText("Введіть ім'я!");
                }

                if (desc.isEmpty()) {

                    descWarning.setText("Введіть опис!");
                }

                if (!name.isEmpty() && !desc.isEmpty()) {
                    nameWarning.setText("");
                    descWarning.setText("");
                    storage.addProductGroup(new ProductGroup(name, desc));
                    AddGroupDialog.this.dispose();
                }
            }
        });
    }
}
