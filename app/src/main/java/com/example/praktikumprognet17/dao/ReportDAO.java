package com.example.praktikumprognet17.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.praktikumprognet17.database.entity.Report;

import java.util.List;

@Dao
public interface ReportDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Report report);

    @Query("SELECT nama_produk, SUM(qty) as total FROM tb_report WHERE strftime('%Y',time) = strftime('%Y','now') group by id_produk order by total desc")
    List<Terlaris> readDataTerlaris();

    @Query("select case strftime('%m',time) when '01' then 'January' when '02' then 'Febuary' when '03' then 'March' when '04' then 'April' when '05' then 'May' when '06' then 'June' when '07' then 'July' when '08' then 'August' when '09' then 'September' when '10' then 'October' when '11' then 'November' when '12' then 'December' else '' end as bulan, strftime('%m',time) as bln, SUM(qty * harga) as total from tb_report where strftime('%Y',time) = strftime('%Y','now') group by bln order by bln asc")
    List<ReportTahun> reportTahun();

    class Terlaris {
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

    class ReportTahun{
        String bulan;
        int total;

        public String getBulan() {
            return bulan;
        }

        public void setBulan(String bulan) {
            this.bulan = bulan;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

}
