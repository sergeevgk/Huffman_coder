import java.util.Map;

public class Options {
    private String fileName;

    public Options(String fileName) {
        this.fileName = fileName;
    }

    public void readOptions(Map<GrammarMain.grammar, String> configMain, Map<GrammarOptions.grammar, String> configOptions,
                            Map<Character, String> configTable) {
        ConfigInterpreter interpreterMain = new ConfigInterpreterMain(fileName);
        ConfigInterpreter interpreterOptions = new ConfigInterpreterOptions(fileName);
        ConfigInterpreter interpreterTable = new ConfigInterpreterTable(fileName);
        interpreterMain.readConfiguration(configMain);
        interpreterOptions.readConfiguration(configOptions);
        interpreterTable.readConfiguration(configTable);
    }
}
