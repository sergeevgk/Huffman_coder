import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OptionsReader {
    private String fileName;

    public OptionsReader(String fileName) {
        this.fileName = fileName;
    }

    public Options readOptions() {
        Map<GrammarMain.Grammar, String> configMain = new EnumMap<GrammarMain.Grammar, String>(GrammarMain.Grammar.class);
        Map<GrammarOptions.Grammar, String> configOptions = new EnumMap<GrammarOptions.Grammar, String>(GrammarOptions.Grammar.class);
        Map<Character, Integer> configTable = new HashMap<>();
        ConfigInterpreter interpreterMain = new ConfigInterpreterMain(fileName);
        ConfigInterpreter interpreterOptions = new ConfigInterpreterOptions(fileName);
        ConfigInterpreter interpreterTable = new ConfigInterpreterTable(fileName);
        interpreterMain.readConfiguration(configMain);
        interpreterOptions.readConfiguration(configOptions);
        interpreterTable.readConfiguration(configTable);
        return new Options(configMain, configOptions, configTable);
    }
}
