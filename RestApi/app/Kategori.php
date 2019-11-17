<?php

namespace App;
use Laravel\Passport\HasApiTokens;
use Illuminate\Database\Eloquent\Model;

class Kategori extends Model
{
    use HasApiTokens;
    protected $table = "tb_kategori";
    protected $fillable = ['kategori'];
}