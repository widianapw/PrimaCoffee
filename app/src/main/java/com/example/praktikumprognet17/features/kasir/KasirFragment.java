package com.example.praktikumprognet17.features.kasir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.praktikumprognet17.R;
import com.example.praktikumprognet17.apihelper.BaseApiService;
import com.example.praktikumprognet17.features.kasir.show_produk.ProdukRecyclerViewAdapter;
import com.example.praktikumprognet17.features.kasir.show_produk.ResultProduk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KasirFragment extends Fragment {
    public static final String URL = "http://10.0.2.2:8000/api/";
    private List<ResultProduk> results = new ArrayList<>();
    private ProdukRecyclerViewAdapter viewAdapter;
    BaseApiService mApiService;

    @BindView(R.id.recycler_produk)
    RecyclerView recycler_produk;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        final RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_produk);
//        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        final ProdukRecyclerViewAdapter produkAdapter = new ProdukRecyclerViewAdapter(KasirFragment.this, results);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.smoothScrollToPosition(recyclerView.getBottom());
//        recyclerView.setAdapter(produkAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_kasir_fragment, container, false);
        return view;
    }
}
