package Storage;


import java.io.Serializable;
import java.util.ArrayList;

public class ProductGroup implements Serializable {
    /**
     * Група товарів містить наступні властивості - назва, опис.
     * <p>
     * Реалізувати додавання/редагування/видалення товару в групу товарів
     * (мається на увазі назва, опис, виробник, ціна за одиницю).
     * <p>
     *      TODO редагування через сет гет в мейні вже буде
     */
    //ArrayList<Storage.Storage.Product> listOfProducts = new ArrayList<>();
    private String name;
    private String description;
    public ArrayList<Product> listOfProducts = new ArrayList<>();

    public ProductGroup() {
    }

    ;

    public ProductGroup(String name) {
        this.name = name;
    }

    public ProductGroup(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Product getProduct(Product product) {

        return product;


    }

    public void setProduct(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }


    public String toString() {
        return name;
    }

    public double priceOfAllProductsInAGroup(){
        double price = 0;
        for(Product product : listOfProducts){
            price += product.getPrice()*product.getNumber();
        }
        return price;
    }

    public void deleteProduct(Product product)  {
        if (listOfProducts.contains(product)) {
            listOfProducts.remove(product);
        } else {
            //todo exception

            // throw new ProductNotFoundException("Product not found: " + product.getName());
        }
    }

    public void addProduct(Product product)  {
        boolean productExists = false;

        for(Product pr: listOfProducts){
            if(pr.getName().equals(product.getName())){
                productExists = true;
                //System.out.println("the same");
               // throw new ProductExistsException("Product already exists: " + product.getName());
                //todo exception
                //break;
            }
        }
        // If the product doesn't exist, add it to the list
        if (!productExists) {
            listOfProducts.add(product);
            //System.out.println("Product added successfully.");
        }
        //listOfProducts.add(product);

        //   for (Product pr : listOfProducts) {
//                if (!pr.getName().equals(product.getName())) {

                //}
            //}
      //  } else {
           // System.out.println("не додавайте одне і те ж");
            //todo throw exception
       // }
    }

    /**
     * Це мені треба для інтерфейсу. Специфічна фігня
     * @return Штука для таблиці
     */
    public String[][] getProductsAsString(){
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(Product product : listOfProducts){
            ArrayList<String> row = new ArrayList<>();
            row.add(product.getName());
            row.add(product.getDescription());
            row.add(product.getProducer());
            row.add(this.name);
            row.add(String.valueOf(product.getNumber()));
            row.add(Double.toString(product.getPrice()));
            res.add(row);
        }
        String[][] actualRes = new String[res.size()][6];
        int i = 0;
        for(ArrayList<String> temp : res){
            actualRes[i] = temp.toArray(new String[0]);
            i++;
        }
        return actualRes;
    }


}
