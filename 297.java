/*
    6.29 audible onsite : 166/297/269
*/
public class Codec {
    /*
        level order, 3% performance
    */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        //String res = "[" + root.val;
        StringBuilder res = new StringBuilder('[').append(root.val);
        while (!que.isEmpty()) {
           int size = que.size();
           //String cur = "";
           StringBuilder res = new StringBuilder('');
           for (int i = 0; i < size; i++) {
               TreeNode tmp = que.poll();
               if (tmp.left != null) {
                   que.offer(tmp.left);
                   //cur += "," + tmp.left.val;
                   cur.append(',').append(tmp.left.val);
               } else {
                   //cur += ",null";
                   cur.append("null"); 
               }
               if (tmp.right != null) {
                   que.offer(tmp.right);
                   //cur += "," + tmp.right.val;
                   cur.append(',').append(tmp.right.val);
               } else {
                   //cur += ",null"; 
                   cur.append("null");
               }
           }
           if (que.size() > 0) {
               res += cur;
           }
        }
        return res + "]";
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() <= 2) {
            return null;
        }
        data = data.substring(1,data.length() - 1);
        String[] str = data.split(",");
        Integer tmp = Integer.valueOf(str[0]);
        TreeNode root = new TreeNode(tmp);
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        for (int i = 1; i < str.length;) {
            TreeNode cur = que.poll();
            if (i < str.length) {
                if (!str[i].equals("null")) {
                    tmp = Integer.valueOf(str[i]);
                    cur.left = new TreeNode(tmp);
                    que.offer(cur.left);
                }
                i++;
            }
           
            if (i < str.length) {
                if (!str[i].equals("null")) {
                    tmp = Integer.valueOf(str[i]);
                    cur.right = new TreeNode(tmp);
                    que.offer(cur.right);
                }
                i++;
            }
        }
        return root;
    }
}