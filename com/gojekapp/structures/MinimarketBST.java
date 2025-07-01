package com.gojekapp.structures;
import java.util.ArrayList;
import java.util.List;

public class MinimarketBST {
    public class Node {
        double jarak; String nama; int rating; Node left, right;
        Node(double j, String n, int r) { jarak=j; nama=n; rating=r; left=right=null; }
        
        public String getNama() { return nama; }
        public double getJarak() { return jarak; }
        public int getRating() { return rating; }
    }
    private Node root;

    public void insert(double j, String n, int r) { root = insertRec(root, j, n, r); }
    private Node insertRec(Node root, double j, String n, int r) {
        if (root == null) return new Node(j, n, r);
        if (j < root.jarak) root.left = insertRec(root.left, j, n, r);
        else if (j > root.jarak) root.right = insertRec(root.right, j, n, r);
        return root;
    }

    public List<Node> getNodesInOrder() {
        List<Node> list = new ArrayList<>();
        collectInOrder(root, list);
        return list;
    }
    
    private void collectInOrder(Node node, List<Node> list) {
        if (node != null) {
            collectInOrder(node.left, list);
            list.add(node);
            collectInOrder(node.right, list);
        }
    }
}