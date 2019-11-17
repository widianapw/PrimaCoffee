<?php

namespace App\Http\Controllers;
use App\Produk;
use App\Kategori;

use Illuminate\Http\Request;

class ProdukController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */

    public function __construct()
    {
        $this->middleware('auth');
    }
    
    public function index()
    {
        $data = Produk::select('tb_produk.id','kategori','nama_produk','harga')
            ->join('tb_kategori','tb_produk.id_kategori','=','tb_kategori.id')
            ->get(); 
        return view("produk.index",compact("data"));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $kategori= Kategori::get();
        
        return view('produk.create',compact("kategori"));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $produk = new Produk;
        $produk->nama_produk = $request->nama_produk;
        $produk->harga = $request->harga;
        $produk->id_kategori = $request->kategori;
        $produk->save();
        return redirect('/produk');
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
        $produk = Produk::select('tb_produk.id','tb_produk.id_kategori','kategori','nama_produk','harga')
            ->join('tb_kategori','tb_produk.id_kategori','=','tb_kategori.id')
            ->where('tb_produk.id',$id)
            ->get()
            ->first();
        $kategori= Kategori::get();
        return view('produk.edit',compact("produk","kategori"));
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
        // return($request);
        Produk::find($id)->update($request->all());
        return redirect('/produk');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        Produk::where('id',$id)->delete();
        return redirect('/produk');
    }
}
