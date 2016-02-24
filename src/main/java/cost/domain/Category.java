package cost.domain;

/**
 * Created by Arne on 17/02/2016.
 */
public enum Category {
    FOOD("Food"),
    DRINKS("Drink"),
    FREETIME("FreeTime");

    private String type;

    Category(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
