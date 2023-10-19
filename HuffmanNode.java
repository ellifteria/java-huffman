public class HuffmanNode implements Comparable{
    
    private Character val;
    private HuffmanNode left, right;
    private int count;

    public HuffmanNode()
    {
        val = null;
        left = null;
        right = null;
        count = 0;
    }

    public HuffmanNode(Character val) {
        this.val = val;
        left = null;
        right = null;
        count = 0;
    }

    public HuffmanNode(Character val, HuffmanNode left) {
        this.val = val;
        this.left = left;
        right = null;
        count = 0;
    }

    public HuffmanNode(Character val, HuffmanNode left, HuffmanNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
        count = 0;
    }

    public HuffmanNode(Character val, int count) {
        this.val = val;
        left = null;
        right = null;
        this.count = count;
    }

    public HuffmanNode(int count, HuffmanNode left, HuffmanNode right)
    {
        val = null;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public HuffmanNode getRight() {
        return right;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public Character getValue() {
        return val;
    }

    public void setValue(Character v) {
        val = v;
    }

    public void setLeft(HuffmanNode l) {
        left = l;
    }

    public void setRight(HuffmanNode r) {
        right = r;
    }

    public int getCount()
    {
        return count;
    }

    public int compareTo(Object o)
    {
        return count - ((HuffmanNode)o).getCount();
    }

    public String toString() {
        return "" + val + " " + count;
    }

}
