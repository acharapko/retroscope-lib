package retroscope.rql.syntaxtree;

/**
 * Created by ALEKS on 1/12/2017.
 * Warnings that arise during the runtime
 */
public class RQLRunTimeWarning {

    private String message;
    private WarningType type;
    private String warnId;
    public enum WarningType {VARIABLE_UNDEFINED, INCOMPATIBLE_TYPES, LOG_OUT_OF_TIME_BOUNDS, RETROSCOPE_EXCEPTION}

    public RQLRunTimeWarning(WarningType type, String warnId, String message){
        this.message = message;
        this.type = type;
        this.warnId = warnId;
    }

    public WarningType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getWarningId() {
        return warnId;
    }

    public boolean equals(Object o) {
        if (!(o instanceof RQLRunTimeWarning)) {
            return false;
        }
        if (o == this) {
            return true;
        }

        //actual comparison
        RQLRunTimeWarning wo = (RQLRunTimeWarning) o;
        if (type != wo.getType()) {
            return false;
        }
        if (!warnId.equals(wo.getWarningId())) {
            return false;
        }
        return true;
    }
}
