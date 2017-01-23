package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 */
public class IdentifierList {

    private String[] identifiers;

    public IdentifierList(String id) {
        identifiers = new String[1];
        identifiers[0] = id;
    }

    public IdentifierList(String id, IdentifierList idList) {
        identifiers = new String[idList.getIdentifiers().length + 1];
        System.arraycopy(idList.getIdentifiers(), 0, identifiers, 1, idList.getIdentifiers().length);
        identifiers[0] = id;
    }

    public String[] getIdentifiers() {
        return identifiers;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (String p : identifiers) {
            sb.append(p.toString());
            sb.append(", ");
        }
        return sb.toString();
    }
}
