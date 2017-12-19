package retroscope.datamodel.datastruct.misc;

import java.util.HashSet;
import java.util.Set;

public class IdList {

    private Set<Long> ids;

    public IdList(long id) {
        ids = new HashSet<>();
        this.ids.add(id);
    }

    public void addId(long id) {
        ids.add(id);
    }

    public Set<Long> getIds() {
        return ids;
    }

}
