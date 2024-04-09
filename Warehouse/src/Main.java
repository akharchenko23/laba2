import Storage.Product;
import Storage.ProductGroup;
import Storage.Storage;
import src.Interface.WareHouseWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductGroup> productGroupArrayList = new ArrayList<>();
        Product pr1 = new Product("name", "desc", "pr", 1, 1);
        Product pr2 = new Product("name2", "desc2", "pr2", 2, 2);
        ProductGroup productGroup1 = new ProductGroup("namePG1", "descPG1", productArrayList);
        ProductGroup productGroup2 = new ProductGroup("namePG2", "descPG2", productArrayList);
        Storage storage = new Storage("name", productGroupArrayList);

        productGroup1.addProduct(pr1);
        productGroup2.addProduct(pr2);

        storage.addProductGroup(productGroup1);
        storage.addProductGroup(productGroup2);

       System.out.println(storage.searchForProduct(pr1));
       System.out.println(storage.searchProductByName("name2"));

        WareHouseWindow start = new WareHouseWindow();
        start.setVisible(1==1);
    }
}