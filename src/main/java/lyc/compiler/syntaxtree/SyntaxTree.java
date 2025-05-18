package lyc.compiler.syntaxtree;

import lyc.compiler.symboltable.Symbol_lyc;

public class SyntaxTree {
    private Node root;
    private int count;

    public SyntaxTree() {
        this.root = null;
    }

    
    public SyntaxTree(Node root) {
        this.root = root;
    }

    public Node createNode(Symbol_lyc data, Node left, Node right) {
        Node node = new Node(data, left, right);

        return node;
    }

    public Node createLeaf(Symbol_lyc data) {
        Node node = new Node(data, null, null);

        return node;
    }
}