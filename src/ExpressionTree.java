public class ExpressionTree {

    private final String RE;
    private Node root;

    public ExpressionTree(String RE) {
        this.RE = RE;
        this.root = null;
    }

    private void refreshRoot() {
        while(root.getParent() != null) root = root.getParent();
     }

     public void make() {
        create();
     }

     private boolean checkSign(char c) {
        return ((int)c >= 42 && (int)c <= 47);
     }

     private boolean checkNum(String c) {
         return ((int)c.charAt(0) >= 48 && (int)c.charAt(0) <= 57);
     }

     private int precedence(char c) {
         return switch ((int)c) {
             case 43 -> 0;
             case 45 -> 1;
             case 47 -> 2;
             case 42 -> 3;
             default -> -1;
         };
     }
     private void create() {
        for(int i=0; i<this.RE.length();) {
            if(checkSign(RE.charAt(i))) {
                char nextSign = RE.charAt(i++);
                insertSign(nextSign);
                refreshRoot();
            } else if(checkNum(String.valueOf(RE.charAt(i)))) {
                int nextNum = 0;
                do {
                    nextNum *= 10;
                    nextNum += Integer.parseInt(String.valueOf(RE.charAt(i++)));
                    if(i >= RE.length()) break;
                } while(!checkSign(RE.charAt(i)));
                insertNum(""+nextNum);
                refreshRoot();
            }
        }
     }

     private boolean insertSign(char c) {
        if(this.root == null) {
            this.root = new Node(String.valueOf(c),null);
            return true;
        } else {
            Node node = root;
            while (node.getLhsNode() != null && !checkSign(node.getValue().charAt(0))) {
                if(node.getRhsNode() != null) node = node.getRhsNode();
                else break;
            }
            while (node != null) {
                if (checkSign(node.getValue().charAt(0))) {
                    if (precedence(c) < precedence(node.getValue().charAt(0))) {
                        Node newNode = new Node(String.valueOf(c), node, null, node.getParent());
                        if (node.getParent() != null) node.getParent().setRhsNode(newNode);
                        return node.setParent(newNode);
                    } else {
                        if(node.getLhsNode() == null) {
                            Node n = new Node(String.valueOf(c),node);
                            return node.setLhsNode(n);
                        }
                        if (node.getRhsNode() == null)
                            return node.setRhsNode(new Node(String.valueOf(c),node));
                        node = node.getRhsNode();
                    }
                } else {
                    Node newNode = new Node(String.valueOf(c), node.getParent());
                    newNode.setLhsNode(node);
                    if(node.getParent() != null) node.getParent().setRhsNode(newNode);
                    return node.setParent(newNode);
                }
            }

        }
        return false;
     }

     private boolean insertNum(String c) {
         if(this.root == null) {
             this.root = new Node(c,null);
             return true;
         } else {
             Node node = root;
             while (node != null) {
                 if (checkSign(node.getValue().charAt(0))) {
                     if(node.getLhsNode() == null)
                         return node.setLhsNode(new Node(String.valueOf(c),node));
                     if (node.getRhsNode() == null)
                         return node.setRhsNode(new Node(String.valueOf(c),node));
                     node = node.getRhsNode();
                 }
             }
         }
        return false;
     }

     public void initializePrinting() {
        printTree(root);
     }
     private void printTree(Node n) {
        if(n != null) {
            System.out.print(n.getValue()+" ");
            printTree(n.getLhsNode());
            printTree(n.getRhsNode());
        }
     }

}
