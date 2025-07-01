package com.gojekapp.main;

import com.gojekapp.models.Riwayat;
import com.gojekapp.services.*;
import com.gojekapp.utils.ConsoleUtil;

import java.util.*;

// Syaikhasril Maulana Firdaus
// L0124077

public class GojekApp {
    private String userName; 
    private double saldo;
    private final Map<Integer, Riwayat> riwayatPesanan = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    private final GoRideService goRideService = new GoRideService(scanner);
    private final GoCarService goCarService = new GoCarService(scanner);
    private final GoFoodService goFoodService = new GoFoodService(scanner);
    private final GoMartService goMartService = new GoMartService(scanner);
    private final GoSendService goSendService = new GoSendService(scanner);
    private final GoLifeService goLifeService = new GoLifeService(scanner);
    private final TopupService topupService = new TopupService(scanner);
    
    private final Set<Integer> validMenuOptions = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));


    public static void main(String[] args) {
        ConsoleUtil.clearScreen();
        GojekApp app = new GojekApp();
        app.run();
    }

    public void run() {
        pendahuluan();

        while (true) {
            tampilkanMenuUtama();
            System.out.print("\n> PILIHAN : ");
            int pilihanMenu = scanner.nextInt();
            scanner.nextLine(); 

            if (!validMenuOptions.contains(pilihanMenu)) {
                System.out.println("Pilihan tidak valid, silakan coba lagi.");
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
                continue;
            }

            try {
                switch (pilihanMenu) {
                    case 1: saldo -= goRideService.handle(riwayatPesanan); break;
                    case 2: saldo -= goCarService.handle(riwayatPesanan); break;
                    case 3: saldo -= goFoodService.handle(riwayatPesanan); break;
                    case 4: saldo -= goMartService.handle(riwayatPesanan); break;
                    case 5: saldo -= goSendService.handle(riwayatPesanan); break;
                    case 6: saldo -= goLifeService.handle(riwayatPesanan); break;
                    case 7: saldo = topupService.handle(saldo); break;
                    case 8: tampilkanRiwayat(); break;
                    case 0:
                        System.out.println("\nTerima kasih telah menggunakan GojekApp, " + this.userName + "!");
                        return;
                }
                 System.out.print("\nTekan Enter untuk kembali ke menu utama...");
                 scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage());
                e.printStackTrace();
                try { Thread.sleep(2000); } catch (InterruptedException ie) {}
            }
        }
    }
    
    private void pendahuluan() {
        System.out.println("Selamat Datang di Gojek!");
        System.out.print("Silakan masukkan nama profil Anda: ");
        this.userName = scanner.nextLine();
        this.saldo = 1000000;
        System.out.println("\nTerima Kasih, " + this.userName + "! Memuat aplikasi...");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    private void tampilkanMenuUtama() {
        ConsoleUtil.clearScreen();
        System.out.println("===== GOJEK APP =====");
        System.out.println("Profil: " + this.userName); 
        System.out.printf("Saldo Anda: Rp %,.0f%n", saldo);
        System.out.println("---------------------");
        System.out.println("Pilih Layanan:");
        System.out.println("1. GoRide");
        System.out.println("2. GoCar");
        System.out.println("3. GoFood");
        System.out.println("4. GoMart");
        System.out.println("5. GoSend");
        System.out.println("6. GoLife");
        System.out.println("---------------------");
        System.out.println("7. Top Up Saldo");
        System.out.println("8. Riwayat Transaksi");
        System.out.println("0. Keluar");
    }

    private void tampilkanRiwayat() {
        System.out.println("\n--- Riwayat Transaksi ---");
        if (riwayatPesanan.isEmpty()) {
            System.out.println("Belum ada riwayat pesanan.");
        } else {
            System.out.println("| ID | Layanan | Biaya         |");
            System.out.println("|----|---------|---------------|");
            for (Map.Entry<Integer, Riwayat> entry : riwayatPesanan.entrySet()) {
                System.out.printf("| %-2d | %-7s | Rp %,-10d |%n", entry.getKey(), entry.getValue().deskripsi, entry.getValue().harga);
            }
        }
    }
}