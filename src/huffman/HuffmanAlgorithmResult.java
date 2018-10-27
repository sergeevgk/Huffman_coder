package huffman;

import java.util.Map;

public class HuffmanAlgorithmResult {
    private byte[] result;
    private Map<Byte, String> huffmanTree;
    private byte[] extraSymbols;

    public HuffmanAlgorithmResult(byte[] result, Map<Byte, String> huffmanTree, byte[] extraSymbols) {
        this.result = result;
        this.huffmanTree = huffmanTree;
        this.extraSymbols = extraSymbols;
    }

    public Map<Byte, String> getHuffmanTree() {
        return huffmanTree;
    }

    public byte[] getResult() {
        return result;
    }

    public byte[] getExtra(){
        return extraSymbols;
    }
}
