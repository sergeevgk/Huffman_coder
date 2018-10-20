package config;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import huffman.HuffmanTableBuilder;
import log.Log;

public class OptionsReader {
    private String fileName;

    public OptionsReader(String fileName) {
        this.fileName = fileName;
    }

    public Options readOptions() {
        Map<GrammarMain, String> configMain = new EnumMap<>(GrammarMain.class);
        Map<GrammarOptions, String> configOptions = new EnumMap<>(GrammarOptions.class);
        Map<Character, String> huffmanTable = new HashMap<>();

        ConfigInterpreter<GrammarMain, String> interpreterMain = new ConfigInterpreterMain(fileName);
        interpreterMain.readConfiguration(configMain);
        fillDefaultMain(configMain);

        ConfigInterpreter<GrammarOptions, String> interpreterOptions = new ConfigInterpreterOptions(configMain.get(GrammarMain.OPTIONS));
        interpreterOptions.readConfiguration(configOptions);
        fillDefaultOptions(configOptions);
        if (configOptions.get(GrammarOptions.CODE_MODE).equals("1")) {
            ConfigInterpreter<Character, String> interpreterTable = new ConfigInterpreterHuffmanTree(configOptions.get(GrammarOptions.HUFFMAN_TABLE));
            interpreterTable.readConfiguration(huffmanTable);
        } else if (configOptions.get(GrammarOptions.CODE_MODE).equals("0")) {
            HuffmanTableBuilder huffmanTableBuilder = new HuffmanTableBuilder(configMain.get(GrammarMain.IN));
            huffmanTable = huffmanTableBuilder.BuildHuffmanTable();
        }
        return new Options(configMain, configOptions, huffmanTable);
    }

    private void fillDefaultMain(Map<GrammarMain, String> configMain) {
        if (configMain.putIfAbsent(GrammarMain.IN, "input.txt") == null) {
            Log.logReport("Missing input file. Using default one.");
        }
        if (configMain.putIfAbsent(GrammarMain.OUT, "output.txt") == null) {
            Log.logReport("Missing output file. Using default one.");
        }
        if (configMain.putIfAbsent(GrammarMain.OPTIONS, "options.txt") == null) {
            Log.logReport("Missing options file. Using default one.");
        }
    }

    private void fillDefaultOptions(Map<GrammarOptions, String> configOptions) {
        if (configOptions.putIfAbsent(GrammarOptions.BUFFER_SIZE, "frequency.txt") == null) {
            Log.logReport("Missing frequency table file. Using default one.");
        }
        if (configOptions.putIfAbsent(GrammarOptions.FREQUENCY_TABLE, "frequency.txt") == null) {
            Log.logReport("Missing frequency table file. Using default one.");
        }
        /*if (configOptions.putIfAbsent(GrammarOptions.HUFFMAN_TABLE, "huffmanTable.txt") == null) {
            Log.logReport("Missing huffman table file. Using default one.");
        }*/
    }
}

