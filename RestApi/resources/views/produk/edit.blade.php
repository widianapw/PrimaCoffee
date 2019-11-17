@extends('layouts.layout')
@section('title','Edit Produk')
@section('judul','Edit Produk')
@section('content')

<form action="/produk/{{$produk->id}}" method="POST" class="form-group">
    @csrf
    @method("PUT")
    <div class="form-header">
        <h3>Form Edit Produk</h3>
    </div>

    <div class="form-body">
        <div class="form-label">
            <label for="nim">Produk</label>
        </div>
        <input type="text" class="form-control" name="nama_produk" value="{{$produk->nama_produk}}" required/>
        <br>
        <div class="form-label">
            <label for="nim">Harga</label>
        </div>
        <input type="text" class="form-control" name="harga" value="{{$produk->harga}}" required />
        <br>

        <select name="id_kategori" class="form-control" required>
            @foreach ($kategori as $k)
            <option value="{{$k->id}}" @if($produk->id_kategori == $k->id)
                selected="selected"
                @endif>{{$k->kategori}}</option>
            @endforeach
        </select>

        <br>
        <div class="form-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
</form>


@endsection