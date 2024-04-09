package Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage extends ProductGroup {
    private ArrayList<ProductGroup> listOfProductGroups;
    private ArrayList<Product> allProducts;
    private String name;


    Storage() {
    }

    Storage(String name) {
        this.name = name;
    }

    public Storage(String name, ArrayList<ProductGroup> listOfProductGroups, ArrayList<Product> allProducts) {
        this.name = name;
        this.listOfProductGroups = listOfProductGroups;
        this.allProducts = allProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void deleteProductGroup(ProductGroup productGroup) {
        if (listOfProductGroups.contains(productGroup)) {
            listOfProductGroups.remove(productGroup);
        } else {
            //todo exception
        }
    }

    public void addProductGroup(ProductGroup productGroup) {
        listOfProductGroups.add(productGroup);
    }

    public Product searchForProduct(Product product) {
        for (ProductGroup productGroup : listOfProductGroups) {
            return productGroup.getProduct(product);
        }
        return null;
    }

    public List<Product> searchProductByName(String name) {
        ArrayList<Product> omg = new ArrayList<>();

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
            //todo переробити цикл
            //for (ProductGroup productGroup : listOfProductGroups) {
//            ArrayList<Product> allProducts = new ArrayList<>();
//            for (ProductGroup productGroup : listOfProductGroups) {
                // System.out.println("pr gr "+productGroup);
           // }
            //System.out.println(allProducts);
            //for (ProductGroup productGroup : listOfProductGroups) {
                for (Product product : allProducts) {
                    Matcher matcher = regex.matcher(product.getName());
                    if (matcher.matches()) {
                        //System.out.println("поклало ");
                        omg.add(product);
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
/*
    public List<Product> searcherNormal() {
        ArrayList<Product> omg = new ArrayList<>();
        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product product : productGroup.getListOfProducts()) {
                if (product.getName().equals(name)) {
                    omg.add(product);
                }
            }
        }
        return omg;
    }

    public List<Product> searchPattern(String name) {
        List<Product> matches = new ArrayList<>();
        name = name.replaceAll("\\*", ".*").replaceAll("\\?", ".{1}");

        Pattern regex = Pattern.compile(name);

        // Шукаємо відповідності для кожного слова в списку
        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product product : productGroup.getListOfProducts()) {
                Matcher matcher = regex.matcher(product.getName());
                if (matcher.matches()) {
                    //System.out.println("не все так погано");
                    //System.out.println(product);
                    matches.add(product);
                    // return product;
                }
            }
        }
        return matches;

        //return null;
    }
*/

}
