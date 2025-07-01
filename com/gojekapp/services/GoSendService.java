package com.gojekapp.services;
import com.gojekapp.models.Riwayat;
import com.gojekapp.utils.DataInitializer;
import java.util.*;

public class GoSendService {
    // Demonstrasi: Disjoint Set (Union-Find), Map
    private final Scanner scanner;
    private Map<String, String> parent = new HashMap<>();
    private List<String> lokasiList = DataInitializer.getLokasiGoSend();

    public GoSendService(Scanner scanner) {
        this.scanner = scanner;
        for (String lokasi : lokasiList) {
            parent.put(lokasi, lokasi); // Each location is its own parent initially
        }
        parent.put("HUB-UTARA", "HUB-UTARA");
        parent.put("HUB-SELATAN", "HUB-SELATAN");
        parent.put("PUSAT-SORTIR", "PUSAT-SORTIR");

        // Simulating delivery routes
        union("Jebres", "HUB-UTARA");
        union("Banjarsari", "HUB-UTARA");
        union("Pasar Kliwon", "HUB-SELATAN");
        union("Serengan", "HUB-SELATAN");
        union("Laweyan", "HUB-SELATAN");
        union("HUB-UTARA", "PUSAT-SORTIR");
        union("HUB-SELATAN", "PUSAT-SORTIR");
    }

    private String find(String i) {
        if (parent.get(i).equals(i)) return i;
        return find(parent.get(i));
    }

    private void union(String i, String j) {
        String rootI = find(i);
        String rootJ = find(j);
        if (!rootI.equals(rootJ)) {
            parent.put(rootI, rootJ);
        }
    }

    private void printPath(String s) {
        if(parent.get(s).equals(s)) {
            System.out.print(s);
            return;
        }
        System.out.print(s + " -> ");
        printPath(parent.get(s));
    }

    public int handle(Map<Integer, Riwayat> riwayat) {
        System.out.println("\n--- GoSend ---");
        System.out.println("\nPilih Lokasi Pengiriman:");
        for(int i=0; i<lokasiList.size(); i++) {
             System.out.printf("%d. %s\n", i+1, lokasiList.get(i));
        }
        System.out.print("> Pilihan Lokasi: ");
        int pilihan = scanner.nextInt(); scanner.nextLine();
        String lokasi = lokasiList.get(pilihan-1);

        System.out.println("\nJalur Pengiriman via Pusat Sortir (Disjoint Set Path):");
        printPath(lokasi);
        
        int total = 15000;
        System.out.printf("\n\nBiaya Pengiriman Flat: Rp %,d\n", total);
        System.out.print("Setuju untuk mengirim? (y/n): ");
        if(scanner.nextLine().equalsIgnoreCase("y")) {
            riwayat.put(riwayat.size()+1, new Riwayat("GoSend", total));
            return total;
        }
        return 0;
    }
}
