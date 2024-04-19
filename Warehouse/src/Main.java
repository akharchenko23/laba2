import Interface.JConsole;
import Interface.WareHouseWindow;
import Storage.Product;
import Storage.ProductGroup;
import Storage.Storage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductGroup> productGroupArrayList = new ArrayList<>();
        // ArrayList<Product> allProducts = new ArrayList<>();
        Product pr1 = new Product("nae", "desc", "pr", 3, 1);
        Product pr2 = new Product("name", "desc2", "pr2", 2, 2);
        Product pr3 = new Product("namRe", "desc3", "pr3", 2, 2);
        ProductGroup productGroup1 = new ProductGroup("namePG1", "descPG1");
        ProductGroup productGroup2 = new ProductGroup("namePG2", "descPG2");
        ProductGroup productGroup3 = new ProductGroup("namePG3", "descPG3");

        Storage storage = new Storage("name");


        productGroup1.addProduct(pr1);
        //productGroup1.addProduct(pr2);
        productGroup2.addProduct(pr2);
        productGroup3.addProduct(pr3);

     /*storage.addProductGroup(productGroup1);
     storage.addProductGroup(productGroup2);
     storage.addProductGroup(productGroup3);*/


        for (Product pr : productGroup1.getListOfProducts()) {
            System.out.println(pr);
        }
        System.out.println(productGroup1.priceOfAllProductsInAGroup() + " " + storage.priceOfAllProductsOnStorage());
        // productGroup1.getListOfProducts().add(pr1);

        //System.out.println(productGroup1);
        // productGroup3.addProduct(pr1);


//        System.out.println(productGroup1);
//        System.out.println(productGroup2);
//        System.out.println(productGroup3);

        // System.out.println(storage + " storage");
        //System.out.println(allProducts);
        //System.out.println(storage.searchForProduct(pr1));
        //System.out.println(storage.searchProductByName("na*e"));

        WareHouseWindow start = new WareHouseWindow();
        start.setVisible(true);
        /*try {
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    WareHouseWindow.console.textArea.append(String.valueOf(((char) b)));
                }
            }, true, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            //ignore
        }
        System.out.println("Привітик!");*/

    }
}