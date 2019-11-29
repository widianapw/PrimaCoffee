package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;


public class ResultKeranjang {
    int id;
    int id_produk;
    int qty;
    String nama_produk;
    int harga;
    String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    int harga_total;

//    Date created_at;
//
//    public Date getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(Date created_at) {
//        this.created_at = created_at;
//    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

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
