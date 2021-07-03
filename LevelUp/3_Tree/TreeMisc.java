import java.util.*;
public class TreeMisc {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }
    // 1382. Balance a Binary Search Tree
    class Solution_01 {
        public TreeNode toBST(ArrayList<Integer> inorder, int i) {
            TreeNode root = new TreeNode(inorder.get(i));
        }

        public void inorder(TreeNode root, ArrayList<Integer> inorder) {
            if (root == null)
                return;
            inorder(root.left, inorder);
            inorder.add(root.val);
            inorder(root.right, inorder);
        }

        public TreeNode balanceBST(TreeNode root) {
            ArrayList<Integer> inorder = new ArrayList<Integer>;
            inorder(root,inorder);
            
        }
    }

    // 662. Maximum Width of Binary Tree
    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null)
                return 0;
            Queue<Pair<TreeNode, Integer>> que = new LinkedList<>();
            que.offer(new Pair<>(root, 0));
            int ans = 0;
            while (!que.isEmpty()) {
                int size = que.size();
                int start = 0;
                int end = 0;
                for (int i = 0; i < size; ++i) {
                    Pair<TreeNode, Integer> temp = que.poll();
                    TreeNode node = temp.getKey();
                    Integer idx = temp.getValue();
                    // System.out.println(node.val + " " + idx);
                    if (i == 0)
                        start = idx;
                    if (i == size - 1)
                        end = idx;

                    if (node.left != null)
                        que.offer(new Pair<>(node.left, 2 * idx + 1));
                    if (node.right != null)
                        que.offer(new Pair<>(node.right, (2 * idx) + 2));
                }
                int val = end - start + 1;
                ans = Math.max(ans, val);
            }
            return ans;
        }
    }

    // 449. Serialize and Deserialize BST
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            sb.append(root.val).append(" ");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.length() == 0) {
                return null;
            }
            String[] nodes = data.split(" ");
            int[] index = new int[] { 0 };
            return deserialize(nodes, index, Integer.MAX_VALUE);
        }

        private TreeNode deserialize(String[] nodes, int[] index, int max) {
            if (index[0] >= nodes.length || Integer.valueOf(nodes[index[0]]) >= max) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(nodes[index[0]++]));
            root.left = deserialize(nodes, index, root.val);
            root.right = deserialize(nodes, index, max);
            return root;
        }
    }
    //leetcode 510
    public Node inorderSuccessor(Node node) {

        Node succ = null;
        Node right = node.right;
        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }

            return right;
        }

        while (node.parent != null && node.parent.left != node) {
            node = node.parent;
        }

        return node.parent;
    }
    // HW : Check if given Preorder, Inorder and Postorder traversals are of same tree
} 
