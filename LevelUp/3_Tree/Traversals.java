import java.util.*;
public class Traversals {
    public static class TreeNode {
        TreeNode left = null;
        TreeNode right = null;
        int val = 0;
        TreeNode(int val){
            this.val = val;
        }
    }
    public static TreeNode getRightMost(TreeNode node, TreeNode curr){
        while(node.right!=null && node.right!=curr){
            node = node.right;
        }
        return node;
    }
    public static ArrayList<Integer> morrisTraversal(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = node;
        while (curr != null) {
            TreeNode left = node.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightMost = getRightMost(left, curr);
                if (rightMost.right == null) {
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    rightMost.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
    public static void inorder(TreeNode root, ArrayList<Integer> ans){
        if(root == null)
           return;
       inorder(root.left, ans);
       ans.add(root.val);
       inorder(root.right,ans);
       
   }
    public static boolean isValidBST_brute_force (TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        inorder(root,ans);
        for(int i = 0 ; i < ans.size()-1;i++){
            if(ans.get(i) >= ans.get(i+1))
                return false;
        }
        return true;
    }
    
    public  static boolean isValidBST(TreeNode root, TreeNode[] prev) {
        if(root == null)
            return true;
        if(!isValidBST(root.left,prev))
            return false;
        if(prev[0] != null && prev[0].val >= root.val)
            return false;
        prev[0] = root;
        if(!isValidBST(root.right,prev))
            return false;
        return true;
    }
    public static boolean isValidBST_Recursive (TreeNode root) {
        TreeNode[] prev =new TreeNode[1];
        return isValidBST(root,prev);
    }

    public static boolean isValidBST_morris_traversal (TreeNode root) {
        TreeNode curr = root;
        long prev = -(long)1e18;
        while(curr!=null){
            TreeNode left = curr.left;
            if(left == null){
                if(prev >= curr.val)
                   return false;
                prev = curr.val;
                curr = curr.right;
            }
            else {
                TreeNode rightMost = getRightMost(left,curr);
                if(rightMost.right == null){
                    rightMost.right = curr;
                    curr = curr.left;
                } else {
                    if(prev >= curr.val)
                       return false;
                    prev = curr.val;
                    curr = curr.right;  
                }
            }
        }
    }

}
