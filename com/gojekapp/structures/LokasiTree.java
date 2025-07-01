package com.gojekapp.structures;
import java.util.ArrayList;
import java.util.List;

public class LokasiTree {
    private String nama; 
    private int id;
    private List<LokasiTree> children = new ArrayList<>();
    public LokasiTree(String nama, int id) { this.nama = nama; this.id = id; }
    public void insertChild(LokasiTree child) { this.children.add(child); }
    public void displayPreOrder(int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.printf("|-- %s (ID: %d)%n", nama, id);
        for (LokasiTree child : children) {
            child.displayPreOrder(depth + 1);
        }
    }
}
