import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterOptions implements ConfigInterpreter<GrammarOptions.Grammar, String> {
    String fileName;
    private final int BUFFER_SIZE = 100;
    private final String DELIMITER = "=";
    public static final Map<String, GrammarOptions.Grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("FREQUENCY_TABLE", GrammarOptions.Grammar.FREQUENCY_TABLE);
        grammarMap.put("CODE_MODE", GrammarOptions.Grammar.CODE_MODE);
        grammarMap.put("BUFFER_SIZE", GrammarOptions.Grammar.BUFFER_SIZE);
        grammarMap.put("HUFFMAN_TREE", GrammarOptions.Grammar.HUFFMAN_TREE);
    }
    public ConfigInterpreterOptions(String fileName){
        this.fileName = fileName;
    }
    @Override
    public final void readConfiguration(Map<GrammarOptions.Grammar, String> configMap) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid options file syntax.\n");
                }
                GrammarOptions.Grammar g = grammarMap.get(set[0]);
                if (g == GrammarOptions.Grammar.FREQUENCY_TABLE) {
                    configMap.put(g, set[1]);
                    //classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == GrammarOptions.Grammar.CODE_MODE || g == GrammarOptions.Grammar.BUFFER_SIZE) {
                    configMap.put(g, set[1]);
                } else if (g == GrammarOptions.Grammar.HUFFMAN_TREE) {
                    configMap.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in options file");
                }
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return;
        }
        if (configMap.putIfAbsent(GrammarOptions.Grammar.BUFFER_SIZE, Integer.toString(BUFFER_SIZE)) == null) {//make final string block_size "*size*"
            Log.logReport("Missing block length, using default block length -" + Integer.toString(BUFFER_SIZE) + "symbols\n");
        }
        return;
    }
}

