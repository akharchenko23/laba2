package Storage;

import java.util.ArrayList;

public class Product {

    /**
    У кожного товару є наступні властивості - назва, опис, виробник, кількість на складі,
    ціна за одиницю.

     */


    private String name;
    private String description;
    private String producer;
    private int number;
    private double price;
     public Product(){}

     public Product(String name, String description, String producer, int number, double price){
         this.name = name;
         this.description = description;
         this.producer = producer;
         this.number = number;
         this.price = price;
     }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
         return name;
    }

    public void setDescription(String description){
         this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
    public String getProducer(){
         return producer;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber(){
         return number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " " + description + " " + producer + " " + number + " " + price;
    }
}
