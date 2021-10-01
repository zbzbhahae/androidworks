package com.zb.leetcode.structs;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.zb.leetcode.interfaces.BSTI;
import com.zb.leetcode.utils.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree<E> implements BSTI<E>, BinaryTreeInfo {

    private int size;
    private Node<E> root;
    private Comparator comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        if(null == root) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        //找到要插入的父节点
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while (null != node) {
            parent = node;
            cmp = compare(element, node.element);
            if(cmp > 0) {
                node = node.rightNode;
            } else if(cmp < 0) {
                node = node.leftNode;
            } else {
                //覆盖当前的值
                Node current = new Node(element, node.parentNode);
                current.leftNode = node.leftNode;
                current.rightNode = node.rightNode;
                node.leftNode = null;
                node.rightNode = null;
                node.parentNode = null;
                return;
            }
        }
        if(cmp > 0) {
            parent.rightNode = new Node(element, parent);
        } else if(cmp < 0) {
            parent.leftNode = new Node(element, parent);
        }
        size++;
    }

    @Override
    public void remove(E element) {
        if(null == element || null == root)
            return;
        //找到要删除的父节点
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while (null != node) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.rightNode;
            } else if (cmp < 0) {
                node = node.leftNode;
            } else {
                //TODO 删除root节点逻辑有问题
                //找到了相等的节点
                size--;//树大小-1
                parent = node.parentNode;
                boolean isDeleteLeftNode = false;
                if(parent.leftNode == node)
                    isDeleteLeftNode = true;

                //取出要删除节点的左右子树
                Node leftNode = node.leftNode;
                Node rightNode = node.rightNode;
                //置空要删除节点的信息
                node.leftNode = null;
                node.rightNode = null;
                node.parentNode = null;
                node.element = null;
                //将左右子树的parent信息置空 分三种情况
                //当前节点的左右子树都不存在，直接返回
                //当前节点只存在左子树或右子树 直接将子树的parent设置为当前节点的parent
                //当前节点的左右子树都存在，则将左子树的parent设置为当前节点parent并
                //把右子树挂到左子树的最右边
                //或者将右子树的parent设置为当前节点的parent，将左子树挂到右子树的最左边

                if(leftNode == null && rightNode == null) {
                    return;
                } else if(leftNode != null && rightNode != null) {
                    //删除节点的左右子树都存在，将左子树parent设置为删除节点的parent
                    //将右子树挂到左子树的最右下
//                    leftNode.parentNode = parent;
                    //将左子树和删除节点的parent建立连结
                    linkToParent(parent, leftNode, isDeleteLeftNode);
                    //寻找左子树的最右下角
                    Node newRightNode = leftNode.rightNode;
                    Node newRightNodeParent = leftNode;
                    while(null != newRightNode) {
                        newRightNodeParent = newRightNode;
                        newRightNode = newRightNode.rightNode;
                    }
                    //将右子树与左子树最右下角连接起来
                    rightNode.parentNode = newRightNodeParent;
                    newRightNodeParent.rightNode = rightNode;
                } else if(leftNode == null) {
                    linkToParent(parent, rightNode, isDeleteLeftNode);
                } else {
                    linkToParent(parent, leftNode, isDeleteLeftNode);
                }
                return;
            }
        }
    }

    private void linkToParent(Node<E> parent, Node<E> leaf, boolean isLeftNode) {
        leaf.parentNode = parent;
        if(isLeftNode) {
            parent.leftNode = leaf;
        } else {
            parent.rightNode = leaf;
        }
    }

    @Override
    public boolean contains(E element) {
        if(null == element || null == root)
            return false;
        if(compare(element, root.element) == 0)
            return true;
        //找到要插入的父节点
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while (null != node) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.rightNode;
            } else if (cmp < 0) {
                node = node.leftNode;
            } else {
                //找到了相等的节点
                return true;
            }
        }


        return false;
    }

    /**
     * 返回值等于0 两元素相等；
     * 返回值大于0，e1>e2
     * 返回值小于0，e1<e2
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if(null != comparator)
            return comparator.compare(e1, e2);

        return ((Comparable<E>) e1).compareTo(e2);
//        return e1.compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if(element == null)
            throw new IllegalArgumentException("Binary Search Tree : element must not be null~!");
    }

    public void traversal(Visitor<E> visitor) {
        traversalFromhead2(root, visitor);
    }

    /**
     * 前序遍历
     * 遍历顺序 节点-左子树-右子树
     */
    public void traversalFromHead(Node<E> node, Visitor<E> visitor) {
        if(null == node || null == visitor || visitor.stop)
            return;
        visitor.stop = visitor.visit(node.element);
        traversalFromHead(node.leftNode,  visitor);
        traversalFromHead(node.rightNode,  visitor);
    }
    /**
     * 用栈实现非递归
     * 前序遍历
     */
    public void traversalFromhead2(Node<E> node, Visitor<E> visitor) {
        if(null == node)
            return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        while ( (null == visitor || !visitor.stop) && stack.size() > 0 ) {
            Node<E> n = stack.pop();
            visitor.stop = visitor.visit(n.element);
            if(n.rightNode != null)
                stack.push(n.rightNode);
            if(n.leftNode != null)
                stack.push(n.leftNode);
        }
    }

    /**
     * 中序遍历
     * 遍历顺序 左子树-节点-右子树
     */
    public void traversalFromCenter(Node<E> node, Visitor<E> visitor) {
        if(null == node || null == visitor || visitor.stop)
            return;
        traversalFromCenter(node.leftNode,  visitor);
        if(visitor.stop)
            return;
        visitor.stop = visitor.visit(node.element);
        traversalFromCenter(node.rightNode, visitor);
    }

    /**
     * 后序遍历
     * 遍历顺序 左子树-节点-右子树
     */
    public void traversalFromBack(Node<E> node, Visitor<E> visitor) {
        if(null == node || null == visitor || visitor.stop)
            return;
        traversalFromBack(node.leftNode, visitor);
        traversalFromBack(node.rightNode, visitor);
        if(visitor.stop)//这里必须再次判断 防止上面两句改变stop 这里还继续打印的情况
            return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 层序遍历
     */
    public void traversalLevelOrder(Visitor<E> visitor) {
        if(null == visitor)
            return;
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (true) {
            Node<E> tem = queue.poll();
            if(visitor.visit(tem.element)) {
                return;
            }
            if (tem.leftNode != null) {
                queue.offer(tem.leftNode);
            }
            if (tem.rightNode != null) {
                queue.offer(tem.rightNode);
            }
            if(0 == queue.size())
                return;
        }
    }
    private void print(Node<E> node) {
        System.out.println(node.element + "");
    }

    public int height() {
        return height2(root);
    }

    private int height(Node<E> node) {
        if(node == null)
            return 0;
        int height = 1+Math.max(height(node.leftNode), height(node.rightNode));
        return height;
    }

    /**
     * 使用层序遍历来计算树的高度
     * 当该层最后一个节点从队列拿出来后，队列中存放的将是下一层所有的节点
     *
     */
    private int height2(Node<E> node) {
        if(null == node)
            return 0;
        int height = 0;
        //每层的节点数量
        int levelSize = 1;
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> tem = queue.poll();
            levelSize--;
            if (tem.leftNode != null) {
                queue.offer(tem.leftNode);
            }
            if (tem.rightNode != null) {
                queue.offer(tem.rightNode);
            }
            if(levelSize == 0) {
                height ++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /**
     * 判断二叉树是否是完全二叉树
     * 看右边单空个数， 且一旦出现单左边的情况，那么队列中剩下的节点
     * 必须为叶子节点
     * 如果出现左右均为空的情况，那么剩下的也必然是叶子节点
     */
    public boolean isCompleteTree() {
        if(null == root) return false;

        boolean leftFlag = true;
        boolean rightFlag = true;
        boolean leafMode = false;
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> tem = queue.poll();
            if (tem.leftNode != null) {
                queue.offer(tem.leftNode);
                leftFlag = true;
                if(leafMode)
                    return false;
            }
            if (tem.rightNode != null) {
                queue.offer(tem.rightNode);
                rightFlag = true;
                if(leafMode)
                    return false;
            }
            if(!leftFlag && rightFlag)
                return false;
            if((leftFlag && !rightFlag) || (!leftFlag && !rightFlag)) {
                leafMode = true;
            }
            leftFlag = false;
            rightFlag = false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String result = toString(root, sb, "");
        return result;
    }

    private String toString(Node<E> node, StringBuilder sb, String prefix) {
        if(null == node) return "";
        sb.append(prefix).append(node.element).append("\n");
        toString(node.leftNode, sb, prefix + "[L]--");
        toString(node.rightNode, sb, prefix + "[R]--");
        return sb.toString();
    }

    /**
     * 打印机接口方法
     */
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).rightNode;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

    public static abstract class Visitor<E> {
        boolean stop = false;
        //返回true表示停止遍历
        public abstract boolean visit(E element);
    }

    private static class Node<E> {
        E element;
        Node leftNode;
        Node rightNode;
        Node parentNode;

        public Node(E element, Node parentNode) {
            this.element = element;
            this.parentNode = parentNode;
        }

        public boolean isLeaf() {
            return leftNode == null && rightNode == null;
        }
        public boolean isFull() {
            return leftNode != null && rightNode != null;
        }
        public boolean isSingleLeft() {
            return leftNode != null && rightNode == null;
        }
        public boolean isSingleRight() {
            return leftNode == null && rightNode != null;
        }

    }
}
