package com.example.praktikumprognet17.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_report")
public class Report implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "id_produk")
    private int id_produk;

    @NonNull
    @ColumnInfo(name = "nama_produk")
    private String nama_produk;

    @NonNull
    @ColumnInfo(name = "harga")
    private int harga;

    @NonNull
    @ColumnInfo(name = "qty")
    private int qty;

    @ColumnInfo(name = "time")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



//    @Ignore
//    private
//    int total;
//
//    public int getTotal() {
//        return total;
//    }
//
//    public void setTotal(int jumlah) {
//        this.total = jumlah;
//    }


    //    @NonNull
//    @ColumnInfo(name = "created_at")
//    private Date created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

//    public Timestamp getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(Timestamp created_at) {
//        this.created_at = created_at;
//    }
}
