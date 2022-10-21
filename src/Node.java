public class Node {
    private String value;
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

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLhsNode() {
        return lhsNode;
    }

    public void setLhsNode(Node lhsNode) {
        this.lhsNode = lhsNode;
    }

    public Node getRhsNode() {
        return rhsNode;
    }

    public void setRhsNode(Node rhsNode) {
        this.rhsNode = rhsNode;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
