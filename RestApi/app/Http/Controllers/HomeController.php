<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Transaksi;
use App\DetTransaksi;
use App\Produk;
use DB;
class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
       $produk = Transaksi::select('tb_produk.nama_produk',DB::raw('COALESCE(SUM(tb_det_transaksi.qty),0)as total'))
       ->join('tb_det_transaksi','tb_det_transaksi.id_transaksi','=','tb_transaksi.id')
       ->join('tb_produk','tb_det_transaksi.id_produk','=','tb_produk.id')
       ->groupBy('tb_produk.id')
       ->orderBy('total','DESC')
        ->get();
        return view('dashboard',compact("produk"));
    }

    public function chart(){
        $result = Transaksi::select('tb_transaksi.id_user',DB::raw('MONTHNAME(tb_transaksi.created_at) as bulan'),DB::raw('COALESCE(SUM(tb_produk.harga*tb_det_transaksi.qty),0)as pendapatan'))
        ->join('tb_det_transaksi','tb_det_transaksi.id_transaksi','=','tb_transaksi.id')
        ->join('tb_produk','tb_det_transaksi.id_produk','=','tb_produk.id')
        ->groupBy(DB::RAW('MONTH(tb_transaksi.created_at)'))
        ->where(
            (DB::RAW('YEAR(tb_transaksi.created_at)')),'=',(DB::RAW('YEAR(NOW())'))
        )
        ->get();

        return response()->json($result);
    }

}
