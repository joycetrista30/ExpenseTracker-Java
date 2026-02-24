/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.expensetracker;
/**
 *
 * @author Joyce
 */
public class Expense {
    private int id;
    private String date;
    private double amount;
    private String category;
    private String description;

    public Expense(int id, String date, double amount, String category, String description) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
    public int getId() { return id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCategory() { return category; }
    public void setDescription(String description) { this.description = description; }
    
    public String toFileFormat() {
        return id + "," + date + "," + amount + "," + category + "," + description;
    }

    // Getter untuk menampilkan data di laporan nanti
    @Override
    public String toString() {
        return "ID: " + id + " | Tgl: " + date + " | Jml: Rp" + amount + 
               " | Kat: " + category + " | Desk: " + description;
    }
}
