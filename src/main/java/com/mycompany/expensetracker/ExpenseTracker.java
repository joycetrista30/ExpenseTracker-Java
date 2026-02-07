package com.mycompany.expensetracker;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    private static ArrayList<Expense> expenseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== EXPENSE TRACKER APPLICATION ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expense");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            
            while (!scanner.hasNextInt()) { 
                System.out.println("Error : Please enter a valid number!");
                scanner.next();
            }
            
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    System.out.println("Thank You for using the application!");
                    break;
                default:
                    System.out.println("Invalid Choice! Please Try Again");
            }
        } while (choice != 3);
    }

    private static void addExpense() {
        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        
        System.out.print("Masukkan Jumlah: ");
        // Note : Input number only
        while (!scanner.hasNextDouble()) { 
            System.out.println("Error: Masukkan angka untuk jumlah pengeluaran!");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        
        System.out.print("Masukkan Kategori (Makan/Transport/Lainnya): ");
        String category = scanner.nextLine();        
        System.out.print("Masukkan Deskripsi: ");
        String description = scanner.nextLine();

        // save expense data in a list
        expenseList.add(new Expense(idCounter++, date, amount, category, description));
        System.out.println("Pengeluaran berhasil dicatat!");
    }

    private static void viewExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("Belum ada catatan pengeluaran.");
        } else {
            System.out.println("\n--- Daftar Pengeluaran ---");
            for (Expense e : expenseList) {
                System.out.println(e);
            }
        }
    }
}