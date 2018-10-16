import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Config_Interpreter_Options implements Config_Interpreter_Base{
    private final int BUFFER_SIZE = 100;
    private final String DELIMITER = "=";
    public enum GRAMMAR {FREQUENCY_TABLE,CODE_MODE, BUFFER_SIZE, HUFFMAN_TREE};
    public static final Map<String, GRAMMAR> grammarMap;
    static
    {
        grammarMap = new HashMap<>();
        grammarMap.put("FREQUENCY_TABLE", GRAMMAR.FREQUENCY_TABLE);
        grammarMap.put("CODE_MODE", GRAMMAR.CODE_MODE);
        grammarMap.put("BUFFER_SIZE", GRAMMAR.BUFFER_SIZE);
        grammarMap.put("HUFFMAN_TREE", GRAMMAR.HUFFMAN_TREE);
    }
    private static Config_Interpreter_Table c_t = new Config_Interpreter_Table();
    public final boolean ReadConfiguration(String fileName, Map config, Map configOptions, Map freqTable){
        try {
            FileInputStream instream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2) {
                    Log.logReport("Invalid options file syntax.\n");
                }
                GRAMMAR g = grammarMap.get(set[0]);
                if (g == GRAMMAR.FREQUENCY_TABLE){
                    configOptions.put(g, set[1]);
                    c_t.ReadConfiguration(set[1], config, configOptions, freqTable);
                }
                else if (g == GRAMMAR.CODE_MODE || g == GRAMMAR.BUFFER_SIZE){
                    configOptions.put(g, set[1]);
                }
                else if(g == GRAMMAR.HUFFMAN_TREE){
                    configOptions.put(g, set[1]);
                }
                else {
                    Log.logReport("Invalid lexeme in options file");
                }

            }
        }
        catch (Exception e){
            Log.logReport("Error while reading config file.\n");
            return false;
        }
        if (configOptions.putIfAbsent(GRAMMAR.BUFFER_SIZE, Integer.toString(BUFFER_SIZE)) == null) {//make final string block_size "*size*"
            Log.logReport("Missing block length, using default block length -" + Integer.toString(BUFFER_SIZE) + "symbols\n");
        }
        return true;
    }
}

