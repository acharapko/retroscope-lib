package retroscope.rql.server;

import retroscope.rql.errors.RQLRunTimeException;

public class DistributedJobException extends RQLRunTimeException {

    public DistributedJobException(String message) {
        super(message);
    }
}
