package Storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
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

    public boolean productExistsInStorageByProduct(Product product) {
        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product pr : productGroup.getListOfProducts()) {
                //ім'я чекаєм
                if (pr.getName().equals(product.getName())) {
                    return true;

                }
            }
        }
        return false;
    }

    public boolean productExistsInStorageByName(String name) {
        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product pr : productGroup.getListOfProducts()) {
                if (pr.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public double priceOfAllProductsOnStorage() {
        double price = 0;
        for (ProductGroup productGroup : listOfProductGroups) {
            price += productGroup.priceOfAllProductsInAGroup();
        }
        return price;
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

    public void addProductGroup(ProductGroup productGroup) throws Exception {
        boolean productGrExists = false;
        for (ProductGroup prGr : listOfProductGroups) {
            if (prGr.getName().equals(productGroup.getName())) {
                productGrExists = true;
                throw new Exception();
            }
        }
        if (!productGrExists) {
            listOfProductGroups.add(productGroup);
        }
        /*
        if (!listOfProductGroups.contains(productGroup)) {
            listOfProductGroups.add(productGroup);
        } else {

        }
        // allProducts.addAll(productGroup.getListOfProducts());

         */
    }

    //один бог знає на біса це треба
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
                    // System.out.println("не все так погано");
                }
            }
        }
        return omg;

    }


}
