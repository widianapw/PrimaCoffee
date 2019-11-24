<?php

namespace App;
use Laravel\Passport\HasApiTokens;
use Illuminate\Database\Eloquent\Model;

class Produk extends Model
{
    use HasApiTokens;
    protected $primaryKey = "id";
    protected $table = "tb_produk";
    protected $fillable = ['id_kategori','nama_produk','harga'];
}
