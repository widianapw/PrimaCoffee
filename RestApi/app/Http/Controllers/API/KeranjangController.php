<?php

namespace App\Http\Controllers\API;
use App\DetTransaksi;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Keranjang;
use App\Transaksi;
use DB;

class KeranjangController extends Controller
{
    public $successStatus = 200;
    public function keranjangNum()
    {
        $num_keranjang = Keranjang::count();
        return array();
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $keranjang = Keranjang::select('tb_keranjang.id','tb_keranjang.id_produk','nama_produk','harga','qty')
            ->join('tb_produk','tb_keranjang.id_produk','=','tb_produk.id')
            ->get();
        $harga_keranjang = Keranjang::select(DB::raw('COALESCE (SUM(harga*qty),0) as harga_total'))
            ->join('tb_produk','tb_keranjang.id_produk','=','tb_produk.id')
            ->get();
        return array("result"=>$keranjang, "harga_keranjang"=>$harga_keranjang);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $input = $request->all();
        $validate = Keranjang::where('id_produk',$request->id_produk)->count();
        
        if($validate>0){
            $keranjang = Keranjang::where('id_produk',$request->id_produk)->get()->first();
            
            $keranjang->qty = $keranjang->qty + $request->qty;
            $keranjang->update();
            
        }
        else{
            $keranjang = Keranjang::create($input);
        }
        $result = array(
            "status" => true,
            "data" => $keranjang,
        );
        
        
        return response()->json($result, $this->successStatus);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {   
        $keranjang = Keranjang::where('id',$id)->first();
        $keranjang->qty = $request->qty;
        $keranjang->update();
        return $keranjang;
    }

    public function updateKeranjang(Request $request){

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        Keranjang::find($id)->delete();
        $keranjang = Keranjang::select('tb_keranjang.id','tb_keranjang.id_produk','nama_produk','harga','qty')
            ->join('tb_produk','tb_keranjang.id_produk','=','tb_produk.id')
            ->get();
        return array("status"=>true,"result"=>$keranjang);        
    }

    public function insertTransaksi(request $request){
        $transaksi = new Transaksi;
        $transaksi->id_user = $request->id_user;
        $transaksi->save();

        $keranjang = Keranjang::get();
        // return $keranjang;
        foreach($keranjang as $k){
            $detail = new DetTransaksi;
            $detail->id_transaksi = $transaksi->id;
            $detail->id_produk = $k->id_produk;
            $detail->qty = $k->qty;
            $detail->save();
        }
        
        Keranjang::truncate();
        return $keranjang;
    }
}
