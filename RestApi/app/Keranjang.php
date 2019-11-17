<?php

namespace App;
use Laravel\Passport\HasApiTokens;
use Illuminate\Database\Eloquent\Model;

class Keranjang extends Model
{
    
    protected $table = "tb_keranjang";
    protected $fillable = ['id_produk','qty'];
}
