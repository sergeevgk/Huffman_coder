import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterOptions implements ConfigInterpreter<GrammarOptions.grammar, String> {
    String fileName;
    private final int BUFFER_SIZE = 100;
    private final String DELIMITER = "=";

    public ConfigInterpreterOptions(String fileName){
        this.fileName = fileName;
    }
    @Override
    public final void readConfiguration(Map<GrammarOptions.grammar, String> configMap) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid options file syntax.\n");
                }
                GrammarOptions.grammar g = GrammarOptions.grammarMap.get(set[0]);
                if (g == GrammarOptions.grammar.FREQUENCY_TABLE) {
                    configMap.put(g, set[1]);
                    //classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == GrammarOptions.grammar.CODE_MODE || g == GrammarOptions.grammar.BUFFER_SIZE) {
                    configMap.put(g, set[1]);
                } else if (g == GrammarOptions.grammar.HUFFMAN_TREE) {
                    configMap.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in options file");
                }
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return;
        }
        if (configMap.putIfAbsent(GrammarOptions.grammar.BUFFER_SIZE, Integer.toString(BUFFER_SIZE)) == null) {//make final string block_size "*size*"
            Log.logReport("Missing block length, using default block length -" + Integer.toString(BUFFER_SIZE) + "symbols\n");
        }
        return;
    }
}

