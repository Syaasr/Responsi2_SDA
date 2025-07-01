package com.gojekapp.models;
import java.util.List;
import java.util.Queue;
public class Resto {
    public String nama; public int harga; public double jarak; public double rating;
    public List<Makanan> listMakanan; public Queue<Integer> antrian;
    public Resto(String n, int h, double j, double r, List<Makanan> lm, Queue<Integer> a) {
        nama=n; harga=h; jarak=j; rating=r; listMakanan=lm; antrian=a;
    }
}