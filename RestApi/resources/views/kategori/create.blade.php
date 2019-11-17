@extends('layouts.layout')

@section('title','Tambah Kategori')
@section('judul','Tambah Kategori')
@section('content')
<form action="/kategori" method="POST" class="form-group">
    @csrf
    <div class="form-body">

        <div class="form-label">
            <label for="nim">Kategori</label>
        </div>
        <input type="text" class="form-control" name="kategori" required />

        <br>
        <div class="form-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
</form>
@endsection