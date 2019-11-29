package com.example.praktikumprognet17.apihelper;

import android.content.Intent;

import com.example.praktikumprognet17.features.home.show_home.ValueReport;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.ValueKeranjang;
import com.example.praktikumprognet17.features.kasir.show_produk.ValueProduk;
import com.example.praktikumprognet17.features.setting.edit_profil.ValueUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("name") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("c_password") String c_password);

//    @GET("kategori/")
//    Call<ValueKategori> view();

    @GET("produk/")
    Call<ValueProduk> viewProduk();

    @FormUrlEncoded
    @POST("keranjang")
    Call<ResponseBody> keranjangRequest(@Field("id_produk") int id_produk1,
                                        @Field("qty") int qty);

    @GET("getuser/{id}")
    Call<ValueUser> viewUser(@Path("id") int id);

    @GET("keranjang/")
    Call<ValueKeranjang> viewKeranjang();

    @DELETE("keranjang/{id}")
    Call<ResponseBody> deleteKeranjang(@Path("id") int id);

    @FormUrlEncoded
    @PUT("updateProfile/")
    Call<ResponseBody> updateProfile(@Field("id") int id, @Field("email") String email, @Field("name") String name);

    @FormUrlEncoded
    @PUT("updatePassword/")
    Call<ResponseBody> updatePassword(@Field("id") int id, @Field("pass_lama") String pass_lama, @Field("pass_baru") String pass_baru);

    @FormUrlEncoded
    @PUT("keranjang/{id}")
    Call<ResponseBody> updateKeranjang( @Path("id") int id_keranjang,@Field("qty") int qty);

    @FormUrlEncoded
    @POST("insertTransaksi")
    Call<ResponseBody> insertTransaksi(@Field("id_user") int id_user);

    @GET("produkTerlaris")
    Call<ValueReport> getTerlaris();
}
