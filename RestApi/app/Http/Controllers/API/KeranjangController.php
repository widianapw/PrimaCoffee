<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Keranjang;
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
        return array("result"=>$keranjang);
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
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
