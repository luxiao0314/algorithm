package test;

/**
 * @Description
 * @Author lux
 * @Date 2023/3/8 10:22 上午
 * @Version
 */
public class TreeNode {

    public int root;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "root=" + root +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
