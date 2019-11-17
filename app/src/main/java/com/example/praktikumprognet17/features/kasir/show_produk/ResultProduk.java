package com.example.praktikumprognet17.features.kasir.show_produk;

public class ResultProduk {
    int id;
    int total_keranjang;
    String nama_produk;
    int harga_total;
    int harga;

    public int getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(int harga_total) {
        this.harga_total = harga_total;
    }

    public int getTotal_keranjang() {
        return total_keranjang;
    }

    public void setTotal_keranjang(int total_keranjang) {
        this.total_keranjang = total_keranjang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
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

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }
}
