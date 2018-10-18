import java.util.Map;

public class Options {
    public Map<GrammarMain.Grammar, String> configMain;
    public Map<GrammarOptions.Grammar, String> configOptions;
    public Map<Character, Integer> configTable;

    public Options(Map<GrammarMain.Grammar, String> configMain, Map<GrammarOptions.Grammar, String> configOptions,
                   Map<Character, Integer> configTable) {
        this.configMain = configMain;
        this.configOptions = configOptions;
        this.configTable = configTable;
    }
}
