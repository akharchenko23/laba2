package Storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage implements Serializable {
    private ArrayList<ProductGroup> listOfProductGroups = new ArrayList<>();
   // private ArrayList<Product> allProducts;
    private String name;


    public Storage() {
    }



    public Storage(String name) {
        this.name = name;
        //this.listOfProductGroups = listOfProductGroups;
       // this.allProducts = allProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setListOfProductGroups(ArrayList<ProductGroup> listOfProductGroups) {
        this.listOfProductGroups = listOfProductGroups;
    }

    public ArrayList<ProductGroup> getListOfProductGroups() {
        return listOfProductGroups;
    }
/*
    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }
*/

    public String toString() {
        return name + " " + listOfProductGroups;
    }

    public void deleteProductGroup(ProductGroup productGroup) {
        if (listOfProductGroups.contains(productGroup)) {
            listOfProductGroups.remove(productGroup);
            //////
            //allProducts.removeAll(productGroup.getListOfProducts());
        } else {
            //todo exception
        }
    }

    public void addProductGroup(ProductGroup productGroup) {
        listOfProductGroups.add(productGroup);
       // allProducts.addAll(productGroup.getListOfProducts());
    }

    public Product searchForProduct(Product product) {
        for (ProductGroup productGroup : listOfProductGroups) {
            return productGroup.getProduct(product);
        }
        return null;
    }

    public HashSet<Product> searchProductByName(String name) {
        HashSet<Product> omg = new HashSet<>();

        boolean found = false;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '*' || name.charAt(i) == '?') {
                found = true;
            }
        }
        if (found == true) {
            name = name.replaceAll("\\*", ".*").replaceAll("\\?", ".{1}");

            Pattern regex = Pattern.compile(name);
            //System.out.println("скільки воно сюди приходить ");

            // Шукаємо відповідності для кожного слова в списку
            //for (ProductGroup productGroup : listOfProductGroups) {
//            ArrayList<Product> allProducts = new ArrayList<>();
//            for (ProductGroup productGroup : listOfProductGroups) {
            // System.out.println("pr gr "+productGroup);
            // }
            //System.out.println(allProducts);
            for (ProductGroup productGroup : listOfProductGroups) {
                for (Product product : productGroup.getListOfProducts()) {
                    Matcher matcher = regex.matcher(product.getName());
                    if (matcher.matches()) {
                        //System.out.println("поклало ");
                        omg.add(product);
                    }
                }
            }

            //}
            // }
            return omg;
        }

        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product product : productGroup.getListOfProducts()) {
                if (product.getName().equals(name)) {
                    omg.add(product);
                    System.out.println("не все так погано");
                }
            }
        }
        return omg;

    }


}
