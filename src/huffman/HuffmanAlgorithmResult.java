package huffman;

import java.util.Map;

public class HuffmanAlgorithmResult {
    private char[] encodedSource;
    private Map<Character, String> huffmanTree;

    public HuffmanAlgorithmResult(char[] encodedSource, Map<Character, String> huffmanTree) {
        this.encodedSource = encodedSource;
        this.huffmanTree = huffmanTree;
    }

    public Map<Character, String> getHuffmanTree() {
        return huffmanTree;
    }

    public char[] getEncodedSource() {
        return encodedSource;
    }
}
