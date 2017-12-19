package retroscope.util;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import retroscope.datamodel.datastruct.RQLSymbol;
import retroscope.datamodel.datastruct.sets.RQLSet;
import retroscope.datamodel.datastruct.variables.RQLVariable;
import retroscope.datamodel.parser.DataParser;
import retroscope.datamodel.parser.DataScanner;
import retroscope.datamodel.parser.RQLData;
import retroscope.net.ignite.StateSequence;

import java.io.IOException;
import java.io.StringReader;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by aleksey on 10/18/16.
 */
public class Utils {


    public static IgniteCache<String, String> getAppMetaCache(Ignite ignite, String appName) {
        CacheConfiguration<String, String> cc = new CacheConfiguration<>();
        cc.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        cc.setName(appName);
        IgniteCache<String, String> appMeta = ignite.getOrCreateCache(cc);
        return appMeta;
    }


    public static IgniteCache<Long, StateSequence> getStateSeqCache(Ignite ignite, String appName, String logName) {
        CacheConfiguration<Long, StateSequence> cc = new CacheConfiguration<>();
        //cc.setAtomicityMode(CacheAtomicityMode.TRANSACTIONAL);
        cc.setName(appName + "_" + logName + "_stateseq");
        IgniteCache<Long, StateSequence> stateSeq = ignite.getOrCreateCache(cc);
        return stateSeq;
    }

    public static String prettyCutStr(String cutStr) {
        DataParser p = new DataParser();
        StringReader sr = new StringReader(cutStr);
        DataScanner scanner = new DataScanner(sr);
        try {
            scanner.yylex();
        } catch (IOException e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }
        p.setScanner(scanner);
        p.parse();
        RQLData dt = p.getData();
        if (dt != null) {
            return prettyCutStr(dt);
        }
        return "";

    }

    public static String prettyCutStr(RQLData cut) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        sb.append(String.format("%80s", "").replace(" ", "-"));
        sb.append(System.getProperty("line.separator"));
        formatter.format("%28s %-15s (%-20s)", "Cut @ HLC :", cut.getTimestamp().toString(), cut.getTimestamp().toLong() + "");
        sb.append(System.getProperty("line.separator"));
        for (RQLSymbol symb:cut.getDataItems()) {
            formatter.format("%2s %-15s", " ", symb.getName());
            sb.append(System.getProperty("line.separator"));
            if (symb instanceof RQLSet) {
                RQLSet symbSet = (RQLSet) symb;
                for (RQLSymbol s2: symbSet.getSet()) {
                    formatter.format("%4s @ %-35s %-40s", " ", s2.getNodeIDsStr(), s2.toValueString());
                    //formatter.format("%4s @ %-35s %-40s", " ", s2.getNodeIDsStr(), s2.toValueString() + " - " + s2.hashCode());
                    sb.append(System.getProperty("line.separator"));
                }
            }
            else if (symb instanceof RQLVariable) {
                formatter.format("%4s @ %-35s %-40s", " ", symb.getNodeIDsStr(), symb.toValueString());
                sb.append(System.getProperty("line.separator"));
            }

            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

}
