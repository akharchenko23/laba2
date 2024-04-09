package Storage;

import java.util.ArrayList;

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
        for(char c : name.toCharArray()){
            if(c == '*'){

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
        return null;
    }


}
