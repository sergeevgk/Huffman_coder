import java.io.*;
import java.util.*;

public class HuffmanAlgorithm {
    private static Map<GrammarMain.Grammar, String> configMain;
    private Map<GrammarOptions.Grammar, String> configOptions;
    private Map<Character, Integer> freqTable;

    public HuffmanAlgorithm(Options options) {
        this.configMain = options.configMain;
        this.configOptions = options.configOptions;
        this.freqTable = options.configTable;
    }

    public final char[] startProcess(char[] source, Options options) {
        Integer codeMode = Integer.parseInt(configOptions.get(GrammarOptions.Grammar.CODE_MODE));
        return processCoder(source, codeMode);
    }

    private final char[] processCoder(char[] s, int mode) {
        switch (mode) {
            case 0:
                //System.out.println(s);
                return (encode(s));
            case 1:
                return (decode(s));
        }
        return null;
    }

    private final char[] encode(char[] source) {
        int i = 0;
        //build priority queue
        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        Set<Character> keySet = freqTable.keySet();
        for (char c : keySet) {
            //System.out.println(freqTable.get(c).intValue());
            Node n = new Node(c, freqTable.get(c));
            System.out.println(n.getPriority());
            queue.add(n);
        }
        //build tree, save tree
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll()));
        }
        Node tree = queue.poll(); // save to extra file
        tree.writeToFile(configOptions.get(GrammarOptions.Grammar.HUFFMAN_TREE));
        //build table sym-code, (!)save table
        HashMap<Character, String> huffmanTable = buildHuffmanTable(tree);
        return toHuffman(source, huffmanTable).toCharArray();
    }

    private static Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return (int) (o1.getPriority() - o2.getPriority());
        }
    };

    private static final HashMap<Character, String> buildHuffmanTable(Node tree) {
        HashMap<Character, String> map = new HashMap<>();
        //int i = 0;
        String s = "";
        traverse(tree, s, map);
        return map;
    }

    private static final void traverse(Node tree, String code, HashMap<Character, String> map) {
        if (tree.isLeaf()) {
            map.put(tree.character, code);
            return;
        }
        if (tree.left != null) {
            traverse(tree.left, code + "0", map);
        }
        if (tree.right != null) {
            traverse(tree.right, code + "1", map);
        }
    }

    private static final String toHuffman(char[] source, HashMap<Character, String> huffmanTable) {
        StringBuilder s = new StringBuilder();
        System.out.println(source);
        for (char c : source) {
            if (c == 0)
                break;
            System.out.println(huffmanTable.get(c));
            s.append(huffmanTable.get(c));
        }
        System.out.println(s);
        return s.toString();
    }

    private final char[] decode(char[] source) {
        char[] buf;

        //get tree form config/string
        //build priority queue
        //build tree, save tree
        //build table sym-code, save table
        //toHuffman()
        return source;
    }
}
