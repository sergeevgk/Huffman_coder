import java.io.*;
import java.util.*;

public class HuffmanAlgorithm {
    private BufferedReader reader;
    private BufferedWriter writer;
    private static Map<ConfigInterpreterMain.grammar, String> config = new EnumMap<>(ConfigInterpreterMain.grammar.class);
    private Map<ConfigInterpreterOptions.grammar, String> configOptions = new EnumMap<>(ConfigInterpreterOptions.grammar.class);
    private Map<Character, Integer> freqTable = new HashMap<>();
    private static ConfigInterpreterMain interpConfig = new ConfigInterpreterMain();

    public final void startProcess(String fileName) throws Exception {
        if (interpConfig.readConfiguration(fileName, config, configOptions, freqTable)) {//open config file to read coder options
            processFile(config, configOptions, freqTable);
        } else {
            Exception e = new Exception("Missing configuration file name.\n");
            throw e;
        }
    }

    private final boolean openFile(String fileName, String mode) {
        try {
            switch (mode) {
                case "r":
                    FileInputStream instream = new FileInputStream(fileName);
                    reader = new BufferedReader(new InputStreamReader(instream));
                    break;

                case "w":
                    FileOutputStream outstream = new FileOutputStream(fileName);
                    writer = new BufferedWriter(new OutputStreamWriter(outstream));
                    break;
                default:
                    Log.logReport("Open file error.");
                    return false;
            }
        } catch (Exception e) {
            Log.logReport("Open file error.");
            return false;
        }
        return true;
    }

    private final void processFile(Map<ConfigInterpreterMain.grammar, String> config, Map<ConfigInterpreterOptions.grammar, String> configOptions, Map<Character, Integer> freqTable) throws IOException {
        int bufSize = Integer.parseInt(configOptions.get(ConfigInterpreterOptions.grammar.BUFFER_SIZE));
        char[] buf = new char[bufSize];
        String inputFileName = config.get(ConfigInterpreterMain.grammar.INPUT);
        String outputFileName = config.get(ConfigInterpreterMain.grammar.OUTPUT);
        if (!openFile(inputFileName, "r")) {
            return;
        }
        if (!openFile(outputFileName, "w")) {
            return;
        }
        int mode = Integer.parseInt(configOptions.get(ConfigInterpreterOptions.grammar.CODE_MODE));
        while ((reader.read(buf)) != -1) {
            writer.write(processCoder(buf, mode, freqTable, config, configOptions));
            writer.flush();
        }
        reader.close();
        writer.close();
    }

    private static final String processCoder(char[] s, int mode, Map<Character, Integer> freqTable, Map<ConfigInterpreterMain.grammar, String> config, Map<ConfigInterpreterOptions.grammar, String> configOptions) {
        switch (mode) {
            case 0:
                //System.out.println(s);
                return (encode(s, freqTable, config, configOptions));
            case 1:
                return (decode(s, freqTable, config, configOptions));
        }
        return null;
    }

    private static final String encode(char[] source, Map<Character, Integer> freqTable, Map<ConfigInterpreterMain.grammar, String> config, Map<ConfigInterpreterOptions.grammar, String> configOptions) {
        int i = 0;
        String s = "";
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
        tree.writeToFile(configOptions.get(ConfigInterpreterOptions.grammar.HUFFMAN_TREE));
        //build table sym-code, (!)save table
        HashMap<Character, String> huffmanTable = buildHuffmanTable(tree);
        return toHuffman(source, huffmanTable);
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

    private static final String decode(char[] source, Map<Character, Integer> freqTable, Map<ConfigInterpreterMain.grammar, String> config, Map configOptions) {
        String s = "";

        //get tree form config/string
        //build priority queue
        //build tree, save tree
        //build table sym-code, save table
        //toHuffman()
        return s;
    }
}
