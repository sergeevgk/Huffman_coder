import java.util.HashMap;
import java.util.Map;

public class GrammarOptions {
    public enum grammar {FREQUENCY_TABLE, CODE_MODE, BUFFER_SIZE, HUFFMAN_TREE};
    public static final Map<String, grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("FREQUENCY_TABLE", grammar.FREQUENCY_TABLE);
        grammarMap.put("CODE_MODE", grammar.CODE_MODE);
        grammarMap.put("BUFFER_SIZE", grammar.BUFFER_SIZE);
        grammarMap.put("HUFFMAN_TREE", grammar.HUFFMAN_TREE);
    }
}
