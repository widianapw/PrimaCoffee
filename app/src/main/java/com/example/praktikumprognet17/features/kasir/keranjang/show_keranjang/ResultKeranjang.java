package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

public class ResultKeranjang {
    int id;
    int qty;
    String nama_produk;
    int harga;
    int harga_total;

    public int getHarga_total() {
        return harga_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public int getHarga() {
        return harga;
    }

    public int getQty() {
        return qty;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
