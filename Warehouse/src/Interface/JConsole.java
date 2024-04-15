package Interface;

import Storage.Product;
import Storage.ProductGroup;

import javax.swing.*;
import java.awt.*;

/*
Реалізувати інтерфейс додавання товару (прийшло на склад крупи гречаної - 10 штук),
інтерфейс списання товару (продали крупи гречаної - 5 шт.)
 */
public class JConsole extends JScrollPane {

/*
    public static void createAndShowGUI() {


        final JFrame frame = new JFrame("це вікно для тестування, в лабі воно зайве");

        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set flow layout for the frame
        frame.getContentPane().setLayout(new FlowLayout());
        JScrollPane scrollPane = getScrollPane();
        frame.add(scrollPane);

        //приклад застосування !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Product pr1 = new Product("nae", "desc", "pr", 3, 1);
        productAdded(pr1, 5);
        productBought(pr1, 2);

        frame.pack();
        frame.setVisible(true);

    }
*/
    private  JTextArea textArea = new JTextArea(10, 40); // Поле класу для JTextArea
    private  JScrollPane scrollableTextArea = new JScrollPane(textArea);

    public  void productAdded(Product product, int numberAdded) {
        textArea.append("Було додано продукт '" + product.getName() + "' у кількості " + numberAdded + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Автоматична прокрутка до нижнього кінця
    }

    public  void productBought(Product product, int numberBought) {
        textArea.append("Було куплено продукт '" + product.getName() + "' у кількості " + numberBought + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Автоматична прокрутка до нижнього кінця
    }

    public  void groupAdded(ProductGroup productGroup) {
        textArea.append("Було додано групу '" + productGroup.getName() + "' \n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Автоматична прокрутка до нижнього кінця
    }

    public  void groupDeleted(ProductGroup productGroup) {
        textArea.append("Було видалено групу '" + productGroup.getName() + "' \n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Автоматична прокрутка до нижнього кінця
    }

    // Метод для отримання JTextArea із скроллерами
    public  JScrollPane getScrollPane() {
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollableTextArea;
    }

    /*
        public static JTextArea productAdded(Product product, int numberAdded) {
            //розмір підлаштувати
            JTextArea textArea = new JTextArea(10, 40);
            JScrollPane scrollableTextArea = new JScrollPane(textArea);
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            textArea.append("Було додано продукт '" + product.getName() + "' у кількості " + numberAdded + "\n");
            return textArea;
        }

        public static JTextArea productBought(Product product, int numberBought) {
            JTextArea textArea = new JTextArea(10, 40);
            JScrollPane scrollableTextArea = new JScrollPane(textArea);
            scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            textArea.append("Було куплено продукт '" + product.getName() + "' у кількості " + numberBought + "\n");
            return textArea;
        }
    public static void main(String[] args) {


        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createAndShowGUI();
            }
        });
    }


     */
}
