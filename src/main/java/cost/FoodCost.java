package cost;

import user.User;

/**
 * Created by Arne on 11/02/2016.
 */
public class FoodCost extends Cost{

    public FoodCost(double price, User owner, String location){
        super(price, Category.FOOD, owner, location);
    }

    @Override
    public String toString() {
        return super.toString()+"food";
    }
}
