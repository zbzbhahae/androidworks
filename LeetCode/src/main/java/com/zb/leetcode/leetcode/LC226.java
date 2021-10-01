package com.zb.leetcode.leetcode;

import java.util.LinkedList;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，
 * 但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */


public class LC226 {



    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        TreeNode node = invertTree2(root);
        int a;
    }

    static TreeNode invertTree(TreeNode root) {
        if(null == root)
            return null;

        LinkedList<TreeNode> data = new LinkedList<>();
        LinkedList<TreeNode> parentCache = new LinkedList<>();
        data.offer(root);
        int levelSize = 1;
        TreeNode nullNode = new TreeNode();
        while (data.size() != 0)  {
            TreeNode node = data.poll();
            parentCache.addFirst(node);
            if(null != node.left) {
                data.offer(node.left);
            } else {
                data.offer(nullNode);
            }
            if(null != node.right) {
                data.offer(node.right);
            } else {
                data.offer(nullNode);
            }
            levelSize--;
            if(0 == levelSize) {//该层结束
                System.out.println("结束一层 size : " + data.size());
                levelSize = data.size();
                //将data中的节点取出 翻转
                for(int i=0; i<data.size()/2; i++) {
                    TreeNode node1 = data.get(i);
                    TreeNode node2 = data.get(data.size()-1-i);
                    if(nullNode == node1 && nullNode == node2) {
                        //要交换的都是空节点 需要给父节点赋空值
                        int parent1Index = i/2;
                        int parent2Index = (data.size()-1-i) /2;
                        TreeNode parent1 = parentCache.get(parent1Index);
                        TreeNode parent2 = parentCache.get(parent2Index);
                        if(i % 2 == 0) {
                            //node1是左节点 node2是右节点
                            parent1.left = null;
                            parent2.right = null;
                        } else {
                            parent1.right = null;
                            parent2.left = null;
                        }
                    } else if(node1 == nullNode) {
                        TreeNode tem = node2;
                        //从parentCache中拿到父节点
                        int parent1Index = i/2;
                        int parent2Index = (data.size()-1-i) /2;
                        TreeNode parent1 = parentCache.get(parent1Index);
                        TreeNode parent2 = parentCache.get(parent2Index);
                        if(i % 2 == 0) {
                            //node1是左节点 node2是右节点
                            parent1.left = tem;
                            parent2.right = null;
                        } else {
                            parent1.right = tem;
                            parent2.left = null;
                        }
                    } else if(node2 == nullNode){
                        TreeNode tem = node1;
                        //从parentCache中拿到父节点
                        int parent1Index = i/2;
                        int parent2Index = (data.size()-1-i) /2;
                        TreeNode parent1 = parentCache.get(parent1Index);
                        TreeNode parent2 = parentCache.get(parent2Index);
                        if(i % 2 == 0) {
                            //node1是左节点 node2是右节点
                            parent1.left = null;
                            parent2.right = tem;
                        } else {
                            parent1.right = null;
                            parent2.left = tem;
                        }
                    } else {
                        int parent1Index = i/2;
                        int parent2Index = (data.size()-1-i) /2;
                        TreeNode parent1 = parentCache.get(parent1Index);
                        TreeNode parent2 = parentCache.get(parent2Index);
                        TreeNode tem = node1;
                        if(i % 2 == 0) {
                            //node1是左节点 node2是右节点
                            parent1.left = node2;
                            parent2.right = tem;
                        } else {
                            parent1.right = node2;
                            parent2.left = tem;
                        }
//                        int tem = node1.val;
//                        node1.val = node2.val;
//                        node2.val = tem;
                    }
                }
                parentCache.clear();
                //检查data中是否都是nullNode 看看是不是遍历完成了
                boolean notNullFlag = false;
                for(TreeNode n : data) {
                    if(nullNode != n) {
                        System.out.printf("不是nullNode : " + n.val);
                        notNullFlag = true;
                        break;
                    }
                    System.out.printf("是nullNode");
                }
                if(!notNullFlag)
                    return root;
            }
        }
        return root;
    }

    /**
     * 答案
     * @param root
     */
    static TreeNode invertTree2(TreeNode root) {
        if(null == root)
            return root;

        LinkedList<TreeNode> data = new LinkedList<>();
        data.offer(root);

        while (data.size() != 0) {
            TreeNode node = data.poll();
            //交换节点   每一层每个节点都要交换一次  上一层交换后的顺序会影响到下一层
            TreeNode tem = node.left;
            node.left = node.right;
            node.right = tem;

            //一定要交换完再添加
            if(null != node.left)
                data.offer(node.left);
            if(null != node.right)
                data.offer(node.right);
        }

        return root;
    }



    static void printTree(TreeNode root) {
        if(null == root)
            return;
    }
}
class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
     public TreeNode() {}
     public TreeNode(int val) { this.val = val; }
     public TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}