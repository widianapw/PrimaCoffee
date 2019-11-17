<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
| 
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::resource('/keranjang','API\KeranjangController');
Route::post('login', 'API\UserController@login');
Route::get('logout', 'API\UserController@logout');
Route::post('register', 'API\UserController@register');
Route::resource('/kategori', 'API\KategoriController');
Route::resource('/produk', 'API\ProdukController');
Route::get('/getuser/{id}', 'API\UserController@getUser');
Route::group(['middleware' => 'auth:api'], function(){
    Route::get('details', 'API\UserController@details');
});