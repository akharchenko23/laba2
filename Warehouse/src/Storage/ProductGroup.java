package Storage;

import Exceptions.Exceptions;

import java.util.ArrayList;

public class ProductGroup extends Product {
    /**
     * Група товарів містить наступні властивості - назва, опис.
     *
     * Реалізувати додавання/редагування/видалення товару в групу товарів
     *      (мається на увазі назва, опис, виробник, ціна за одиницю).
     *
     *      TODO редагування через сет гет в мейні вже буде
     */
    //ArrayList<Storage.Storage.Product> listOfProducts = new ArrayList<>();
    private String name;
    private String description;
    private ArrayList<Product> listOfProducts;

    public ProductGroup(){};

    public ProductGroup(String name) {
        this.name = name;
    }

    public ProductGroup(String name, String description, ArrayList<Product> listOfProducts) {
        this.name = name;
        this.description = description;
        this.listOfProducts = listOfProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct(Product product){
        if(listOfProducts.contains(product)){
            return product;
        }
        return null;
    }

    public void setProduct(ArrayList<Product> listOfProducts){
        this.listOfProducts = listOfProducts;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public  void deleteProduct(Product product){
        if(listOfProducts.contains(product)) {
            listOfProducts.remove(product);
        }else{
            //todo throw exception
        }
    }

    public  void addProduct(Product product){
        listOfProducts.add(product);
    }








}