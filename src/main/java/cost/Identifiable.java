package cost;

/**
 * Created by Arne on 11/02/2016.
 */
public class Identifiable {

    private long id;

    public Identifiable(long id){
        setId(id);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }
}
