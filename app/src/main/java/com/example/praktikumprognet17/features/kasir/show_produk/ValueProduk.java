package com.example.praktikumprognet17.features.kasir.show_produk;


import java.util.List;

public class ValueProduk {
    String value;
    String message;
    int total_keranjang;
    List<ResultProduk> result;
    List<ResultProduk> harga_keranjang;

//    List<ResultProduk> total_keranjang;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<ResultProduk> getResult() {
        return result;
    }
//
//    public List<ResultProduk> getTotal_keranjang(){
//        return total_keranjang;
//    }


    public int getTotal_keranjang() {
        return total_keranjang;
    }

    public void setTotal_keranjang(int total_keranjang) {
        this.total_keranjang = total_keranjang;
    }

    public List<ResultProduk> getHarga_keranjang() {
        return harga_keranjang;
    }
}
