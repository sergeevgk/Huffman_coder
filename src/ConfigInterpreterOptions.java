import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterOptions implements ConfigInterpreter {
    private final int BUFFER_SIZE = 100;
    private final String DELIMITER = "=";

    public enum grammar {FREQUENCY_TABLE, CODE_MODE, BUFFER_SIZE, HUFFMAN_TREE}

    ;
    private static final Map<String, grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("FREQUENCY_TABLE", grammar.FREQUENCY_TABLE);
        grammarMap.put("CODE_MODE", grammar.CODE_MODE);
        grammarMap.put("BUFFER_SIZE", grammar.BUFFER_SIZE);
        grammarMap.put("HUFFMAN_TREE", grammar.HUFFMAN_TREE);
    }

    private static ConfigInterpreterTable classImplementer = new ConfigInterpreterTable();

    public final boolean readConfiguration(String fileName, Map config, Map configOptions, Map freqTable) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid options file syntax.\n");
                }
                grammar g = grammarMap.get(set[0]);
                if (g == grammar.FREQUENCY_TABLE) {
                    configOptions.put(g, set[1]);
                    classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == grammar.CODE_MODE || g == grammar.BUFFER_SIZE) {
                    configOptions.put(g, set[1]);
                } else if (g == grammar.HUFFMAN_TREE) {
                    configOptions.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in options file");
                }
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return false;
        }
        if (configOptions.putIfAbsent(grammar.BUFFER_SIZE, Integer.toString(BUFFER_SIZE)) == null) {//make final string block_size "*size*"
            Log.logReport("Missing block length, using default block length -" + Integer.toString(BUFFER_SIZE) + "symbols\n");
        }
        return true;
    }
}

