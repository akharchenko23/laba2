package Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage extends ProductGroup {
    private ArrayList<ProductGroup> listOfProductGroups;
    private String name;


    Storage() {
    }

    Storage(String name) {
        this.name = name;
    }

    public Storage(String name, ArrayList<ProductGroup> listOfProductGroups) {
        this.name = name;
        this.listOfProductGroups = listOfProductGroups;
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

    public Product searchProductByName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '*' || name.charAt(i) == '?') {
                //searchPattern(name);
                name = name.replaceAll("\\*", ".*").replaceAll("\\?", ".{1}");

                Pattern regex = Pattern.compile(name);
                int count = 0;
                count = listOfProductGroups.size();
                //while (count > 0) {
                    count--;
                    for (ProductGroup productGroup : listOfProductGroups) {
                        for (Product product : productGroup.getListOfProducts()) {
                            Matcher matcher = regex.matcher(product.getName());
                            if (matcher.matches()) {
                                System.out.println(product);
                                //matches.add(product.getName());
                                return product;
                                //треба змусити його проходити по всіх словах, а не знаходити перше і радіти
                            }
                        }
                    }
               // }

            }
        }
        for (ProductGroup productGroup : listOfProductGroups) {
            // ArrayList<Product> list = productGroup.getListOfProducts();
            for (Product product : productGroup.getListOfProducts()) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
        }

        System.out.println("все погано");
        return null;
    }

    public Product searchPattern(String name) {
        // List<String> matches = new ArrayList<>();
        name = name.replaceAll("\\*", ".*").replaceAll("\\?", ".{1}");

        Pattern regex = Pattern.compile(name);

        // Шукаємо відповідності для кожного слова в списку
        for (ProductGroup productGroup : listOfProductGroups) {
            for (Product product : productGroup.getListOfProducts()) {
                Matcher matcher = regex.matcher(product.getName());
                if (matcher.matches()) {
                    System.out.println("не все так погано");
                    System.out.println(product);
                    //matches.add(product.getName());
                    return product;
                }
            }
        }
        return null;
    }


}
