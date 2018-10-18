import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterMain implements ConfigInterpreter<GrammarMain.Grammar, String> {
    String fileName;
    private final String OUTPUT_FILE = "output.txt";
    private final String DELIMITER = "=";
    public static final Map<String, GrammarMain.Grammar> grammarMap;

    static {
        grammarMap = new HashMap<>();
        grammarMap.put("IN", GrammarMain.Grammar.INPUT);
        grammarMap.put("OUT", GrammarMain.Grammar.OUTPUT);
        grammarMap.put("OPTIONS", GrammarMain.Grammar.OPTIONS);
    }
    //@Override
    public ConfigInterpreterMain(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public final void readConfiguration(Map<GrammarMain.Grammar, String> configMap) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2 && !set[1].contains(".txt")) {
                    Log.logReport("Invalid configuration file syntax.\n");
                }
                GrammarMain.Grammar g = grammarMap.get(set[0]);
                if (g == GrammarMain.Grammar.OPTIONS) {
                    configMap.put(g, set[1]);
                    //classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == GrammarMain.Grammar.INPUT || g == GrammarMain.Grammar.OUTPUT) { //(input, output)
                    configMap.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in configuration file.");
                }
            }
            if (configMap.putIfAbsent(GrammarMain.Grammar.OUTPUT, OUTPUT_FILE) == null) {
                Log.logReport("Missing output file, using default output file -- output.txt\n");
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return;
        }
        return;
    }
}
