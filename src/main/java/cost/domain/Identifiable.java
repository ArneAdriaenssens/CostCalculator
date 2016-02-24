package cost.domain;

import cost.repository.DbCostException;

/**
 * Created by Arne on 11/02/2016.
 */
public class Identifiable {

    private long id;

    public Identifiable(long id){
        setId(id);
    }

    public Identifiable() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id<0)throw new DbCostException("Wrong id used");
        this.id = id;
    }
}
