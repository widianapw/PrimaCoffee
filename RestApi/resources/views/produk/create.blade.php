@extends('layouts.layout')

@section('title','Tambah Produk')
@section('judul','Tambah Produk')
@section('content')

<form action="/produk" method="POST" class="form-group">
    @csrf
    <div class="form-body">

        <div class="form-label">
            <label for="nim">Nama Produk</label>
        </div>
        <input type="text" class="form-control" name="nama_produk" required/>
        <br>
        <div class="form-label">
            <label for="nim">Harga</label>
        </div>
        <input type="number" class="form-control" name="harga" required/>
        <br>
        <div class="form-label">
            <label for="nim">Kategori</label>
        </div>
        <select name="kategori" class="form-control" required>
            <option value="">Pilih Kategori</option>
            @foreach ($kategori as $k)
            <option value="{{$k->id}}">{{$k->kategori}}</option>
            @endforeach
        </select>
        <br>
        {{-- <div class="form-label">
                                        <label for="nama">Tipe</label>
                                    </div> --}}
        {{-- <input type="text" class="form-control" name="tipe" />  --}}

        <br>
        <div class="form-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
</form>

@endsection