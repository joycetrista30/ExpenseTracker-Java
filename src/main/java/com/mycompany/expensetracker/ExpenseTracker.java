package com.mycompany.expensetracker;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    private static ArrayList<Expense> expenseList = new ArrayList<>();
    private static ArrayList<String> categories = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;
    private static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        categories.add("Food");
        categories.add("Transport");
        categories.add("Bills");
        loadFromFile();
        int choice;
        do {
            System.out.println("\n EXPENSE TRACKER APPLICATION ");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expense");
            System.out.println("3. Manage Categories");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            
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
                    manageCategories();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Thank You for using the application!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again");
            }
        } while (choice != 4);
    }

    private static void addExpense() {
        System.out.print("Enter Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Amount: ");
        // Note : Input number only
        while (!scanner.hasNextDouble()) { 
            System.out.println("Error: Please enter a numeric value for the amount!");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.println("Available Categories: " + categories);
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();        
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        // save expense data in a list
        expenseList.add(new Expense(idCounter++, date, amount, category, description));
        System.out.println("Expense recorded successfully!");
    }

    private static void viewExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("No expense records found");
        } else {
            System.out.println("\n Expense List ");
            for (Expense e : expenseList) {
                System.out.println(e);
            }
        }
    }
    private static void manageCategories() {
        System.out.println("\n1. View Categories\n2. Add Category");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        if (subChoice == 1) {
            System.out.println("Categories: " + categories); // [cite: 181]
        } else if (subChoice == 2) {
            System.out.print("New Category Name: ");
            categories.add(scanner.nextLine());
            System.out.println("Category added!"); // [cite: 178]
        }
    }
    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenseList) {
                writer.println(e.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage()); // [cite: 183]
        }
    }
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    expenseList.add(new Expense(id, parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                    idCounter = Math.max(idCounter, id + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data."); // [cite: 183]
        }
    }
}