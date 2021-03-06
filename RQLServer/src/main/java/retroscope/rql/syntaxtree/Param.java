package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 */
public class Param {

    private String identifier;
    private String superIdentifier = "";


    public Param(String identifier) {
        this.identifier = identifier;
    }

    public Param(String superIdentifier, String identifier) {
        this.superIdentifier = superIdentifier;
        this.identifier = identifier;
    }

    public String toString()
    {
        if (superIdentifier.equals("")) {
            return identifier;
        } else {
            return superIdentifier + "." + identifier;
        }
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setSuperIdentifier(String superIdentifier) {
        this.superIdentifier = superIdentifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getSuperIdentifier() {
        return superIdentifier;
    }

}
