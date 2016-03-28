package cost.domain;

import cost.repository.DbCostException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Arne on 11/02/2016.
 */

@MappedSuperclass
public abstract class Identifiable {

    @Id
    @GeneratedValue
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
