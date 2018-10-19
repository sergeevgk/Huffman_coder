import java.io.BufferedWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import log.Log;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.EOF;

public class Node {
    Node left, right;
    char character;
    private int priority;

    public int getPriority() {
        return priority;
    }

    public Node(char character, int priority) {
        this.priority = priority;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        //if (left == null) ... if (right == null) ...
        this.character = 0;
        this.priority = left.priority + right.priority;
        if (left.priority < right.priority) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }

    public Node(String fileName) { //creating tree from file
        // with huffman tree pre-saved
        char c;
        Deque<Node> st = new ArrayDeque<Node>();
        Scanner scan = new Scanner(fileName);
        while ((c = scan.next().charAt(0)) != EOF) {//!!!
            if (c == '1') {
                st.push(new Node(c, scan.next().charAt(0)));
            } else if (c == '0') {
                Node temp = st.pop();
                st.push(new Node(st.pop(), temp));
            }
        }
        Node tree = st.pop();
        this.priority = tree.priority;
        this.character = tree.character;
        this.left = tree.left;
        this.right = tree.right;
    }

    public boolean isLeaf() {
        return (this.left == null && this.right == null);
    }

    public void writeToFile(String fileName) {
        try {
            FileWriter outstream = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(outstream);
            String s = "";
            dfs(this, s);
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Log.logReport("Error while writing huffman tree to file");
        }
    }

    private static void dfs(Node n, String s) {
        if (n == null)
            return;
        dfs(n.left, s);
        if (n.isLeaf()) {
            s += "1";
        } else {
            s += "0";
        }
        dfs(n.right, s);
    }
}


