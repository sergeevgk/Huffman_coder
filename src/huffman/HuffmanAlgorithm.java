package huffman;

import java.util.*;

import config.GrammarOptions;
import config.Options;


public class HuffmanAlgorithm {
    private Map<GrammarOptions, String> configOptions;
    private Map<Character, String> huffmanTable;

    public HuffmanAlgorithm(Options options) {
        this.configOptions = options.configOptions;
        this.huffmanTable = options.huffmanTable;
    }

    public HuffmanAlgorithmResult startProcess(char[] source) {
        int codeMode = Integer.parseInt(configOptions.get(GrammarOptions.CODE_MODE));
        return processCoder(source, codeMode);
    }

    private HuffmanAlgorithmResult processCoder(char[] s, int mode) {
        switch (mode) {
            case 0:
                return encode(s);
            case 1:
                return decode(s);
        }
        return null;
    }

    private HuffmanAlgorithmResult encode(char[] source) {
        return new HuffmanAlgorithmResult(toHuffman(source, huffmanTable).toCharArray(), huffmanTable);
    }

    private String toHuffman(char[] source, Map<Character, String> huffmanTable) {
        StringBuilder s = new StringBuilder();
        for (char c : source) {
            if (c == 0)
                break;
            s.append(huffmanTable.get(c));
        }
        System.out.println(s);
        return s.toString();
    }

    private HuffmanAlgorithmResult decode(char[] source) {
        Map<String, Character> codeToCharMap = new HashMap<>();
        for (char key : huffmanTable.keySet()) {
            codeToCharMap.put(huffmanTable.get(key), key);
        }
        StringBuilder decodedString = new StringBuilder();
        StringBuilder tempString = new StringBuilder();
        for (char ch : source) {
            tempString.append(ch);
            if (codeToCharMap.containsKey(tempString.toString())) {
                decodedString.append(codeToCharMap.get(tempString.toString()));
                tempString = new StringBuilder();
            }
        }
        return new HuffmanAlgorithmResult(decodedString.toString().toCharArray(), huffmanTable);
    }
}
