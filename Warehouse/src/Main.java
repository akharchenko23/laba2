import Storage.Product;
import Storage.ProductGroup;
import Storage.Storage;
import src.Interface.WareHouseWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductGroup> productGroupArrayList = new ArrayList<>();
       // ArrayList<Product> allProducts = new ArrayList<>();
        Product pr1 = new Product("name", "desc", "pr", 1, 1);
        Product pr2 = new Product("nae", "desc2", "pr2", 2, 2);
        Product pr3 = new Product("namRe", "desc3", "pr3", 2, 2);
        ProductGroup productGroup1 = new ProductGroup("namePG1", "descPG1");
        ProductGroup productGroup2 = new ProductGroup("namePG2", "descPG2");
        ProductGroup productGroup3 = new ProductGroup("namePG3", "descPG3");

        Storage storage = new Storage("name", productGroupArrayList);

//перероблю, але потім
//        allProducts.add(pr1);
//        allProducts.add(pr2);
//        allProducts.add(pr3);



        productGroup1.addProduct(pr1);
        productGroup2.addProduct(pr2);
        productGroup3.addProduct(pr3);

       // productGroup1.getListOfProducts().add(pr1);

        System.out.println(productGroup1);
       // productGroup3.addProduct(pr1);

        storage.addProductGroup(productGroup1);
        storage.addProductGroup(productGroup2);
        storage.addProductGroup(productGroup3);

//System.out.println(allProducts);
       //System.out.println(storage.searchForProduct(pr1));
      System.out.println(storage.searchProductByName("na*e"));

        WareHouseWindow start = new WareHouseWindow();
        start.setVisible(1==1);
    }
}