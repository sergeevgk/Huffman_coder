package config;

import java.util.Map;

public class Options {
    public Map<GrammarMain, String> configMain;
    public Map<GrammarOptions, String> configOptions;
    public Map<Character, Integer> configTable;

    public Options(Map<GrammarMain, String> configMain,
                   Map<GrammarOptions, String> configOptions,
                   Map<Character, Integer> configTable) {
        this.configMain = configMain;
        this.configOptions = configOptions;
        this.configTable = configTable;
    }
}
