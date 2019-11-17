@extends('layouts.layout')

@section('title','Data Kategori')
@section('judul','Data Kategori')
@section('content')
<form action="/kategori/create/" method="GET" class="button">
    <button type="submit" class="btn btn-primary">
        Tambah Data
    </button>
</form>
<div class="table-responsive">
    <table class="table table-striped ">
        <thead>
            <tr>
                <th>No.</th>
                <th>Kategori</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($data as $m)
            <tr>
                <td>{{ $loop->iteration }}</td>
                <td>{{ $m->kategori }}</td>
                <td>
                    <form action="/kategori/{{$m->id}}/edit" method="GET">
                        @csrf
                        <button type="submit" class="btn btn-warning">
                            <i class="fa fa-fw fa-edit" style="color:white"></i>
                        </button>
                    </form>
                </td>
                <td>
                    <form action="/kategori/{{$m->id}}/" method="POST">
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