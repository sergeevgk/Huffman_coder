package huffman;

import java.util.Map;

public class HuffmanAlgorithmResult {
    private char[] result;
    private Map<Character, String> huffmanTree;

    public HuffmanAlgorithmResult(char[] result, Map<Character, String> huffmanTree) {
        this.result = result;
        this.huffmanTree = huffmanTree;
    }

    public Map<Character, String> getHuffmanTree() {
        return huffmanTree;
    }

    public char[] getResult() {
        return result;
    }
}
