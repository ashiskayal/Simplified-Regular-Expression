public class Node {
    private final String value;
    private Node lhsNode, rhsNode, parent;

    public Node(String value, Node lhsNode, Node rhsNode, Node parent) {
        this.value = value;
        this.lhsNode = lhsNode;
        this.rhsNode = rhsNode;
        this.parent = parent;
    }

    public Node(String value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public Node getLhsNode() {
        return lhsNode;
    }

    public boolean setLhsNode(Node lhsNode) {
        this.lhsNode = lhsNode;
        return true;
    }

    public Node getRhsNode() {
        return rhsNode;
    }

    public boolean setRhsNode(Node rhsNode) {
        this.rhsNode = rhsNode;
        return true;
    }

    public Node getParent() {
        return parent;
    }

    public boolean setParent(Node parent) {
        this.parent = parent;
        return true;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
