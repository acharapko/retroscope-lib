package retroscope.rql.syntaxtree;

/**
 * Created by Aleksey on 12/20/2016.
 */
public class Aggregator {

    private AggregatorType aggregator;


    public Aggregator(AggregatorType aggregator) {
        this.aggregator = aggregator;
    }

    public AggregatorType getAggregator() {
        return aggregator;
    }
}
