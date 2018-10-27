package config;

import java.util.Map;

public class Options {
    public Map<GrammarMain, String> configMain;
    public Map<GrammarOptions, String> configOptions;
    public Map<Byte, String> huffmanTable;

    public Options(Map<GrammarMain, String> configMain,
                   Map<GrammarOptions, String> configOptions,
                   Map<Byte, String> huffmanTable) {
        this.configMain = configMain;
        this.configOptions = configOptions;
        this.huffmanTable = huffmanTable;
    }
}
