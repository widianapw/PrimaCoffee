package com.example.praktikumprognet17.database.entity;

public class Terlaris {
    int total;
    String nama_produk;

    public Terlaris(int total, String nama_produk){
        this.total = total;
        this.nama_produk = nama_produk;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }
}
