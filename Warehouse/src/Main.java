import Storage.Product;
import Storage.ProductGroup;
import Storage.Storage;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        ArrayList<ProductGroup> productGroupArrayList = new ArrayList<>();
       // ArrayList<Product> allProducts = new ArrayList<>();
        Product pr1 = new Product("name", "desc", "pr", 1, 1);
        Product pr2 = new Product("nae", "desc2", "pr2", 2, 2);
        Product pr3 = new Product("namRe", "desc3", "pr3", 2, 2);
        ProductGroup productGroup1 = new ProductGroup("namePG1", "descPG1", productArrayList);
        ProductGroup productGroup2 = new ProductGroup("namePG2", "descPG2", productArrayList);
        ProductGroup productGroup3 = new ProductGroup("namePG3", "descPG3", productArrayList);

        Storage storage = new Storage("name", productGroupArrayList);

//перероблю, але потім
//        allProducts.add(pr1);
//        allProducts.add(pr2);
//        allProducts.add(pr3);



        productGroup1.addProduct(pr1);
        productGroup2.addProduct(pr2);
        productGroup3.addProduct(pr3);
       // productGroup3.addProduct(pr1);

        storage.addProductGroup(productGroup1);
        storage.addProductGroup(productGroup2);
        storage.addProductGroup(productGroup3);

//System.out.println(allProducts);
       //System.out.println(storage.searchForProduct(pr1));
      System.out.println(storage.searchProductByName("na*e"));

    }
}