package lyc.compiler.syntaxtree;

import lyc.compiler.symboltable.Symbol_lyc;

public class Node {
    private Symbol_lyc symbol;
    private Node left;
    private Node right;


    public Node(Symbol_lyc symbol, Node left, Node right) {
        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "symbol=" + symbol +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
