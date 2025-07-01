package com.gojekapp.services;
import com.gojekapp.models.Riwayat;
import com.gojekapp.structures.LokasiTree;
import com.gojekapp.utils.DataInitializer;
import java.util.*;

public class GoRideService {
    // Demonstrasi: Shortest Path (Dijkstra), Graph (Adjacency Matrix), Tree (General)
    private final Scanner scanner;
    private final List<String> lokasiList = DataInitializer.getLokasiGoRide();
    private final int[][] graph = DataInitializer.getGoRideGraph();
    private final LokasiTree lokasiTree = DataInitializer.createLokasiTree();
    private final int MAP_SIZE = lokasiList.size();

    public GoRideService(Scanner scanner) { this.scanner = scanner; }

    private int[] dijkstra(int src) {
        int[] dist = new int[MAP_SIZE];
        boolean[] sptSet = new boolean[MAP_SIZE];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int count = 0; count < MAP_SIZE - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < MAP_SIZE; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        return dist;
    }

    private int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < MAP_SIZE; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    public int handle(Map<Integer, Riwayat> riwayat) throws InterruptedException {
        System.out.println("\n--- GoRide ---");
        System.out.println("\nPilih Lokasi Penjemputan (Hirarki Area):");
        lokasiTree.displayPreOrder(0);
        
        System.out.print("\n> Masukkan ID Tempat Jemput: ");
        int lokasi = scanner.nextInt();
        
        System.out.println("\nMenghitung Jarak Terpendek dari " + lokasiList.get(lokasi - 1) + "...");
        int[] distances = dijkstra(lokasi - 1);
        System.out.println("\n| ID | Lokasi Tujuan      | Jarak Terpendek |");
        System.out.println("|----|--------------------|-----------------|");
        for (int i = 0; i < MAP_SIZE; i++) {
             System.out.printf("| %-2d | %-18s | %-15s |%n", i + 1, lokasiList.get(i), distances[i] == Integer.MAX_VALUE ? "Tak Terjangkau" : distances[i] + " km");
        }
        
        System.out.print("\n> Masukkan ID Tempat Tujuan: ");
        int tujuan = scanner.nextInt();
        scanner.nextLine();

        if (distances[tujuan-1] == Integer.MAX_VALUE) {
            System.out.println("Tujuan tidak terjangkau dari lokasi penjemputan.");
            Thread.sleep(2000);
            return 0;
        }

        int total = 2500 * distances[tujuan - 1];
        System.out.printf("\nPerjalanan dari %s -> %s (Jarak: %d km)", lokasiList.get(lokasi - 1), lokasiList.get(tujuan - 1), distances[tujuan-1]);
        System.out.printf("\nTotal Biaya: Rp %,d. Setuju? (y/n): ", total);
        String persetujuan = scanner.nextLine();
        
        if (persetujuan.equalsIgnoreCase("y")) {
            riwayat.put(riwayat.size() + 1, new Riwayat("GoRide", total));
            return total;
        }
        return 0;
    }
}
