package com.gojekapp.utils;

import com.gojekapp.models.*;
import com.gojekapp.structures.*;

import java.util.*;

public class DataInitializer {

    // =================================================================================
    // DATA UNTUK GoRide (Tree, Graph, Shortest Path)
    // =================================================================================
    public static List<String> getLokasiGoRide() {
        return Arrays.asList("Mojosongo", "Jebres", "Puncang Sawit", "Jagalan", "Purwodingratan", 
                              "Tegalharjo", "Kepatihan", "Sewu", "Gandekan", "Sudiroprajan");
    }

    public static int[][] getGoRideGraph() {
        return new int[][]{
            {0, 5, 0, 0, 0, 0, 0, 0, 0, 0}, {5, 0, 2, 3, 3, 4, 0, 0, 0, 0}, {0, 2, 0, 2, 0, 0, 0, 0, 0, 0},
            {0, 3, 2, 0, 2, 0, 0, 3, 0, 0}, {0, 3, 0, 2, 0, 1, 0, 0, 0, 2}, {0, 4, 0, 0, 1, 0, 1, 0, 0, 3},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 3, 0, 0, 0, 0, 2, 0}, {0, 0, 0, 0, 0, 0, 0, 2, 0, 1},
            {0, 0, 0, 0, 2, 3, 0, 0, 1, 0},
        };
    }

    public static LokasiTree createLokasiTree() {
        LokasiTree akar = new LokasiTree("Mojosongo", 1);
        LokasiTree anak_1 = new LokasiTree("Tegalharjo", 6);
        LokasiTree anak_2 = new LokasiTree("Jebres", 2);
        anak_1.insertChild(new LokasiTree("Kepatihan", 7));
        anak_2.insertChild(new LokasiTree("Puncang Sawit", 3));
        akar.insertChild(anak_2);
        akar.insertChild(anak_1);
        return akar;
    }

    // =================================================================================
    // DATA UNTUK GoCar (Graph, MST)
    // =================================================================================
     public static List<String> getLokasiGoCar() {
        return Arrays.asList("Jebres", "Pasar Kliwon", "Banjarsari", "Serengan", "Laweyan", "Boyolali", "Sukoharjo");
    }

    public static int[][] getGoCarGraph() {
        return new int[][]{
            {0,  7,  9,  0,  0,  0, 0 }, {7,  0, 12,  4,  0,  0, 10}, {9, 12,  0, 10,  8, 12, 0 },
            {0,  4, 10,  0,  8,  0, 10}, {0,  0,  8,  8,  0,  7, 10}, {0,  0, 12,  0,  7,  0, 15},
            {0, 10,  0, 10, 10, 10, 15}
        };
    }

    // =================================================================================
    // DATA UNTUK GoFood (List, Queue, Sorting)
    // =================================================================================
    public static List<Resto> getRestoList() {
        List<Resto> restoList = new ArrayList<>();
        
        // 1. Olive Fried Chicken
        List<Makanan> menuOlive = Arrays.asList(
            new Makanan("Paket Geprek", 15000), new Makanan("Paket Sayap", 12000), new Makanan("Kulit Krispi", 8000)
        );
        restoList.add(new Resto("Olive Fried Chicken", 1, 2.3, 4.9, menuOlive, new LinkedList<>(Arrays.asList(1,2,3))));
        
        // 2. Seblak Asgar
        List<Makanan> menuSeblak = Arrays.asList(
            new Makanan("Seblak Original", 12000), new Makanan("Seblak Seafood", 18000), new Makanan("Seblak Ceker", 15000)
        );
        restoList.add(new Resto("Seblak Asgar", 2, 1.8, 4.7, menuSeblak, new LinkedList<>(Arrays.asList(1,2,3,4))));

        // 3. Mie Gacoan
        List<Makanan> menuGacoan = Arrays.asList(
            new Makanan("Mie Angel", 10000), new Makanan("Mie Setan Lvl 1-4", 10500), new Makanan("Mie Iblis Lvl 1-4", 10500),
            new Makanan("Udang Keju", 9500), new Makanan("Lumpia Udang", 9500), new Makanan("Siomay Ayam", 9500),
            new Makanan("Es Gobak Sodor", 9500), new Makanan("Es Petak Umpet", 9500), new Makanan("Es Teh", 4000)
        );
        restoList.add(new Resto("Mie Gacoan", 3, 1.2, 4.8, menuGacoan, new LinkedList<>(Arrays.asList(1,2,3,4,5,6))));

        // 4. Waroeng Steak & Shake
        List<Makanan> menuWS = Arrays.asList(
            new Makanan("Chicken Steak", 22000), new Makanan("Sirloin Steak", 25000), new Makanan("Chicken Double", 29000),
            new Makanan("Milkshake Coklat", 12000), new Makanan("Milkshake Strawberry", 12000), new Makanan("Lemon Tea", 8000)
        );
        restoList.add(new Resto("Waroeng Steak & Shake", 4, 2.1, 4.6, menuWS, new LinkedList<>(Arrays.asList(1,2))));

        return restoList;
    }

    // =================================================================================
    // DATA UNTUK GoMart (Binary Search Tree, Stack)
    // =================================================================================
    public static MinimarketBST createMinimarketBST() {
        MinimarketBST bst = new MinimarketBST();
        bst.insert(1.5, "Indomaret Kartika", 4);
        bst.insert(2.4, "Alfamart Pedaringan", 4);
        bst.insert(3.2, "Alfamidi Kol. Sutarto", 5);
        bst.insert(1.4, "Red Palm Minimarket", 3);
        bst.insert(2.9, "Toko Kelontong Pak Min", 2);
        return bst;
    }

    public static List<MenuToko> getMenuToko() {
        return Arrays.asList(
            new MenuToko("Susu Kotak", 6000), new MenuToko("Roti Tawar", 15000), 
            new MenuToko("Mie Instan", 3500), new MenuToko("Air Mineral", 3000)
        );
    }
    
    // =================================================================================
    // DATA UNTUK GoSend (Disjoint Set, Map)
    // =================================================================================
    public static List<String> getLokasiGoSend() {
        return Arrays.asList("Jebres", "Pasar Kliwon", "Banjarsari", "Serengan", "Laweyan");
    }

    // =================================================================================
    // DATA UNTUK GoLife (Binary Tree)
    // =================================================================================
    public static LayananBinaryTree createLayananTree() {
        LayananBinaryTree root = new LayananBinaryTree("Pilih Tipe Layanan: Kebersihan (1) atau Pijat (2)?");
        
        // Cabang Kiri: Kebersihan
        root.setLeft(new LayananBinaryTree("Pilih Area: Seluruh Rumah (1) atau Kamar Saja (2)?"));
        root.getLeft().setLeft(new LayananBinaryTree("GoClean: Paket Seluruh Rumah (Rp 150.000)"));
        root.getLeft().setRight(new LayananBinaryTree("GoClean: Paket Kamar Saja (Rp 75.000)"));

        // Cabang Kanan: Pijat
        root.setRight(new LayananBinaryTree("Pilih Durasi: 1 Jam (1) atau 2 Jam (2)?"));
        root.getRight().setLeft(new LayananBinaryTree("GoMassage: Pijat 1 Jam (Rp 100.000)"));
        root.getRight().setRight(new LayananBinaryTree("GoMassage: Pijat 2 Jam (Rp 180.000)"));

        return root;
    }
}