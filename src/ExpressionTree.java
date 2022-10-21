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

     private boolean checkSign(char c) {
        if((int)c == 46) return false;
        return ((int)c >= 42 && (int)c <= 47);
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
     public void make() {
        for(int i=0; i<this.RE.length();) {
            if(checkSign(RE.charAt(i))) {
                char nextSign = RE.charAt(i++);
                insertSign(nextSign);
                refreshRoot();
            } else {
                StringBuilder nextNum = new StringBuilder(5);
                do {
                    nextNum.append(RE.charAt(i++));
                    if(i >= RE.length()) break;
                } while(!checkSign(RE.charAt(i)));
                insertNum(String.valueOf(nextNum));
                refreshRoot();
            }
        }
     }

     private boolean insertSign(char c) {
        if(this.root != null) {
            Node node = root;
            while (node.getLhsNode() != null && !checkSign(node.getValue().charAt(0))) {
                if(node.getRhsNode() != null) node = node.getRhsNode();
                else break;
            }
            while (node != null) {
                if (checkSign(node.getValue().charAt(0))) {
                    if (precedence(c) < precedence(node.getValue().charAt(0))) {
                        return createNInsert(node, String.valueOf(c));
                    } else if(checkLR(node,String.valueOf(c))) return true;
                    node = node.getRhsNode();
                } else
                    return createNInsert(node,String.valueOf(c));
            }
        }
         this.root = new Node(String.valueOf(c),null);
         return true;
     }

     private boolean insertNum(String c) {
         if(this.root != null) {
             Node node = root;
             while (node != null) {
                 if (checkSign(node.getValue().charAt(0))) {
                     if(checkLR(node,c)) return true;
                     node = node.getRhsNode();
                 }
             }
         }
         this.root = new Node(c,null);
         return true;
     }

    private boolean createNInsert(Node node, String c) {
        Node newNode = new Node(c, node, null, node.getParent());
        if(node.getParent() != null) node.getParent().setRhsNode(newNode);
        return node.setParent(newNode);
    }

    private boolean checkLR(Node node, String c) {
        if(node.getLhsNode() == null) return node.setLhsNode(new Node(c,node));
        if (node.getRhsNode() == null) return node.setRhsNode(new Node(c,node));
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