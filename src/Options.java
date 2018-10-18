import java.util.Map;

public class Options {
    public Map<GrammarMain.grammar, String> configMain;
    public Map<GrammarOptions.grammar, String> configOptions;
    public Map<Character, Integer> configTable;
    public Options(Map<GrammarMain.grammar, String> configMain, Map<GrammarOptions.grammar, String> configOptions,
                   Map<Character, Integer> configTable) {
        this.configMain = configMain;
        this.configOptions = configOptions;
        this.configTable = configTable;
    }

   /* public void readOptions(Map<GrammarMain.grammar, String> configMain, Map<GrammarOptions.grammar, String> configOptions,
                            Map<Character, String> configTable) {
        ConfigInterpreter interpreterMain = new ConfigInterpreterMain(fileName);
        ConfigInterpreter interpreterOptions = new ConfigInterpreterOptions(fileName);
        ConfigInterpreter interpreterTable = new ConfigInterpreterTable(fileName);
        interpreterMain.readConfiguration(configMain);
        interpreterOptions.readConfiguration(configOptions);
        interpreterTable.readConfiguration(configTable);
    }*/
}
