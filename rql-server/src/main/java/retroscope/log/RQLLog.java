package retroscope.log;

import retroscope.rql.RQLItem;


/**
 * Created by Aleksey on 1/31/2017.
 *
 */
public interface RQLLog extends BasicLog<String, RQLItem>, RQLSnapshotable {

    int getNodeId();

}
