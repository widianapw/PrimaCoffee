@extends('layouts.layout')
@section('title','Edit Kategori')
@section('judul','Edit Kategori')
@section('content')
<form action="/kategori/{{$kategori->id}}" method="POST" class="form-group">
    @csrf
    @method("PUT")
    <div class="form-body">
        <div class="form-label">
            <label for="nim">Kategori</label>
        </div>
        <input type="text" class="form-control" name="kategori" value="{{$kategori->kategori}}" required/>

        <br>
        <div class="form-footer">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
</form>
@endsection