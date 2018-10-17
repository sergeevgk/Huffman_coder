import java.util.HashMap;
import java.util.Map;

public class GrammarMain {
    public enum grammar {INPUT, OUTPUT, OPTIONS};
    public static final Map<String, grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("IN", grammar.INPUT);
        grammarMap.put("OUT", grammar.OUTPUT);
        grammarMap.put("OPTIONS", grammar.OPTIONS);
    }
}
