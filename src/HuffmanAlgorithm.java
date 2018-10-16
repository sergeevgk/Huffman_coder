import java.io.*;
import java.util.*;

public class HuffmanAlgorithm {
    private BufferedReader reader;
    private BufferedWriter writer;
    private static Map<ConfigInterpreter.GRAMMAR, String> config = new EnumMap<>(ConfigInterpreter.GRAMMAR.class);
    private Map<ConfigInterpreterOptions.GRAMMAR, String> configOptions = new EnumMap<>(ConfigInterpreterOptions.GRAMMAR.class);
    private Map<Character, Integer> freqTable = new HashMap<>();
    private static ConfigInterpreter interpConfig = new ConfigInterpreter();

    public final void StartProcess(String fileName) throws Exception {
        if (interpConfig.ReadConfiguration(fileName, config, configOptions, freqTable)) {//open config file to read coder options
            ProcessFile(config, configOptions, freqTable);
        } else {
            Exception e = new Exception("Missing configuration file name.\n");
            throw e;
        }
    }

    private final boolean OpenFile(String fileName, String mode) {
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
                    Log.LogReport("Open file error.");
                    return false;
            }
        } catch (Exception e) {
            Log.LogReport("Open file error.");
            return false;
        }
        return true;
    }

    private final void ProcessFile(Map<ConfigInterpreter.GRAMMAR, String> config, Map<ConfigInterpreterOptions.GRAMMAR, String> configOptions, Map<Character, Integer> freqTable) throws IOException {
        int bufSize = Integer.parseInt(configOptions.get(ConfigInterpreterOptions.GRAMMAR.BUFFER_SIZE));
        char[] buf = new char[bufSize];
        String inputFileName = config.get(ConfigInterpreter.GRAMMAR.INPUT);
        String outputFileName = config.get(ConfigInterpreter.GRAMMAR.OUTPUT);
        if (!OpenFile(inputFileName, "r")) {
            return;
        }
        if (!OpenFile(outputFileName, "w")) {
            return;
        }
        int mode = Integer.parseInt(configOptions.get(ConfigInterpreterOptions.GRAMMAR.CODE_MODE));
        while ((reader.read(buf)) != -1) {
            writer.write(ProcessCoder(buf, mode, freqTable, config, configOptions));
            writer.flush();
        }
        reader.close();
        writer.close();
    }

    private static final String ProcessCoder(char[] s, int mode, Map<Character, Integer> freqTable, Map<ConfigInterpreter.GRAMMAR, String> config, Map<ConfigInterpreterOptions.GRAMMAR, String> configOptions) {
        switch (mode) {
            case 0:
                //System.out.println(s);
                return (Encode(s, freqTable, config, configOptions));
            case 1:
                return (Decode(s, freqTable, config, configOptions));
        }
        return null;
    }

    private static final String Encode(char[] source, Map<Character, Integer> freqTable, Map<ConfigInterpreter.GRAMMAR, String> config, Map<ConfigInterpreterOptions.GRAMMAR, String> configOptions) {
        int i = 0;
        String s = "";
        //build priority queue
        PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
        Set<Character> keySet = freqTable.keySet();
        for (char c : keySet) {
            //System.out.println(freqTable.get(c).intValue());
            Node n = new Node(c, freqTable.get(c));
            System.out.println(n.GetPriority());
            queue.add(n);
        }
        //build tree, save tree
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll()));
        }
        Node tree = queue.poll(); // save to extra file
        tree.WriteToFile(configOptions.get(ConfigInterpreterOptions.GRAMMAR.HUFFMAN_TREE));
        //build table sym-code, (!)save table
        HashMap<Character, String> huffmanTable = BuildHuffmanTable(tree);
        return toHuffman(source, huffmanTable);
    }

    private static Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return (int) (o1.GetPriority() - o2.GetPriority());
        }
    };

    private static final HashMap<Character, String> BuildHuffmanTable(Node tree) {
        HashMap<Character, String> map = new HashMap<>();
        //int i = 0;
        String s = "";
        Traverse(tree, s, map);
        return map;
    }

    private static final void Traverse(Node tree, String code, HashMap<Character, String> map) {
        if (tree.isLeaf()) {
            map.put(tree.character, code);
            return;
        }
        if (tree.left != null) {
            Traverse(tree.left, code + "0", map);
        }
        if (tree.right != null) {
            Traverse(tree.right, code + "1", map);
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

    private static final String Decode(char[] source, Map<Character, Integer> freqTable, Map<ConfigInterpreter.GRAMMAR, String> config, Map configOptions) {
        String s = "";

        //get tree form config/string
        //build priority queue
        //build tree, save tree
        //build table sym-code, save table
        //toHuffman()
        return s;
    }
}
