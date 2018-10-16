import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreter implements ConfigInterpreterBase {
    private final String OUTPUT_FILE = "output.txt";
    private final String DELIMITER = "=";
    private static ConfigInterpreterOptions classImplementer = new ConfigInterpreterOptions();

    public enum GRAMMAR {INPUT, OUTPUT, OPTIONS}

    ;
    private static final Map<String, GRAMMAR> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("IN", GRAMMAR.INPUT);
        grammarMap.put("OUT", GRAMMAR.OUTPUT);
        grammarMap.put("OPTIONS", GRAMMAR.OPTIONS);
    }

    public final boolean ReadConfiguration(String fileName, Map config, Map configOptions, Map freqTable) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2 && !set[1].contains(".txt")) {
                    Log.LogReport("Invalid configuration file syntax.\n");
                }
                GRAMMAR g = grammarMap.get(set[0]);
                if (g == GRAMMAR.OPTIONS) {
                    config.put(g, set[1]);
                    classImplementer.ReadConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == GRAMMAR.INPUT || g == GRAMMAR.OUTPUT) { //(input, output)
                    config.put(g, set[1]);
                } else {
                    Log.LogReport("Invalid lexeme in configuration file.");
                }
            }
            if (config.putIfAbsent(GRAMMAR.OUTPUT, OUTPUT_FILE) == null) {
                Log.LogReport("Missing output file, using default output file -- output.txt\n");
            }
        } catch (Exception e) {
            Log.LogReport("Error while reading config file.\n");
            return false;
        }
        return true;
    }
}
