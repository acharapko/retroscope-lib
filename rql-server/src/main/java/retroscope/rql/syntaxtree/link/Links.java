package retroscope.rql.syntaxtree.link;

import java.util.ArrayList;

/**
 * Created by Aleksey on 3/2/2017.
 * this class is used to aggregate all LINK in one list during parse before passing it to the QueryEnd
 */
public class Links {

    private ArrayList<Link> links;

    public Links(Link link) {
        links = new ArrayList<Link>();
        links.add(link);
    }

    public Links(Links l, Link link) {
        links = l.getLinks();
        links.add(link);
    }

    public ArrayList<Link> getLinks() {
        return links;
    }
}
