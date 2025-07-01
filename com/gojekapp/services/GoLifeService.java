package com.gojekapp.services;
import com.gojekapp.models.Riwayat;
import com.gojekapp.structures.LayananBinaryTree;
import com.gojekapp.utils.DataInitializer;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoLifeService {
    private final Scanner scanner;
    private final LayananBinaryTree layananTree = DataInitializer.createLayananTree();

    public GoLifeService(Scanner scanner) { this.scanner = scanner; }

    public int handle(Map<Integer, Riwayat> riwayat) {
        System.out.println("\n--- GoLife ---");
        System.out.println("Ikuti petunjuk untuk memilih layanan.");

        // Diubah: Memanggil traverse yang baru, dan cek jika null
        LayananBinaryTree resultNode = layananTree.traverse(scanner);
        
        if (resultNode == null) {
            System.out.println("\nPemesanan GoLife dibatalkan.");
            return 0;
        }

        String result = resultNode.getData();
        System.out.println("\nLayanan yang Anda pilih: " + result);

        Pattern pattern = Pattern.compile("Rp ([0-9.,]+)");
        Matcher matcher = pattern.matcher(result);
        int total = 0;
        if (matcher.find()) {
            total = Integer.parseInt(matcher.group(1).replaceAll("[.,]", ""));
        }

        System.out.printf("\nTotal Biaya: Rp %,d\n", total);
        System.out.print("Setuju untuk memesan? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            riwayat.put(riwayat.size() + 1, new Riwayat("GoLife", total));
            return total;
        }
        return 0;
    }
}