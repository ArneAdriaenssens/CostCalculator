package cost.domain;

import user.domain.User;

/**
 * Created by Arne on 11/02/2016.
 */
public class Cost extends Identifiable{

    private double price;
    private User owner;
    private String location;
    private Category category;
    private String description;
    
    public Cost(){}
    
    public Cost(double price, String location, User owner, Category category, String description){
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

    public User getOwner() {
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

    public void setOwner(User owner){
        if(owner==null)throw new IllegalArgumentException("Owner can't be empty");
        this.owner = owner;
    }

    public void setPrice(double price)  {
        if(price<=0)throw new IllegalArgumentException("Price must be greater then 0");
        this.price = price;
    }

    public void setLocation(String location) {
        if(location==null||location.equals(""))throw new IllegalArgumentException("Location can't be empty");
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
