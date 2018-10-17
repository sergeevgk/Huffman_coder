import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterMain implements ConfigInterpreter {
    private final String OUTPUT_FILE = "output.txt";
    private final String DELIMITER = "=";
    private static ConfigInterpreterOptions classImplementer = new ConfigInterpreterOptions();

    public enum grammar {INPUT, OUTPUT, OPTIONS}

    ;
    private static final Map<String, grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("IN", grammar.INPUT);
        grammarMap.put("OUT", grammar.OUTPUT);
        grammarMap.put("OPTIONS", grammar.OPTIONS);
    }

    public final boolean readConfiguration(String fileName, Map config, Map configOptions, Map freqTable) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2 && !set[1].contains(".txt")) {
                    Log.logReport("Invalid configuration file syntax.\n");
                }
                grammar g = grammarMap.get(set[0]);
                if (g == grammar.OPTIONS) {
                    config.put(g, set[1]);
                    classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == grammar.INPUT || g == grammar.OUTPUT) { //(input, output)
                    config.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in configuration file.");
                }
            }
            if (config.putIfAbsent(grammar.OUTPUT, OUTPUT_FILE) == null) {
                Log.logReport("Missing output file, using default output file -- output.txt\n");
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return false;
        }
        return true;
    }
}
