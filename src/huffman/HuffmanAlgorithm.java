package huffman;

import java.util.*;

import config.GrammarOptions;
import config.Options;


public class HuffmanAlgorithm {
    private Map<GrammarOptions, String> configOptions;
    private Map<Byte, String> huffmanTable;

    public HuffmanAlgorithm(Options options) {
        this.configOptions = options.configOptions;
        this.huffmanTable = options.huffmanTable;
    }

    public HuffmanAlgorithmResult startProcess(byte[] source) {
        int codeMode = Integer.parseInt(configOptions.get(GrammarOptions.CODE_MODE));
        return processCoder(source, codeMode);
    }

    private HuffmanAlgorithmResult processCoder(byte[] s, int mode) {
        switch (mode) {
            case 0:
                return encode(s);
            case 1:
                return decode(s);
        }
        return null;
    }

    private HuffmanAlgorithmResult encode(byte[] source) {
        byte a[] = {};
        return new HuffmanAlgorithmResult(toHuffman(source, huffmanTable).getBytes(), huffmanTable, a);
    }

    private String toHuffman(byte[] source, Map<Byte, String> huffmanTable) {
        StringBuilder s = new StringBuilder();
        for (byte c : source) {
            /*if (c == 0)
                break;*/
            s.append(huffmanTable.get(c));
        }
        System.out.println(s);
        return s.toString();
    }

    private HuffmanAlgorithmResult decode(byte[] source) {
        Map<String, Byte> codeToCharMap = new HashMap<>();
        int maxCodeLength = 1;
        String temp;
        ArrayList <Byte> exSym = new ArrayList<>();
        ArrayList <Byte> decSym = new ArrayList<>();
        for (byte key : huffmanTable.keySet()) {
            codeToCharMap.put(temp = huffmanTable.get(key), key);
            if (maxCodeLength < temp.length())
                maxCodeLength = temp.length();
        }
        StringBuilder tempString = new StringBuilder();
        int currentIndex = 0;
        for (byte ch : source) {
            tempString.append((char)ch);
            currentIndex += 1;
            if (codeToCharMap.containsKey(tempString.toString())) {
                decSym.add(codeToCharMap.get(tempString.toString()));
                tempString = new StringBuilder();
                if (source.length - currentIndex < maxCodeLength) {
                    while (source.length - currentIndex < maxCodeLength && source.length - currentIndex  > 0) {
                        exSym.add(source[currentIndex]);
                        currentIndex += 1;
                    }
                    break;
                }
            }
        }
        byte[] extraSymbols = convertToPrimitive(exSym);
        byte[] decodedSymbols = convertToPrimitive(decSym);
        return new HuffmanAlgorithmResult(decodedSymbols, huffmanTable, extraSymbols);
    }
    private byte[] convertToPrimitive(ArrayList<Byte> list){
        byte[] array = new byte[list.size()];
        Byte[] Array = list.toArray(new Byte[list.size()]);
        int index = 0;
        for (Byte B: Array){
            array[index] = Array[index];
            index += 1;
        }
        return array;
    }
}

