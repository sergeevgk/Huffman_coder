import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OptionsReader{
    private String fileName;
    public OptionsReader(String fileName){
        this.fileName = fileName;
    }
    public Options readOptions(){
        Map<GrammarMain.grammar, String> configMain = new EnumMap<GrammarMain.grammar, String>(GrammarMain.grammar.class);
        Map<GrammarOptions.grammar, String> configOptions = new EnumMap<GrammarOptions.grammar, String>(GrammarOptions.grammar.class);
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
