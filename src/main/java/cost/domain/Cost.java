package cost.domain;

import java.io.Serializable;
import owner.domain.Owner;

/**
 * Created by Arne on 11/02/2016.
 */
public class Cost extends Identifiable implements Serializable{

    private double price;
    private Owner owner;
    private String location;
    private Category category;
    private String description;
    
    public Cost(){}
    
    public Cost(double price, String location, Owner owner, Category category, String description){
        super();
        setOwner(owner);
        setPrice(price);
        setLocation(location);
        setCategory(category);
        setDescription(description);
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOwner(Owner owner){
        this.owner = owner;
    }

    public void setPrice(double price)  {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public String toString() {
        return "cost price: "+getPrice()+" location: "+getLocation()+" category: "+getCategory() +" description: " + getDescription() + " owner name: " + getOwner().getFirstName();
    }
}
