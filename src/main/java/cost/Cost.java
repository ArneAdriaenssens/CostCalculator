package cost;

import user.User;

/**
 * Created by Arne on 11/02/2016.
 */
public class Cost {

    private double price;
    private Category category;
    private User owner;
    private String location;

    public Cost(double price, Category category, User user, String location) throws IllegalArgumentException{
        setCategory(category);
        setOwner(user);
        setPrice(price);
        setLocation(location);
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

    private void setCategory(Category category)  throws IllegalArgumentException{
        if (category==null)throw new IllegalArgumentException("Category can't be empty");
        this.category = category;
    }

    private void setOwner(User owner)  throws IllegalArgumentException{
        if(owner==null)throw new IllegalArgumentException("Owner can't be empty");
        this.owner = owner;
    }

    private void setPrice(double price) throws IllegalArgumentException {
        if(price<=0)throw new IllegalArgumentException("Price must be greater then 0");
        this.price = price;
    }

    private void setLocation(String location) throws IllegalArgumentException{
        if(location==null||location.equals(""))throw new IllegalArgumentException("Location can't be empty");
        this.location = location;
    }

    @Override
    public String toString() {
        return "cost price: "+getPrice()+" location: "+getLocation()+" category: ";
    }
}
