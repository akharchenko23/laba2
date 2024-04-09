package Storage;

import java.util.ArrayList;

public class Storage extends ProductGroup {
    private ArrayList<ProductGroup> listOfProductGroups;
    private String name;


    Storage(){}
    Storage(String name){
        this.name = name;
    }
    Storage(String name, ArrayList<ProductGroup> listOfProductGroups){
        this.name = name;
        this.listOfProductGroups = listOfProductGroups;
    }

    public void deleteProductGroup(ProductGroup productGroup){
        listOfProductGroups.remove(productGroup);
    }



}
