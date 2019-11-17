@extends('layouts.layout')

@section('title','Dashboard')
@section('judul','Dashboard')
@section('content')

<div class="">
    <div class="card">
        <h5 class="card-header">Total Pendapatan</h5>
        <div class="card-body">
            <div>
                <canvas id="canvas"></canvas>
            </div>
        </div>
    </div>
</div>

<div class="card">
    <h5 class="card-header">Produk Terlaris</h5>
    <div class="card-body">
        <table class="table table-hover">
            <thead>
                <th>
                    No
                </th>
                <th>
                    Nama Produk
                </th>
                <th>
                    Total Dibeli
                </th>
            </thead>
            <tbody>
                @foreach ($produk as $p)
                <tr>
                    <td>{{$loop->iteration}}</td>
                    <td>{{$p->nama_produk}}</td>
                    <td>{{$p->total}}</td>
                </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</div>


@endsection