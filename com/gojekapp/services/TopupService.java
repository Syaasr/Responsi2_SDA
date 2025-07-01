package com.gojekapp.services;
import java.util.Scanner;

public class TopupService {
    private final Scanner scanner;
    public TopupService(Scanner scanner) { this.scanner = scanner; }

    public double handle(double saldo) throws InterruptedException {
        System.out.println("\n--- Top Up Saldo ---");
        System.out.printf("Saldo Anda saat ini: Rp %,.0f\n", saldo);
        System.out.print("Masukkan jumlah top up: ");
        double jumlah = scanner.nextDouble(); scanner.nextLine();
        System.out.println("Memproses...");
        Thread.sleep(1500);
        System.out.println("Top up berhasil!");
        return saldo + jumlah;
    }
}