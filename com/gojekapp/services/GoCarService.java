package com.gojekapp.services;
import com.gojekapp.models.Riwayat;
import com.gojekapp.utils.DataInitializer;
import java.util.*;

public class GoCarService {
    // Demonstrasi: MST (Prim), Graph
    private final Scanner scanner;
    private final List<String> lokasiList = DataInitializer.getLokasiGoCar();
    private final int[][] graph = DataInitializer.getGoCarGraph();
    private final int MAP_SIZE = lokasiList.size();

    public GoCarService(Scanner scanner) { this.scanner = scanner; }

    private void primMST() {
        int[] parent = new int[MAP_SIZE];
        int[] key = new int[MAP_SIZE];
        boolean[] mstSet = new boolean[MAP_SIZE];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < MAP_SIZE - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < MAP_SIZE; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }
        printMST(parent);
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < MAP_SIZE; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }
    
    private void printMST(int[] parent) {
        System.out.println("\nMenghitung rute efisien yang menghubungkan titik-titik utama...");
        for (int i = 1; i < MAP_SIZE; i++)
            System.out.printf("- Rute %s ke %s (Jarak: %d)\n", lokasiList.get(parent[i]), lokasiList.get(i), graph[i][parent[i]]);
    }

    public int handle(Map<Integer, Riwayat> riwayat) {
        System.out.println("\n--- GoCar ---"); 
        primMST();
        
        System.out.println("\nLayanan GoCar menggunakan tarif flat untuk perjalanan antar titik utama.");
        System.out.println("Tarif: Rp 50,000");
        System.out.print("\nSetuju untuk memesan GoCar? (y/n): ");
        String persetujuan = scanner.nextLine();
        
        if (persetujuan.equalsIgnoreCase("y")) {
            int total = 50000;
            riwayat.put(riwayat.size() + 1, new Riwayat("GoCar", total));
            return total;
        }
        return 0;
    }
}