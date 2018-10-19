package huffman;

public class Node implements Comparable<Node> {
    Node left, right;
    private Character character;
    private int priority;

    public int getPriority() {
        return priority;
    }

    public Character getCharacter() {
        return character;
    }

    public Node(Character character, int priority, Node left, Node right) {
        this.priority = priority;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return this.character != null;
    }

    @Override
    public int compareTo(Node o) {
        return this.priority - o.priority;
    }
}


