package huffman;

import java.util.*;

import config.GrammarOptions;
import config.Options;


public class HuffmanAlgorithm {
    private Map<GrammarOptions, String> configOptions;
    private Map<Character, String> huffmanTree;

    public HuffmanAlgorithm(Options options) {
        this.configOptions = options.configOptions;
        this.huffmanTree = options.huffmanTree;
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

    private Map<Character, Integer> createFreqTable(char[] source) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : source) {
            if (!map.containsKey(ch)) {
                map.put(ch, 0);
            }
            int value = map.get(ch);
            map.put(ch, value + 1);
        }
        return map;
    }

    private HuffmanAlgorithmResult encode(char[] source) {
        Map<Character, Integer> frequencyTable = createFreqTable(source);

        Queue<Node> queue = new PriorityQueue<>();
        for (char c : frequencyTable.keySet()) {
            Node n = new Node(c, frequencyTable.get(c), null, null);
            queue.add(n);
        }

        while (queue.size() > 1) {
            Node first = queue.poll();
            Node second = queue.poll();
            queue.add(new Node(null, first.getPriority() + second.getPriority(), first, second));
        }
        Node tree = queue.poll();

        Map<Character, String> huffmanTree = buildHuffmanTree(tree);
        return new HuffmanAlgorithmResult(toHuffman(source, huffmanTree).toCharArray(), huffmanTree);
    }

    private HashMap<Character, String> buildHuffmanTree(Node tree) {
        HashMap<Character, String> map = new HashMap<>();
        traverse(tree, "", map);
        return map;
    }

    private void traverse(Node tree, String code, Map<Character, String> map) {
        if (tree == null) {
            return;
        }
        if (tree.isLeaf()) {
            if (code.isEmpty()) {
                map.put(tree.getCharacter(), "0");
            } else {
                map.put(tree.getCharacter(), code);
            }
            return;
        }
        traverse(tree.left, code + "0", map);
        traverse(tree.right, code + "1", map);
    }

    private String toHuffman(char[] source, Map<Character, String> huffmanTable) {
        StringBuilder s = new StringBuilder();
        for (char c : source) {
            s.append(huffmanTable.get(c));
        }
        return s.toString();
    }

    private HuffmanAlgorithmResult decode(char[] source) {
        Map<String, Character> codeToCharMap = new HashMap<>();
        for (char key : huffmanTree.keySet()) {
            codeToCharMap.put(huffmanTree.get(key), key);
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
        return new HuffmanAlgorithmResult(decodedString.toString().toCharArray(), huffmanTree);
    }
}
