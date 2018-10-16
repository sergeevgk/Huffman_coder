import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public interface Config_Interpreter_Base {
    //public enum Grammar{};
    //private static FileReader fileReader;
    //private static BufferedReader reader;
    public abstract boolean ReadConfiguration(String fileName, Map config, Map configOptions, Map freqTable);
   /* public enum GRAMMAR {INPUT, OUTPUT, OPTIONS, FREQUENCY_TABLE,CODE_MODE, BUFFER_SIZE, HUFFMAN_TREE};
    public static final Map<String, GRAMMAR> grammarMap;
    static
    {
        grammarMap = new HashMap<>();
        grammarMap.put("IN", GRAMMAR.INPUT);
        grammarMap.put("OUT", GRAMMAR.OUTPUT);
        grammarMap.put("OPTIONS", GRAMMAR.OPTIONS);
        grammarMap.put("FREQUENCY_TABLE", GRAMMAR.FREQUENCY_TABLE);
        grammarMap.put("CODE_MODE", GRAMMAR.CODE_MODE);
        grammarMap.put("BUFFER_SIZE", GRAMMAR.BUFFER_SIZE);
        grammarMap.put("HUFFMAN_TREE", GRAMMAR.HUFFMAN_TREE);
    }*/
}
