package com.gojekapp.structures;
import java.util.Scanner;

public class LayananBinaryTree {
    private String data;
    private LayananBinaryTree left, right;

    public LayananBinaryTree(String data) { this.data = data; this.left = null; this.right = null; }
    public String getData() { return data; }
    public LayananBinaryTree getLeft() { return left; }
    public LayananBinaryTree getRight() { return right; }
    public void setLeft(LayananBinaryTree left) { this.left = left; }
    public void setRight(LayananBinaryTree right) { this.right = right; }
    public boolean isLeaf() { return left == null && right == null; }
    
    public LayananBinaryTree traverse(Scanner scanner) {
        LayananBinaryTree current = this;
        while (!current.isLeaf()) {
            System.out.println("\n" + current.getData());
            System.out.print("Pilihan Anda (1, 2, 0 = Batal): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 1) {
                current = current.getLeft();
            } else if (choice == 2) {
                current = current.getRight();
            } else if (choice == 0) {
                return null; 
            } else {
                System.out.println("Input tidak valid, coba lagi.");
            }
        }
        return current;
    }
}