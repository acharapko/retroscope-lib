package retroscope.log;

import retroscope.rql.RQLItem;

/**
 * Created by Aleksey on 2/9/2017.
 *
 */
public interface RQLSnapshotable extends Snapshotable<String, RQLItem> {

   RQLSetMap getSnapshotSetMap(int snapshotID);

}
