public class ExpressionTree {

    private String RE;
    private Node root;

    public ExpressionTree(String RE) {
        this.RE = RE;
        this.root = null;
    }

    public String getRE() {
        return RE;
    }

    public void setRE(String RE) {
        this.RE = RE;
    }

    private void refreshRoot() {
        while(root.getParent() != null) root = root.getParent();
     }



}
