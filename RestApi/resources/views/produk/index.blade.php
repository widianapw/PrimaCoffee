@extends('layouts.layout')

@section('title','Data Produk')
@section('judul','Data Produk')
@section('content')

<form action="/produk/create/" method="GET" class="button">
    <button type="submit" class="btn btn-primary">
        Tambah Data
    </button>
</form>
<div class="table-responsive">
    <table class="table table-striped ">
        <thead>
            <tr>
                <th>No.</th>
                <th>Produk</th>
                <th>Harga</th>
                <th>Kategori</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($data as $m)
            <tr>
                <td>{{ $loop->iteration }}</td>
                <td>{{ $m->nama_produk}}</td>
                <td>{{ $m->harga }}</td>
                <td>{{ $m->kategori }}</td>
                <td>
                    <form action="/produk/{{$m->id}}/edit" method="GET">
                        @csrf
                        <button type="submit" class="btn btn-warning">
                            <i class="fa fa-fw fa-edit" style="color:white"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="/produk/{{$m->id}}/" method="POST">
                        @method("DELETE")
                        @csrf
                        <button type="submit" onclick="return confirm('Are you sure?')" class="btn btn-danger">
                            <i class="fa fa-fw fa-trash"></i>
                        </button>
                    </form>
                </td>
            </tr>
            @endforeach
        </tbody>
    </table>
</div>



@endsection