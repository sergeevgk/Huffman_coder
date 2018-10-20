package huffman;

import log.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HuffmanTableBuilder {
    public Map<Character, Integer> frequencyTable = new HashMap<>();
    private String inputFileName;

    public HuffmanTableBuilder(String inputFileName) {
        this.inputFileName = inputFileName;
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
    public Map<Character, String> BuildHuffmanTable(){
        int b;
        try {
            FileInputStream inStream = new FileInputStream(inputFileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            Integer count;
            char c;
            while ((b = reader.read()) != -1) {
                count = frequencyTable.get(c = (char) b);
                if (!frequencyTable.containsKey(c)) {
                    frequencyTable.put((char)b, 0);
                }
                int value = frequencyTable.get(c);
                frequencyTable.put(c, value + 1);
            }
            Queue<Node> queue = new PriorityQueue<>();
            for (char ch : frequencyTable.keySet()) {
                Node n = new Node(ch, frequencyTable.get(ch), null, null);
                queue.add(n);
            }

            while (queue.size() > 1) {
                Node first = queue.poll();
                Node second = queue.poll();
                queue.add(new Node(null, first.getPriority() + second.getPriority(), first, second));
            }
            Node tree = queue.poll();

            return buildHuffmanTable(tree);
        } catch (IOException e){
            Log.logReport("Error while building huffman table occurred.");
            return null;
        }
    }
    private HashMap<Character, String> buildHuffmanTable(Node tree) {
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
}
