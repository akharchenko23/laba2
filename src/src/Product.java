public class Product {

    /*
    У кожного товару є наступні властивості - назва, опис, виробник, кількість на складі,
    ціна за одиницю. Група товарів містить наступні властивості - назва, опис.
     */

    private String name;
    private String description;
    private String producer;
    private int number;
    private double price;
     public Product(){};

     public Product(String name, String description, String producer, int number, double price){
         this.name = name;
         this.description = description;
         this.producer = producer;
         this.number = number;
         this.price = price;
     }



}
