import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigInterpreterMain implements ConfigInterpreter<GrammarMain.grammar, String> {
    String fileName;
    private final String OUTPUT_FILE = "output.txt";
    private final String DELIMITER = "=";
    //@Override
    public ConfigInterpreterMain(String fileName){
        this.fileName = fileName;
    }

    @Override
    public final void readConfiguration(Map<GrammarMain.grammar, String> configMap) {
        try {
            FileInputStream inStream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] set = line.split(DELIMITER);
                if (set.length != 2 && !set[1].contains(".txt")) {
                    Log.logReport("Invalid configuration file syntax.\n");
                }
                GrammarMain.grammar g = GrammarMain.grammarMap.get(set[0]);
                if (g == GrammarMain.grammar.OPTIONS) {
                    configMap.put(g, set[1]);
                    //classImplementer.readConfiguration(set[1], config, configOptions, freqTable);
                } else if (g == GrammarMain.grammar.INPUT || g == GrammarMain.grammar.OUTPUT) { //(input, output)
                    configMap.put(g, set[1]);
                } else {
                    Log.logReport("Invalid lexeme in configuration file.");
                }
            }
            if (configMap.putIfAbsent(GrammarMain.grammar.OUTPUT, OUTPUT_FILE) == null) {
                Log.logReport("Missing output file, using default output file -- output.txt\n");
            }
        } catch (Exception e) {
            Log.logReport("Error while reading config file.\n");
            return;
        }
        return;
    }
}
