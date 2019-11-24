package com.example.praktikumprognet17.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.praktikumprognet17.database.entity.Keranjang;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.ResultKeranjang;

import java.util.List;

@Dao
public interface KeranjangDAO {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    long insert(Keranjang keranjang);
//
//    @Update
//    void update(Keranjang keranjang);
//
    @Delete
    void delete(Keranjang keranjang);
//
//    @Query("DELETE FROM tb_keranjang")
//    void deleteAll();
//
//    @Query("SELECT tb_keranjang.id, qty, nama_produk, harga  from tb_keranjang INNER JOIN tb_produk ON tb_keranjang.id_produk = tb_produk.id_produk")
    @Query("SELECT * FROM tb_keranjang")
    Keranjang[] readDataKeranjang();
}
