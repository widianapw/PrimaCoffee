<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class DetTransaksi extends Model
{
    protected $table = "tb_det_transaksi";
    protected $fillable = ['id_transaksi','id_produk','qty'];
}
