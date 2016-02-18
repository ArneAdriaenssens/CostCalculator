package cost;

import user.User;

/**
 * Created by Arne on 11/02/2016.
 */
public class FreeTimeCost extends Cost{

    public FreeTimeCost(double price, User owner, String location){
        super(price, owner, location);
    }

    @Override
    public String toString() {
        return super.toString()+"freetime";
    }
}
