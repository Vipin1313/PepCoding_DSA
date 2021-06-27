public class AVLTree {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        int bal = 0;
        int height = 0;

        TreeNode(int val) {
            this.val = val;
            this.bal = 0;
            this.height = 0;
        }
    }

    public static void updateHeightBal(TreeNode node) {
        int lh = node.left != null ? node.left.height : -1;
        int rh = node.right != null ? node.right.height : -1;
        node.height = Math.max(lh, rh) + 1;
        node.bal = lh - rh;
    }

    public static TreeNode rotateRight(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BkaRight = B.right;

        B.right = A;
        A.left = BkaRight;

        updateHeightBal(B);
        updateHeightBal(A);

        return B;
    }

    public static TreeNode rotateLeft(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BkaLeft = B.left;

        B.left = A;
        A.right = BkaLeft;

        updateHeightBal(A);
        updateHeightBal(B);

        return B;
    }

    public static TreeNode getRotation(TreeNode node) {
        updateHeightBal(node);

        if (node.bal == 2) { // ll , lr
            if (node.left.bal == 1) { // ll
                return rotateRight(node);
            } else { // lr
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (node.val == -2) {
            if (node.right.bal == -1) { // rr
                return rotateLeft(node);
            } else { // rl
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }

    // =========================================================================================================

    public static int max(TreeNode root) {
        if (root.right != null)
            return max(root.right);
        else
            return root.val;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else {
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maxData = max(root.left);
            root.val = maxData;
            root.left = deleteNode(root.left, maxData);
        }
        root = getRotation(root);
        return root;
    }

    public static TreeNode add(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            root.right = add(root.right, val);
        else
            root.left = add(root.left, val);
        root = getRotation(root);
        return root;
    }

    public static void display(TreeNode node) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append(" -> " + node.val + " <- ");
        sb.append((node.right != null ? node.right.val : "."));

        System.out.println(sb.toString());

        display(node.left);
        display(node.right);

    }

    public static void main(String[] args) {
        TreeNode root = null;
        for (int i = 1; i <= 100; i++) {
            root = add(root, i * 10);
        }
        display(root);
    }
    
}