import java.util.*;

public class l003ConstructionSet {

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

    public static TreeNode constructFromInOrder(int[] inOrder, int si, int ei) {
        if (si > ei)
            return null;

        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(inOrder[mid]);

        root.left = constructFromInOrder(inOrder, si, mid - 1);
        root.right = constructFromInOrder(inOrder, mid + 1, ei);

        return root;
    }

    static int idx = 0;

    public static TreeNode bstFromPreorder(int[] preorder, int lr, int rr) {
        if (idx == preorder.length || preorder[idx] < lr || preorder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(preorder[idx++]);

        node.left = bstFromPreorder(preorder, lr, node.val);
        node.right = bstFromPreorder(preorder, node.val, rr);

        return node;
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstFromPreorder(preorder, -(int) 1e9, (int) 1e9);
    }

    public static TreeNode bstFromPostOrder(int[] postOrder, int lr, int rr) {
        if (idx == -1 || postOrder[idx] < lr || postOrder[idx] > rr)
            return null;

        TreeNode node = new TreeNode(postOrder[idx--]);

        node.right = bstFromPreorder(postOrder, node.val, rr);
        node.left = bstFromPreorder(postOrder, lr, node.val);

        return node;
    }

    public static TreeNode bstFromPostOrder(int[] postOrder) {
        idx = postOrder.length - 1;
        return bstFromPostOrder(postOrder, -(int) 1e9, (int) 1e9);
    }

    public static class bstLPair {
        TreeNode par = null;
        int lr = 0;
        int rr = 0;

        bstLPair(TreeNode par, int lr, int rr) {
            this.par = par;
            this.lr = lr;
            this.rr = rr;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        if (LevelOrder.length == 0)
            return null;
        LinkedList<bstLPair> que = new LinkedList<>();
        TreeNode root = new TreeNode(LevelOrder[0]);
        int idx = 1;

        que.addLast(new bstLPair(root, -(int) 1e9, root.val));
        que.addLast(new bstLPair(root, root.val, (int) 1e9));

        while (idx < LevelOrder.length) {
            bstLPair rp = que.removeFirst();
            int lr = rp.lr, rr = rp.rr;
            if (LevelOrder[idx] < lr || LevelOrder[idx] > rr)
                continue;

            TreeNode node = new TreeNode(LevelOrder[idx++]);
            if (node.val < rp.par.val)
                rp.par.left = node;
            else
                rp.par.right = node;

            que.addLast(new bstLPair(node, rp.lr, node.val));
            que.addLast(new bstLPair(node, node.val, rp.rr));
        }

        return root;
    }

    public static TreeNode preOrIn(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != pre[psi])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(pre[psi]);

        root.left = preOrIn(pre, psi + 1, psi + tel, in, isi, idx - 1);
        root.right = preOrIn(pre, psi + tel + 1, pei, in, idx + 1, iei);

        return root;
    }

    public static TreeNode postOrIn(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (isi > iei)
            return null;
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tel = idx - isi;
        TreeNode root = new TreeNode(post[pei]);

        root.left = postOrIn(post, psi, psi + tel - 1, in, isi, idx - 1);
        root.right = postOrIn(post, psi + tel, pei - 1, in, idx + 1, iei);

        return root;
    }

    // 889. Construct Binary Tree from Preorder and Postorder Traversal
    class Solution {
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            int n = pre.length;
            return construct(pre, 0, n - 1, post, 0, n - 1);
        }

        public TreeNode construct(int[] pre, int psi, int pei, int[] post, int ppsi, int ppei) {
            if (psi > pei)
                return null;

            TreeNode root = new TreeNode(pre[psi]);
            if (psi == pei)
                return root;
            int idx = ppsi;
            while (post[idx] != pre[psi + 1])
                idx++;

            int tnel = idx - ppsi + 1;
            root.left = construct(pre, psi + 1, psi + tnel, post, ppsi, idx);
            root.right = construct(pre, psi + tnel + 1, pei, post, idx + 1, ppei + 1);

            return root;
        }
    }

    public static TreeNode getLeftMost(TreeNode node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static TreeNode getRightMost(TreeNode node) {
        if (node == null)
            return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static void predecessorSuccessor(TreeNode node, int data) {
        TreeNode curr = node, predecessor = null, successor = null;
        while (curr != null) {
            if (node.val == data) {
                TreeNode leftMost = getLeftMost(node.right);
                successor = leftMost != null ? leftMost : successor;

                TreeNode rigthMost = getRightMost(node.left);
                predecessor = rigthMost != null ? rigthMost : predecessor;
            } else if (node.val < data) {
                predecessor = node;
                curr = curr.right;
            } else {
                successor = node;
                curr = curr.left;
            }
        }
    }

    // Construct tree from Inorder and LevelOrder
    class GfG {
        int search(int[] level, HashSet<Integer> hs, int k) {
            for (int i = k; i < level.length; i++) {
                if (hs.contains(level[i]))
                    return i;
            }
            return -1;
        }

        Node buildTree(int inord[], int level[], int isi, int iei, int lcurr, HashMap<Integer, Integer> map) {
            if (isi <= iei && lcurr != -1) {
                int mid = map.get(level[lcurr]);
                Node root = new Node(level[lcurr]);
                HashSet<Integer> ls = new HashSet<>();
                HashSet<Integer> rs = new HashSet<>();
                for (int i = isi; i < mid; i++)
                    ls.add(inord[i]);
                for (int i = mid; i <= iei; i++)
                    rs.add(inord[i]);
                int lind = search(level, ls, lcurr + 1);
                int rind = search(level, rs, lcurr + 1);
                root.left = buildTree(inord, level, isi, mid - 1, lind, map);
                root.right = buildTree(inord, level, mid, iei, rind, map);
                return root;
            }
            return null;
        }

        Node buildTree(int inord[], int level[]) {
            // your code here
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int i = 0; i < inord.length; i++)
                map.put(inord[i], i);
            return buildTree(inord, level, 0, inord.length - 1, 0, map);
        }
    }

    

}