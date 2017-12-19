package retroscope.rql.server;

import retroscope.rql.environment.GlobalCut;
import retroscope.rql.errors.RQLRunTimeException;
import retroscope.rql.errors.RQLRunTimeWarning;

import java.util.ArrayList;

public interface Runner {

    void execute();

    String explain();

    boolean isExplain();

    boolean hasRunTimeErrors();

    boolean hasRunTimeWarnings();

    ArrayList<RQLRunTimeException> getExceptions();

    ArrayList<RQLRunTimeWarning> getWarnings();

    ArrayList<GlobalCut> getEmittedCuts();
}
