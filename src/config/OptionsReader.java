package config;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OptionsReader {
    private String fileName;

    public OptionsReader(String fileName) {
        this.fileName = fileName;
    }

    public Options readOptions() {
        Map<GrammarMain, String> configMain = new EnumMap<>(GrammarMain.class);
        Map<GrammarOptions, String> configOptions = new EnumMap<>(GrammarOptions.class);
        Map<Character, Integer> configTable = new HashMap<>();

        ConfigInterpreter<GrammarMain, String> interpreterMain = new ConfigInterpreterMain(fileName);
        ConfigInterpreter<GrammarOptions, String> interpreterOptions = new ConfigInterpreterOptions(fileName);
        ConfigInterpreter<Character, Integer> interpreterTable = new ConfigInterpreterTable(fileName);

        interpreterMain.readConfiguration(configMain);
        interpreterOptions.readConfiguration(configOptions);
        interpreterTable.readConfiguration(configTable);

        return new Options(configMain, configOptions, configTable);
    }
}
