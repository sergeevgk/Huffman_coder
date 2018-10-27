package huffman;

import log.Log;

import java.io.*;
import java.util.*;

public class HuffmanTableBuilder {
    public Map<Byte, Integer> frequencyTable = new HashMap<>();
    private String inputFileName;

    public HuffmanTableBuilder(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    private Map<Byte, Integer> createFreqTable(byte[] source) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte ch : source) {
            if (!map.containsKey(ch)) {
                map.put(ch, 0);
            }
            int value = map.get(ch);
            map.put(ch, value + 1);
        }
        return map;
    }
    public Map<Byte, String> BuildHuffmanTable(){
        try {
            FileInputStream inStream = new FileInputStream(inputFileName);
            BufferedInputStream reader = new BufferedInputStream(inStream);
            int c;
            byte b;
            while ((c = reader.read()) != -1) {
                b = (byte)c;
                if (!frequencyTable.containsKey(b)) {
                    frequencyTable.put(b, 0);
                }
                int value = frequencyTable.get(b);
                frequencyTable.put(b, value + 1);
            }
            Queue<Node> queue = new PriorityQueue<>();
            for (byte ch : frequencyTable.keySet()) {
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
    private HashMap<Byte, String> buildHuffmanTable(Node tree) {
        HashMap<Byte, String> map = new HashMap<>();
        traverse(tree, "", map);
        return map;
    }

    private void traverse(Node tree, String code, Map<Byte, String> map) {
        if (tree == null) {
            return;
        }
        if (tree.isLeaf()) {
            if (code.isEmpty()) {
                map.put(tree.getByte(), "0");
            } else {
                map.put(tree.getByte(), code);
            }
            return;
        }
        traverse(tree.left, code + "0", map);
        traverse(tree.right, code + "1", map);
    }
}
