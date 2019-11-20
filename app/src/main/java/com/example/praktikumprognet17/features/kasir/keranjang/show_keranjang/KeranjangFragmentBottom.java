package com.example.praktikumprognet17.features.kasir.keranjang.show_keranjang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.apihelper.UtilsApi;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.praktikumprognet17.util.Tools;


public class KeranjangFragmentBottom extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;
    private LinearLayout layout_keranjang;
    BaseApiService mApiService;
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultKeranjang> results = new ArrayList<>();
    private KeranjangRecyclerViewAdapter viewAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.activity_keranjang_dialog, null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        layout_keranjang = (LinearLayout) view.findViewById(R.id.keranjang_dialog);
        loadDataKeranjang(view);

        ((View) view.findViewById(R.id.lyt_spacer)).setMinimumHeight(Tools.getScreenHeight() / 2);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomsheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
//                    hideView(layout_keranjang);
                }
                if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    showView(layout_keranjang,getActionBarSize());
                }
                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        return dialog;
    }

    public void loadDataKeranjang(View view){
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KeranjangRecyclerViewAdapter(getActivity(),results);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_keranjang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(viewAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiService api = retrofit.create(BaseApiService.class);
        Call<ValueKeranjang> call = api.viewKeranjang();
        call.enqueue(new Callback<ValueKeranjang>() {
            @Override
            public void onResponse(Call<ValueKeranjang> call, Response<ValueKeranjang> response) {
                results = response.body().getResult();
                viewAdapter = new KeranjangRecyclerViewAdapter(getActivity(),results);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<ValueKeranjang> call, Throwable t) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void hideView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);
    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize() {
        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int size = (int) styledAttributes.getDimension(0, 0);
        return size;
    }
}
