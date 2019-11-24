<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\User;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;
use Validator;

class UserController extends Controller
{

    public $successStatus = 200;

    public function login(){
        if(Auth::attempt(['email' => request('email'), 'password' => request('password'),'is_admin' => 0])){
            $user = Auth::user();
            $success['token'] =  $user->createToken('nApp')->accessToken;
            $result = array(
                "status" => true,
                "token" => $user->createToken('nApp')->accessToken,
                "message" => "Login Berhasil",
                "data" => $user,
            );
            return response()->json($result, $this->successStatus);
        }
        else{
            return response()->json(['error'=>'Unauthorised'], 401);
        }
        
    }

    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'email' => 'required|email',
            'password' => 'required',
            'c_password' => 'required|same:password',
            'is_admin' => '0',
        ]);

        if ($validator->fails()) {
            return response()->json(['error'=>$validator->errors()], 401);            
        }

        $input = $request->all();
        $input['password'] = bcrypt($input['password']);
        $user = User::create($input);
        
        $result = array(
            "status" => true,
            "token" => $user->createToken('nApp')->accessToken,
            "name" => $user,
        );
        return response()->json($result, $this->successStatus);
    }

    public function details()
    {
        $user = Auth::user();
        return response()->json(['success' => $user], $this->successStatus);
    }

    public function logout(){
        $user = Auth::user();
        Auth::guard('web')->logout();
        return response()->json(['success' => $user], $this->successStatus);
    }

    public function getUser($id){
        $user = User::where('id',$id)->get()->first();
        return response()->json($user, $this->successStatus);
    }

    public function updateProfile(request $request){
        
        $user = User::where('id',$request->id)->first();
        $user->email = $request->email;
        $user->name = $request->name;
        $user->update();
        return $user;
    }

    public function updatePassword(request $request){
        
        $user = User::where('id',$request->id)->first();
        
        if (Hash::check($request->pass_lama, $user->password)) {
            $user->password = Hash::make($request->pass_baru);
            $user->update();
            $result = array(
                "status" => true,
                "data" => $user,
            );
        }
        else{
            $result = array(
                "status" => false,
                "data" => $user,
            );
        }
        return response()->json($result, $this->successStatus);  
        
    }
}