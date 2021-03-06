package com.example.praktikumprognet17.features.kasir.qty_dialog;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kasir.KasirFragment;
import com.example.praktikumprognet17.features.kasir.show_produk.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QtyDialog extends DialogFragment {
    KasirFragment mContext;
    BaseApiService mApiService;
    int qty;
    private EditText etQty;

    private OnItemClickListener listener;
    final Fragment fragment1 = new KasirFragment();
    View mView;

    public QtyDialog(KasirFragment context, View view){
        mContext = context;
        mView = view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        Bundle mArgs = getArguments();
        String nama = mArgs.getString("nama");
        View v = inflater.inflate(R.layout.activity_qty_dialog, null);
        etQty = v.findViewById(R.id.qtyItem);


//        database = Room.databaseBuilder(
//                getContext(),
//                KeranjangAppDatabase.class,
//                "rest_api") //Nama File Database yang akan disimpan
//                .build();


        builder.setView(v)
                // Add action buttons
                .setMessage(nama)
                .setPositiveButton(R.string.save, (dialog, id) -> {
                    qty = Integer.parseInt(etQty.getText().toString());
                    int id_produk1 = mArgs.getInt("id");



                    Log.e("id_produk", "" + id_produk1);
                    mApiService = UtilsApi.getAPIService();
                    mApiService.keranjangRequest(id_produk1, qty).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Log.i("debug", "onResponse: BERHASIL");
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("status").equals("true")) {
                                        Log.e("id_produk", "Data Berhasil Ditambahkan");

//                                        Bundle args = new Bundle();
//                                        args.putString("from","dialog");
//                                        listener.onItemClicked(v,args);

                                        mContext.loadDataProduk(mView);

                                    } else {
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        QtyDialog.this.getDialog().cancel();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.i("debug", "onResponse: GA BERHASIL");
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                        }
                    });
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        QtyDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_qty_dialog, container);
//        etQty = (EditText) view.findViewById(R.id.qtyItem);
//        Log.e("as","klg"+etQty.getText().toString());
        return view;

    }
}
