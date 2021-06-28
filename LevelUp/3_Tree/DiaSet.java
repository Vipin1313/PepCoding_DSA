import java.util.*;
public class DiaSet {
    public static class TreeNode {
        TreeNode left = null;
        TreeNode right = null;
        int val = 0;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        Node left = null;
        Node right = null;
        int val = 0;

        Node(int val) {
            this.val = val;
        }
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int diaOfBT_01(TreeNode root) {
        if (root == null)
            return 0;
        int ld = diaOfBT_01(root.left);
        int rd = diaOfBT_01(root.right);

        int lh = height(root.left);
        int rh = height(root.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // {diameter,height}
    public static int[] diaOfBT_02(TreeNode root) {
        if (root == null)
            return new int[] { 0, -1 };

        int[] lp = diaOfBT_02(root.left);
        int[] rp = diaOfBT_02(root.right);

        int[] myRes = new int[2];
        myRes[0] = Math.max(Math.max(lp[0], rp[0]), lp[1] + rp[0] + 2);
        myRes[1] = Math.max(lp[1], rp[1]) + 1;

        return myRes;
    }

    public static int diaOfBT_03(TreeNode root, int[] dia) {
        if (root == null)
            return 0;
        int lh = diaOfBT_03(root.left, dia);
        int rh = diaOfBT_03(root.right, dia);

        dia[0] = Math.max(dia[0], lh + rh + 2);

        return Math.max(lh, rh) + 1;
    }
    // 112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
            
         else if(root.left == null && root.right == null && sum - root.val == 0)  return true;
            
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // 113 brute force
    public void hasPathSum(TreeNode root, int sum, ArrayList<Integer> smallAns, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
            
        smallAns.add(root.val);
        if(root.left == null && root.right == null && sum - root.val == 0) {
            ans.add(smallAns);
            return;
        }
            
        hasPathSum(root.left, sum - root.val, new ArrayList<Integer>(smallAns), ans);
        hasPathSum(root.right, sum - root.val, new ArrayList<Integer>(smallAns), ans);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        hasPathSum(root,sum,new ArrayList<Integer>(),ans);
        return ans;
    }

    // 113 optimised 

    public void hasPathSum_02(TreeNode root, int sum, ArrayList<Integer> smallAns, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        
        if(root.left == null && root.right == null && sum - root.val == 0) {
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }
           
        smallAns.add(root.val);
        hasPathSum(root.left, sum - root.val, smallAns, ans);
        hasPathSum(root.right, sum - root.val, smallAns, ans);
        
        smallAns.remove(smallAns.size()-1);
    }
    
    public List<List<Integer>> pathSum_02(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        hasPathSum(root,sum,new ArrayList<Integer>(),ans);
        return ans;
    }

    
}
