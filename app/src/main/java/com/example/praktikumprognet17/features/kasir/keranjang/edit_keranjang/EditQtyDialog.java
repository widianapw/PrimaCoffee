package com.example.praktikumprognet17.features.kasir.keranjang.edit_keranjang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang.KeranjangActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditQtyDialog extends DialogFragment {

    View mView;
    KeranjangActivity mContext;
    BaseApiService mApiService;
    public EditQtyDialog(KeranjangActivity context){
        mContext = context;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        Bundle mArgs = getArguments();
        int id_keranjang = mArgs.getInt("id");
        String nama = mArgs.getString("nama");
        int qty_awal = mArgs.getInt("qty");

        View v = inflater.inflate(R.layout.activity_edit_qty_dialog, null);
        EditText etEditQty = v.findViewById(R.id.editQtyItem);
        etEditQty.setText(Integer.toString(qty_awal));


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v)
                .setMessage(nama)
                .setPositiveButton(R.string.save, (dialog, id) -> {

                    mApiService = UtilsApi.getAPIService();
                    Log.e("ID KERANJANG", ""+id_keranjang);
                    int qty = Integer.parseInt(etEditQty.getText().toString());

                    mApiService.updateKeranjang(id_keranjang, qty).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            mContext.loadDataKeranjang();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });

                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {

                });

        return builder.create();
    }
}
