package config;

import java.util.Map;

public class Options {
    public Map<GrammarMain, String> configMain;
    public Map<GrammarOptions, String> configOptions;
    public Map<Character, String> huffmanTree;

    public Options(Map<GrammarMain, String> configMain,
                   Map<GrammarOptions, String> configOptions,
                   Map<Character, String> huffmanTree) {
        this.configMain = configMain;
        this.configOptions = configOptions;
        this.huffmanTree = huffmanTree;
    }
}
