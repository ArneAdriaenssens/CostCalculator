package factory;

/**
 * Created by Arne on 11/02/2016.
 */
public enum RepositoryTypes {
    DB("Db"),
    TXT("Txt"),
    FAKE("Fake");

    private String value;

    RepositoryTypes(String text){
        this.value=text;
    }

    public String getValue(){
        return this.value;
    }
}
